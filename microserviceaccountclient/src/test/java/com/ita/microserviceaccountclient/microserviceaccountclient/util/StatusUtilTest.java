package com.ita.microserviceaccountclient.microserviceaccountclient.util;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class StatusUtilTest {
    @Test
    public void should_test_getLoginStatus() {
        //Given
        String openId = "test";
        String sessionKey = "test132";

        //When
        String result = StatusUtil.getLoginStatus(openId, sessionKey);

        //Then
        Assert.assertNotNull(result);
    }
}