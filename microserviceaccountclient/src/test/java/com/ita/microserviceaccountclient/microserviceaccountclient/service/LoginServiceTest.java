package com.ita.microserviceaccountclient.microserviceaccountclient.service;

import com.ita.microserviceaccountclient.microserviceaccountclient.util.CacheUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
public class LoginServiceTest {

    @Autowired
    private DiscoveryClient discoveryClient;

    @MockBean
    private CacheUtil cacheUtil;

    @Autowired
    private LoginService loginService;

    @Test
    public void should_return_null_when_call_getOpenId_given_loginStatus() {
        String loginStatus = "login";
        when(cacheUtil.getOpenId(loginStatus)).thenReturn(null);
        String result = loginService.getOpenId(loginStatus);
        assertNull(result);
    }


}