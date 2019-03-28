package com.ita.microserviceaccountclient.microserviceaccountclient.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.ita.microserviceaccountclient.microserviceaccountclient.entity.Account;
import com.ita.microserviceaccountclient.microserviceaccountclient.entity.User;
import com.ita.microserviceaccountclient.microserviceaccountclient.po.MonthOfBill;
import com.ita.microserviceaccountclient.microserviceaccountclient.service.AccountService;
import com.ita.microserviceaccountclient.microserviceaccountclient.service.LoginService;
import com.ita.microserviceaccountclient.microserviceaccountclient.service.UserService;
import net.minidev.json.JSONObject;
import org.assertj.core.util.Maps;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.Date;
import java.util.Map;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(AccountController.class)
public class AccountControllerTest {

    @Autowired
    private ObjectMapper mapper;

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;
    @MockBean
    private LoginService loginService;
    @MockBean
    private AccountService accountService;


    @Test
    public void should_return_status_ok_when_call_addAccount_given_account() throws Exception {
        //Given
        String userId = "userId";
        User user = mock(User.class);
        Account account = new Account();
        when(userService.findUserByUserId(userId)).thenReturn(user);
        Map map = Maps.newHashMap("",account);
        doNothing().when(accountService).save(account, user);

        //When
        //Then
        mockMvc.perform(post("?userId=" + userId).content(JSONObject.toJSONString(map)).contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().isOk());
    }


    @Test
    public void should_return_No_Content_when_call_deleteAccount_given_id() throws Exception {
        //Given
        Integer id = 1;
        when(accountService.deleteAccount(id)).thenReturn(true);

        //When
        //Then
        mockMvc.perform(delete("/"+id).contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isNoContent());
    }

    @Test
    public void should_return_Bad_Request_when_call_deleteAccount_given_not_exist_id() throws Exception {
        //Given
        Integer id = 2;
        when(accountService.deleteAccount(id)).thenReturn(false);

        //When
        //Then
        mockMvc.perform(delete("/"+id).contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    public void should_return_null_when_call_getAccountById_given_not_exist_id() throws Exception{
        //given
        Integer id = 2;
        when(accountService.getAccountById(id)).thenReturn(null);
        //when
        //then
        mockMvc.perform(get("/" + id).contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void should_return_badRequest_when_call_updateAccountById_given_not_exist_trd_session() throws Exception{
        //given
        Integer id = 2;
        String userId = "user_id";
        Account account = new Account();
        when(userService.findUserByUserId(userId)).thenReturn(null);
        Map map = Maps.newHashMap("",account);
        //when
        //then
        mockMvc.perform(post("/" + id + "?userId=" + userId).content(JSONObject.toJSONString(map)).contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    public void should_return_noContent_when_call_updateAccountById_given_correct_param() throws Exception{
        //given
        Integer id = 2;
        String userId = "bad_user_id";
        User user = mock(User.class);
        Account account = new Account();

        when(userService.findUserByUserId(userId)).thenReturn(user);
        Map map = Maps.newHashMap("",account);
        when(accountService.updateAccountById(anyInt(), any())).thenReturn(true);


        //when
        //then
        mockMvc.perform(post("/" + id + "?userId=" + userId).content(JSONObject.toJSONString(map)).contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().isNoContent());
    }

    @Test
    public void should_return_exception_when_call_updateAccountById_given_incorrect_param() throws Exception{
        //given
        Integer id = 2;
        String userId = "bad_user_id";
        User user = mock(User.class);
        Account account = new Account();

        when(userService.findUserByUserId(userId)).thenReturn(user);
        Map map = Maps.newHashMap("",account);
        when(accountService.updateAccountById(anyInt(), any())).thenReturn(false);


        //when
        //then
        mockMvc.perform(post("/" + id + "?userId=" + userId).content(JSONObject.toJSONString(map)).contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    public void should_return_accounts_when_call_getAccounts() throws Exception{
        //given
        Account account = new Account();
        account.setUser(new User());
        when(accountService.getAllUndeletedAccounts()).thenReturn(Arrays.asList(account));
        //when
        //then
        mockMvc.perform(get("").contentType(MediaType.APPLICATION_JSON_VALUE));
    }

    @Test
    public void should_return_ok_when_call_getAccountsByMonth() throws Exception{
        //given
        String time = "1";
        String userId = "userId";
        User user = mock(User.class);
        when(userService.findUserByUserId(userId)).thenReturn(user);
        MonthOfBill monthOfBill = new MonthOfBill();
        when(accountService.getAccountsByMonth(time, user)).thenReturn(monthOfBill);
        //when
        //then
        mockMvc.perform(get("/month/" + time + "?userId=" + userId).contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void should_return_badRequest_when_call_getAccountsByMonth() throws Exception{
        //given
        String time = "1";
        String userId = "userId";
        when(userService.findUserByUserId(userId)).thenReturn(null);
        //when
        //then
        mockMvc.perform(get("/month/" + time + "?userId=" + userId).contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

}