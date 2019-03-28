package com.ita.microserviceuserclient.microserviceuserclient.services;

import com.ita.microserviceuserclient.microserviceuserclient.entities.Account;
import com.ita.microserviceuserclient.microserviceuserclient.entities.User;
import com.ita.microserviceuserclient.microserviceuserclient.po.UserInfo;
import com.ita.microserviceuserclient.microserviceuserclient.repository.UserRepository;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.*;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final AccountService accountService;

    private final long oneDayTime = 1000 * 60 * 60 * 24;

    @Autowired
    public UserService(UserRepository userRepository, AccountService accountService) {
        this.userRepository = userRepository;
        this.accountService = accountService;
    }

    public UserInfo getUserInfo(String userId) {
        UserInfo userInfo = new UserInfo();
        List<User> users = userRepository.findByUserId(userId);
        List<Account> accounts = accountService.getAllUndeletedAccounts(userId);
        if(users == null || users.isEmpty() || accounts.isEmpty()) {
            return userInfo;
        }
        User user = users.get(0);
        List<Account> undeletedAccounts = user.getAccountList().stream().filter(account -> account.getIsDelete().equals("0")).collect(Collectors.toList());
//        List<Account> userAccounts = accounts.stream().filter( account -> account.getUser().equals(user)).collect(Collectors.toList());
        long timeNow = System.currentTimeMillis();
        userInfo.setDays(String.format("%d", (timeNow - user.getDate().getTime() + 1) / oneDayTime));
        userInfo.setRecords(String.format("%d", undeletedAccounts.size()));
        Double balance = 0.0;
        for(Account account : undeletedAccounts) {
            if(account.isIncome()){
                balance += account.getAmount();
            } else {
                balance -= account.getAmount();
            }
        }
        userInfo.setBalance(String.format("%.2f", balance));
        return userInfo;
    }

    public User findUserById(String userId) {
        List<User> users = userRepository.findByUserId(userId);
        if(users == null || users.isEmpty()){
            return null;
        }
        return users.get(0);
    }

    public void addUser(String userId) {
        User user = findUserById(userId);
        if (user == null) {
            User newUser = new User();
            newUser.setUserId(userId);
            newUser.setDate(new Date());
            userRepository.save(newUser);
        }
    }

    public void updateUserInfo(String userId, User user) {
        User userInDb = findUserById(userId);
        String gender = user.getGender();
        if(StringUtils.isNotEmpty(gender)) {
            gender = gender.equals("1") ? "male" : "female";
        }
        userInDb.setGender(gender);
        userInDb.setImage(user.getImage());
        userInDb.setNickName(user.getNickName());
        userRepository.save(userInDb);
    }
}
