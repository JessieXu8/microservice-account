package com.ita.microserviceaccountclient.microserviceaccountclient.service;


import com.ita.microserviceaccountclient.microserviceaccountclient.util.CacheUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.stereotype.Service;

@Service
public class LoginService {
    @Autowired
    private DiscoveryClient discoveryClient;
    public String getOpenId(String loginStatus) {
        return CacheUtil.Instance.getOpenId(loginStatus);
    }
}
