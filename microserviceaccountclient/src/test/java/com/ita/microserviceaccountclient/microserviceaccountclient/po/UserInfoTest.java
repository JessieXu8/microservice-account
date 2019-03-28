package com.ita.microserviceaccountclient.microserviceaccountclient.po;

import com.ita.microserviceaccountclient.microserviceaccountclient.entity.User;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class UserInfoTest {
    @Test
    public void should_test_setMethod() {
        //Given
        UserInfo userInfo = new UserInfo();

        //When
        userInfo.setBalance("12");
        userInfo.setDays("3");
        userInfo.setRecords("record");

        //Then
        Assert.assertNotNull(userInfo);
        Assert.assertEquals("12", userInfo.getBalance());
        Assert.assertEquals("3", userInfo.getDays());
        Assert.assertEquals("record", userInfo.getRecords());
    }

}