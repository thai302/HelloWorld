package com.kitcut.helloworld.baserestapi.controller;

import com.kitcut.helloworld.baserestapi.config.LocaleResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.PropertySource;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@RestController
@RequestMapping("/api/test")
@PropertySource({
        "classpath:messages.properties",
//        "classpath:swagger.properties"
})
public class TestController {

    @Value("${test}")
    private String a;

    @Value("${employee.name}")
    private String b;

    @Autowired
    private MessageSource messageSource;

    @GetMapping("/testDateTimeFormat")
    public Date testDateTimeFormat(@RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") Date date) {
        return date;
    }

    @Autowired
    private LocaleResolver localeResolver;

    @PostMapping("/testLanguage")
    public Date testLanguage(HttpServletRequest request) {
        return new Date();
//        messageSource.getMessage("test", null, LocaleContextHolder.getLocale())
    }
}
