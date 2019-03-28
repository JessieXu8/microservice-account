package com.ita.microserviceaccountclient.microserviceaccountclient.service;

import com.ita.microserviceaccountclient.microserviceaccountclient.entity.Account;
import com.ita.microserviceaccountclient.microserviceaccountclient.entity.Type;
import com.ita.microserviceaccountclient.microserviceaccountclient.entity.User;
import com.ita.microserviceaccountclient.microserviceaccountclient.repository.AccountRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.ParseException;
import java.util.*;
import java.util.stream.Collectors;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AccountServiceTest {

    @MockBean
    private  TypeService typeService;

    @MockBean
    private AccountRepository accountRepository;

    @Autowired
    private AccountService accountService;


    @Test
    public void should_save_when_call_account_save_given_type_in_db() {
        User user = mock(User.class);
        Type type = new Type();
        type.setType("type");
        Account account = new Account();
        account.setType(type);
        account.setDateStr("2019-03-27");
        when(typeService.findTypeByTypeName(type)).thenReturn(type);
        accountService.save(account,user);
        verify(accountRepository, times(1)).save(any());
    }



    @Test
    public void should_save_when_call_account_save_given_type_not_in_db() {
        User user = mock(User.class);
        Type type =mock(Type.class);
        Account account = new Account();
        account.setType(type);
        account.setDateStr("2019-03-27");
        when(typeService.findTypeByTypeName(type)).thenReturn(null);
        accountService.save(account,user);
        verify(accountRepository, times(1)).save(any());
    }


    @Test
    public void should_delete_account_when_call_deleteAccount_given_id_in_db() {
        Integer id = 1;
        Account account = new Account();
        when(accountRepository.findById(id)).thenReturn(Optional.of(account));
        accountService.deleteAccount(id);
        verify(accountRepository, times(1)).save(any());
    }

    @Test
    public void should_delete_account_when_call_deleteAccount_given_id_not_in_db() {
        Integer id = 1;
        Account account = new Account();
        when(accountRepository.findById(id)).thenReturn(Optional.empty());
        accountService.deleteAccount(id);
        verify(accountRepository, times(0)).save(any());
    }

    @Test
    public void should_return_all_undeleted_accounts_when_call_getAllUndeletedAccounts() throws ParseException {
        when(accountRepository.findAllByIsDelete("0")).thenReturn(Arrays.asList(new Account()));

        List<Account> allUndeletedAccounts = accountService.getAllUndeletedAccounts();

        assertEquals(1, allUndeletedAccounts.size());
    }

    @Test
    public void should_return_true_when_call_updateAccountById_given_id_newAccount() throws ParseException {
        Integer id = 1;
        Account newAccount = new Account();
        newAccount.setType(new Type());
        when(accountRepository.findById(id)).thenReturn(Optional.ofNullable(newAccount));
        when(typeService.findTypeByTypeName(newAccount.getType())).thenReturn(null);

        Boolean result = accountService.updateAccountById(id, newAccount);

        assertTrue(result);
    }

    @Test
    public void should_return_false_when_call_updateAccountById_given_id_newAccount() throws ParseException {
        Integer id = 1;
        Account newAccount = new Account();
        when(accountRepository.findById(id)).thenReturn(Optional.ofNullable(null));

        Boolean result = accountService.updateAccountById(id, newAccount);

        assertFalse(result);
    }

    @Test
    public void should_return_null_when_call_getAccountById_given_id() throws ParseException {
        Integer id = 1;
        when(accountRepository.findById(id)).thenReturn(Optional.ofNullable(null));

        Account result = accountService.getAccountById(id);

        assertNull(result);
    }

}