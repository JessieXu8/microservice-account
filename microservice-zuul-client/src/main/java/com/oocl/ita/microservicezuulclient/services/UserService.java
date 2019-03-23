package com.oocl.ita.microservicezuulclient.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service("userService")
public class UserService {
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private DiscoveryClient discoveryClient;


    public void createIfNotExist(String openId) {
        //指定商品微服务的名称,返回服务列表
        List<ServiceInstance> instances = discoveryClient.getInstances("micro-service-user-client");
        if (null == instances || instances.isEmpty()){
            return ;
        }
        //从服务列表中获取服务
        ServiceInstance serviceInstance = instances.get(0);
        //拿到这个service instance之后就可以调用它里面的方法
        //serviceInstance.getHost();拿到主机名
        //serviceInstance.getPort();拿到端口号
        String url = "http://" + serviceInstance.getHost() + ":" + serviceInstance.getPort();
        restTemplate.patchForObject(url + "/users/" + openId, null, ResponseEntity.class);
    }
}
