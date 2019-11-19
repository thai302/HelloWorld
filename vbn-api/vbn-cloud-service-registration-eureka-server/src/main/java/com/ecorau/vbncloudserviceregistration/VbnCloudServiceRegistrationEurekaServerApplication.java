package com.ecorau.vbncloudserviceregistration;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication
public class VbnCloudServiceRegistrationEurekaServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(VbnCloudServiceRegistrationEurekaServerApplication.class, args);
    }

}
