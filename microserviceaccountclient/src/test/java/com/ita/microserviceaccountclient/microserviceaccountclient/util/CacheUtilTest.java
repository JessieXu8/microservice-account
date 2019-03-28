package com.ita.microserviceaccountclient.microserviceaccountclient.util;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CacheUtilTest{

    @Test
    public void should_return_null_when_call_getOpenId() {
        String loginStatus = "login";
        String result = CacheUtil.Instance.getOpenId(loginStatus);
        assertNull(result);
    }

    @Test
    public void should_return_null_when_call_get_given_loginStatus() {
        String loginStatus = "login";
        String result = CacheUtil.Instance.get(loginStatus);
        assertNull(result);
    }

    @Test
    public void should_success_when_call_put_given_loginStatus_openId_sessionKey() {
        String loginStatus = "loginStatus";
        String openId = "openId";
        String sessionKey = "sessionKey";
        CacheUtil.Instance.put(loginStatus,openId, sessionKey);
    }
}
