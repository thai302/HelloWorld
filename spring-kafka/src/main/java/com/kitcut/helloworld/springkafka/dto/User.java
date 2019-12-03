package com.kitcut.helloworld.springkafka.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class User implements Serializable {
    private Long id;
    private String username;
    private String password;
}
