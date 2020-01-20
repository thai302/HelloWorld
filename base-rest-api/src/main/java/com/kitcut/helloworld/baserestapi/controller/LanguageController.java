package com.kitcut.helloworld.baserestapi.controller;

import com.kitcut.helloworld.baserestapi.config.LocaleResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@RestController
@RequestMapping("/api/language")
@PropertySource({
//        "classpath:messages.properties",
        "classpath:swagger.properties",
        "classpath:validate.properties",
//        "file:${app.home}",
//        "file:${app.home}/messages_vi.properties",
})
public class LanguageController {

//    @Value("${test}")
//    private String a;

    @Value("${my.secret}")
    private String b;

//    @Value("${employee.name}")
//    private String c;

    @Autowired
    private MessageSource messageSource;

    @Autowired
    private LocaleResolver localeResolver;

    @Autowired
    private Environment env;

    @GetMapping("/testLanguage")
    public Date testLanguage(HttpServletRequest request) {
        return new Date();
//        messageSource.getMessage("test", null, LocaleContextHolder.getLocale())
    }
}
