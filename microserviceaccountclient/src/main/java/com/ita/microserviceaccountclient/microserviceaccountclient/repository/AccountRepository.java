package com.ita.microserviceaccountclient.microserviceaccountclient.repository;

import com.ita.microserviceaccountclient.microserviceaccountclient.entity.Account;
import com.ita.microserviceaccountclient.microserviceaccountclient.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface AccountRepository extends JpaRepository<Account,Integer> {
    List<Account> findAccountsByDateBetween(Date startDate, Date endDate);
    List<Account> findAllByUserAndDateBetween(User user, Date startDate, Date endDate);
    List<Account> findAllByIsDelete(String deleted);
}
