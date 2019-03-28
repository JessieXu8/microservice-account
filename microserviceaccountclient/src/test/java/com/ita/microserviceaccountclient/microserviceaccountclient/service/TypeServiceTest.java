package com.ita.microserviceaccountclient.microserviceaccountclient.service; 

import com.ita.microserviceaccountclient.microserviceaccountclient.entity.Type;
import com.ita.microserviceaccountclient.microserviceaccountclient.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TypeServiceTest{

    @Autowired
    private TypeService typeService;

    @MockBean
    private RestTemplate restTemplate;

    @MockBean
    private DiscoveryClient discoveryClient;

    @Test
    public void should_return_null_when_call_save_given_geturl_is_success() {
        Object type = new Object();
        when(discoveryClient.getInstances("micro-service-type-client")).thenReturn(null);
        ResponseEntity<ResponseEntity> result = typeService.save(type);
        assertNull(result);
    }

    @Test
    public void should_return_null_when_call_save_given_userId() {
        Object type = new Object();
        when(discoveryClient.getInstances("micro-service-type-client")).thenReturn(Arrays.asList(mock(ServiceInstance.class)));
        ResponseEntity<ResponseEntity> result = typeService.save(type);
        assertNull(result);
    }

    @Test
    public void should_return_null_when_call_findTypeByTypeName_given_userId() {
        Type type = new Type();
        when(discoveryClient.getInstances("micro-service-type-client")).thenReturn(null);
        Type result = typeService.findTypeByTypeName(type);
        assertNull(result);
    }

    @Test
    public void should_return_null_when_call_findTypeByTypeName_given_geturl_is_success() {
        Type type = new Type();
        when(discoveryClient.getInstances("micro-service-type-client")).thenReturn(Arrays.asList(mock(ServiceInstance.class)));
        Type result = typeService.findTypeByTypeName(type);
        assertNull(result);
    }
}
