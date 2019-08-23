package com.kitcut.lang.impl;

import org.springframework.stereotype.Component;

// Tiếng anh
@Component
public class English  implements Language {

    public String getGreeting() {
        return "Hello";
    }

    public String getBye() {
        return "Bye bye";
    }
}
