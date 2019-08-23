package com.kitcut.bean;

import com.kitcut.lang.impl.Language;
import com.kitcut.lang.impl.Vietnamese;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GreetingService {

    @Autowired
    private Language language;

    @Autowired
    private Vietnamese vietnamese;

    private int a;

    public void setA(int a) {
        this.a = a;
    }

    public GreetingService() {
        this.a = 1;
    }

    public void sayGreeting() {

        String greeting = language.getGreeting();

        System.out.println("Greeting: " + greeting);
    }

}