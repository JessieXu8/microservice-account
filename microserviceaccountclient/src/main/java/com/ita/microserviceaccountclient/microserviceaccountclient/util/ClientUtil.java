package com.ita.microserviceaccountclient.microserviceaccountclient.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;

import java.util.List;

public class ClientUtil {
    @Autowired
    private DiscoveryClient discoveryClient;

    public static String getUrl(List<ServiceInstance> instances){
        if (null == instances || instances.isEmpty()){
            return null;
        }
        //从服务列表中获取服务
        ServiceInstance serviceInstance = instances.get(0);
        //拿到这个service instance之后就可以调用它里面的方法
        //serviceInstance.getHost();拿到主机名
        //serviceInstance.getPort();拿到端口号
        return  "http://" + serviceInstance.getHost() + ":" + serviceInstance.getPort();
    }
}
