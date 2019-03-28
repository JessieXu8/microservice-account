package com.ita.microserviceaccountclient.microserviceaccountclient.entity;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.Date;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class UserTest {
    @Test
    public void should_invoke_setMethod() {
        //Given
        User user = new User();

        //When
        user.setId(1);
        user.setAccountList(new ArrayList<>());
        user.setDate(new Date());
        user.setGender("1");
        user.setImage("/image/food.png");
        user.setNickName("xinruzhishui");
        user.setUserId("1");

        //Then
        Assert.assertNotNull(user);
    }

    @Test
    public void should_invoke_getMethod() {
        //Given
        User user = new User();

        //When

        //Then
        assertThat(user.getAccountList().size(), is(0));
        assertNull(user.getGender());
        assertNull(user.getId());
        assertNull(user.getImage());
        assertNull(user.getNickName());
        assertNull(user.getUserId());
        assertNull(user.getDate());
    }
}