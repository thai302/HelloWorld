package com.kitcut.config;

import com.kitcut.lang.impl.English;
import com.kitcut.lang.impl.Language;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan({"com.kitcut.bean", "com.kitcut.lang"})
public class AppConfiguration {
    @Bean(name = "language")
    public Language english() {
        return new English();
    }

//    @Bean
//    public Vietnamese vietnamese() {
//        return new Vietnamese();
//    }


}
