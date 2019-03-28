package com.ita.microserviceaccountclient.microserviceaccountclient.service;

import com.ita.microserviceaccountclient.microserviceaccountclient.entity.Type;
import com.ita.microserviceaccountclient.microserviceaccountclient.entity.User;
import com.ita.microserviceaccountclient.microserviceaccountclient.util.ClientUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;

@Service
public class TypeService {
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private DiscoveryClient discoveryClient;
    public ResponseEntity<ResponseEntity> save(Object type) {
        String url = ClientUtil.getUrl(discoveryClient.getInstances("micro-service-type-client"));
        if(!StringUtils.isEmpty(url)){
            return restTemplate.postForEntity(url,type,ResponseEntity.class);
        }
        return null;
    }

    public Type findTypeByTypeName(Type type) {
        String url = ClientUtil.getUrl(discoveryClient.getInstances("micro-service-type-client"));
        if(!StringUtils.isEmpty(url)){
            return restTemplate.getForObject(url + "/type?name=" + type.getType(),Type.class);
        }
        return null;
    }
}
