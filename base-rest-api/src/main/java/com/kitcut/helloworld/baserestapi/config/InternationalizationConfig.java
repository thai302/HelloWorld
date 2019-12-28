package com.kitcut.helloworld.baserestapi.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource({
        "classpath:swagger_vi.properties",
        "classpath:validate_vi.properties"
})
public class InternationalizationConfig {

    @Value("${language}")
    private String language;

//    @Bean
//    public LocaleResolver localeResolver() {
//        return new AcceptHeaderLocaleResolver();
////        SessionLocaleResolver sessionLocaleResolver = new SessionLocaleResolver();
////        sessionLocaleResolver.setDefaultLocale(new Locale(language));
////        return sessionLocaleResolver;
//    }

//    @Bean
//    public LocaleChangeInterceptor localeChangeInterceptor() {
//        LocaleChangeInterceptor localeChangeInterceptor = new LocaleChangeInterceptor();
//        localeChangeInterceptor.setParamName("language");
//        return localeChangeInterceptor;
//    }
}
