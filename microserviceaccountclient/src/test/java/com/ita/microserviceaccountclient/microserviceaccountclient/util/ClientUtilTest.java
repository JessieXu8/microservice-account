package com.ita.microserviceaccountclient.microserviceaccountclient.util;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ClientUtilTest {

    @Test
    public void should_return_null_when_call_getOpenId() {
        List<ServiceInstance> instances = null;
        String result = ClientUtil.getUrl(instances);
        assertNull(result);
    }

    @Test
    public void should_return_result_when_call_getOpenId() {
        List<ServiceInstance> instances = Arrays.asList(mock(ServiceInstance.class));
        String result = ClientUtil.getUrl(instances);
        assertThat(result, is("http://null:0"));
    }

}
