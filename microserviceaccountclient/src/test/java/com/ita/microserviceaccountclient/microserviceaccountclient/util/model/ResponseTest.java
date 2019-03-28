package com.ita.microserviceaccountclient.microserviceaccountclient.util.model;

import com.ita.microserviceaccountclient.microserviceaccountclient.po.DayOfBill;
import com.ita.microserviceaccountclient.microserviceaccountclient.po.Record;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.HashMap;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class ResponseTest {
    @Test
    public void should_test_response() {
        //Given
        Response response = new Response();

        //When
        response.setAttributes(new HashMap<>());
        response.setMsg("msg");
        response.setSuccess(true);
        response.setTotal(1L);
        response.setObj(new Object());

        //Then
        assertNotNull(response.getAttributes());
        assertNotNull(response.getMsg());
        assertNotNull(response.getTotal());
        assertNotNull(response.getObj());
        assertNotNull(response.isSuccess());
    }

}