package com.ita.microserviceaccountclient.microserviceaccountclient.entity;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class TypeTest {

    @Test
    public void should_invoke_setMethod() {
        //Given
        Type type = new Type();

        //When
        type.setAccountKind("test");
        type.setAccountList(new ArrayList<>());
        type.setId(1);
        type.setImgUrl("test");
        type.setType("test");

        //Then
        Assert.assertNotNull(type);
    }

    @Test
    public void should_invoke_getMethod() {
        //Given
        Type type = new Type();

        //when

        //Then
        assertNull(type.getAccountKind());
        assertNull(type.getType());
        assertThat(type.getAccountList().size(), is(0));
        assertNull(type.getId());
        assertNull(type.getImgUrl());
    }
}