package com.ecorau.vbnapi.auth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class VbnAuthApplication {

    public static void main(String[] args) {
        SpringApplication.run(VbnAuthApplication.class, args);
    }

}
