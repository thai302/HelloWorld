package com.kitcut.helloworld.springclouddiscoveryeurekaclient;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class SpringCloudDiscoveryEurekaClientApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringCloudDiscoveryEurekaClientApplication.class, args);
    }

}
