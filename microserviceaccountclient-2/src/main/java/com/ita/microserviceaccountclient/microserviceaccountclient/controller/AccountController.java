package com.ita.microserviceaccountclient.microserviceaccountclient.controller;

import com.ita.microserviceaccountclient.microserviceaccountclient.po.MonthOfBill;
import com.ita.microserviceaccountclient.microserviceaccountclient.entity.Account;
import com.ita.microserviceaccountclient.microserviceaccountclient.entity.User;
import com.ita.microserviceaccountclient.microserviceaccountclient.exceptions.BadRequestException;
import com.ita.microserviceaccountclient.microserviceaccountclient.service.AccountService;
import com.ita.microserviceaccountclient.microserviceaccountclient.service.LoginService;
import com.ita.microserviceaccountclient.microserviceaccountclient.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("")
public class AccountController {
    @Autowired
    private final AccountService accountService;
    @Autowired
    private final LoginService loginService;
    @Autowired
    private final UserService userService;

    @Autowired
    public AccountController(AccountService accountService, LoginService loginService, UserService userService) {
        this.accountService = accountService;
        this.loginService = loginService;
        this.userService = userService;
    }

    @PostMapping(path="", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity addAccount(@RequestBody Account account, @RequestParam String userId) {
        User user = userService.findUserByUserId(userId);
        if(user == null) return ResponseEntity.badRequest().build();
        accountService.save(account, user);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity deleteAccountByID(@PathVariable Integer id){
        if (accountService.deleteAccount(id)){
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        throw new BadRequestException("Delete error");
    }

    @GetMapping(path = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Account> getAccounts(){
        List<Account> accounts = accountService.getAllUndeletedAccounts();
        for(Account account : accounts) {
            if(account.getUser() != null)
                account.getUser().setUserId(null);
        }
        return accounts;
    }

    @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Account getAccountById(@PathVariable Integer id){
        return accountService.getAccountById(id);
    }

    @PostMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity updateAccountById(@PathVariable Integer id, @RequestBody Account newAccount, @RequestParam String userId){
        User user = userService.findUserByUserId(userId);
        if(user == null) return ResponseEntity.badRequest().build();
        if(accountService.updateAccountById(id, newAccount)){
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        throw new BadRequestException("update error");
    }

    @GetMapping(path = "/month/{time}")
    public ResponseEntity<MonthOfBill> getAccountsByMonth(@PathVariable String time, @RequestParam String userId){
        HttpStatus httpStatus = HttpStatus.OK;
        User user = userService.findUserByUserId(userId);
        if(user == null){
            return ResponseEntity.badRequest().body(new MonthOfBill());
        }
        MonthOfBill monthOfBill = accountService.getAccountsByMonth(time, user);
        return new ResponseEntity<>(monthOfBill, httpStatus);
    }
}
