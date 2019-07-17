package com.kitcut.helloworld.springboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Locale;

@Controller
public class WebController {

    @Autowired
    private MessageSource messageSource;

    @RequestMapping(value = "/index")
    public String index() {
        return "index";
    }

    @RequestMapping("/view-products")
    public String viewProducts() {
        return "view-products";
    }

    @RequestMapping("/add-products")
    public String addProducts() {
        return "add-products";
    }

    @RequestMapping("/locale")
    public String locale() {
        String msg = messageSource.getMessage("welcome.text", null, Locale.FRENCH);
        return "locale";
    }
}
