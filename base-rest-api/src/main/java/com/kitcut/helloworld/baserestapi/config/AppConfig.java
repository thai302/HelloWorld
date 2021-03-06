package com.kitcut.helloworld.baserestapi.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Component
public class AppConfig implements WebMvcConfigurer {
    @Value("${cors.origin}")
    private String corsOrigin;

    @Autowired
    private Interceptor interceptor;

//    @Autowired
//    private LocaleChangeInterceptor localeChangeInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
//        registry.addInterceptor(localeChangeInterceptor);
        registry.addInterceptor(interceptor)
                .excludePathPatterns("/swagger**", "/webjars/springfox-swagger-ui/**");
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping(corsOrigin);
    }
}
