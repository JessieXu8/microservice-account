package com.ita.microserviceaccounteureka.microserviceaccounteureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication

public class MicroserviceAccountEurekaApplication {

    public static void main(String[] args) {
        SpringApplication.run(MicroserviceAccountEurekaApplication.class, args);
    }

}
