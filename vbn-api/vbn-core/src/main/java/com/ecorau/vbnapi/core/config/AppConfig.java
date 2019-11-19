package com.ecorau.vbnapi.core.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;

@Component
public class AppConfig implements WebMvcConfigurer {
    @Value("${cors.origin}")
    private String corsOrigin;

    @Autowired
    private LocaleChangeInterceptor localeChangeInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(localeChangeInterceptor)
                .excludePathPatterns("/swagger**", "/webjars/springfox-swagger-ui/**");
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping(corsOrigin);
    }
}
