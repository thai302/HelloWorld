package com.kitcut.helloworld.baserestapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@RequestMapping("/api/test")
public class TestController {

    @Autowired
    private MessageSource messageSource;

    @GetMapping("/testDateTimeFormat")
    public Date testDateTimeFormat(@RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") Date date) {
        return date;
    }

    @PostMapping("/testLanguage")
    public Date testLanguage() {
        return new Date();
    }
}
