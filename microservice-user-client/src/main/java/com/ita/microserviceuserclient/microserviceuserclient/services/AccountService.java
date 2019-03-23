package com.ita.microserviceuserclient.microserviceuserclient.services;

import com.ita.microserviceuserclient.microserviceuserclient.entities.Account;
import com.netflix.appinfo.InstanceInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@Service
public class AccountService {
    @Autowired RestTemplate restTemplate;

    @Autowired DiscoveryClient discoveryClient;

    public List<Account> getAllUndeletedAccounts() {
        List<ServiceInstance> instances = discoveryClient.getInstances("micro-service-account-client");
        if (null == instances || instances.isEmpty()){
            return null;
        }
        //从服务列表中获取服务
        ServiceInstance serviceInstance = instances.get(0);
        //拿到这个service instance之后就可以调用它里面的方法
        //serviceInstance.getHost();拿到主机名
        //serviceInstance.getPort();拿到端口号
        String url = "http://" + serviceInstance.getHost() + ":" + serviceInstance.getPort();
        Account[] accounts = restTemplate.getForObject(url, Account[].class);
        return Arrays.asList(accounts);
    }
}
