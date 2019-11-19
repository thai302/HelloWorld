package com.ecorau.vbnapi.core;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class VbnCoreApplication {

    public static void main(String[] args) {
        SpringApplication.run(VbnCoreApplication.class, args);
    }
}
