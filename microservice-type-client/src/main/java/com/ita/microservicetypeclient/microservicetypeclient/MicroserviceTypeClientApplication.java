package com.ita.microservicetypeclient.microservicetypeclient;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * It means it is a eureka client
 */
@EnableDiscoveryClient
@SpringBootApplication
public class MicroserviceTypeClientApplication {

    public static void main(String[] args) {
        SpringApplication.run(MicroserviceTypeClientApplication.class, args);
    }

}
