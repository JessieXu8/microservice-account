package com.ita.microserviceaccountclient.microserviceaccountclient.util;

import org.springframework.web.client.RestTemplate;

public class RestRequestUtil {
    public static String get(String url) {
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject(url, String.class);
    }
}
