package com.kitcut.helloworld.springcloudloadbalanceribbon;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

//@RibbonClient(name = "ping-a-server", configuration = RibbonConfiguration.class)
@EnableEurekaClient
@SpringBootApplication
public class SpringCloudLoadBalanceRibbonApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringCloudLoadBalanceRibbonApplication.class, args);
    }

}
