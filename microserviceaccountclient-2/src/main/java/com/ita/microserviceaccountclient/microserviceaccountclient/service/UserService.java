package com.ita.microserviceaccountclient.microserviceaccountclient.service;


import com.ita.microserviceaccountclient.microserviceaccountclient.entity.User;

import com.ita.microserviceaccountclient.microserviceaccountclient.util.ClientUtil;
import org.hibernate.cache.spi.support.AbstractReadWriteAccess;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private DiscoveryClient discoveryClient;
    public User findUserByUserId(String userId) {
        if(userId == null) return null;
        String url = ClientUtil.getUrl(discoveryClient.getInstances("micro-service-user-client"));
        if(!StringUtils.isEmpty(url)){
            return restTemplate.getForObject(url+ "/" +userId,User.class );
        }
        return null;
    }
}
