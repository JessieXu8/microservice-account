package com.ita.microserviceaccountclient.microserviceaccountclient.service;


import com.ita.microserviceaccountclient.microserviceaccountclient.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceTest {

    @Autowired
    private UserService userService;

    @MockBean
    private DiscoveryClient discoveryClient;

    @MockBean
    private RestTemplate restTemplate;

    @Test
    public void should_return_null_when_call_findUserByUserId_given_userId_null() {
        User result = userService.findUserByUserId(null);
        assertNull(result);
    }

    @Test
    public void should_return_null_when_call_findUserByUserId_given_userId() {
        String userId = "userId";
        when(discoveryClient.getInstances("micro-service-user-client")).thenReturn(null);
        User result = userService.findUserByUserId(userId);
        assertNull(result);
    }

    @Test
    public void should_return_user_when_call_findUserByUserId_given_userId() {
        String userId = "userId";
        when(discoveryClient.getInstances("micro-service-user-client")).thenReturn(Arrays.asList(mock(ServiceInstance.class)));
        User user = new User();
//        when(restTemplate.getForEntity("http://null:0/userId", User.class)).thenReturn(null);
        User result = userService.findUserByUserId(userId);
        assertNull(result);
    }

}