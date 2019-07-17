package com.kitcut.helloworld.springboot.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Component
public class AppConfig extends WebMvcConfigurerAdapter {

    @Autowired
    private HelloInterceptor helloInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(helloInterceptor);
    }
}
