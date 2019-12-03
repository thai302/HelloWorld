package com.kitcut.helloworld.springkafka.controller;

import com.kitcut.helloworld.springkafka.dto.User;
import com.kitcut.helloworld.springkafka.dto.User1;
import com.kitcut.helloworld.springkafka.service.Sender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController {

    @Autowired
    private Sender sender;

    @GetMapping("")
    public void test() throws Exception{

        User user = new User();
        user.setId(1L);
        user.setUsername("thai");

        User1 user1 = new User1();
        user1.setId(1L);
        user1.setUsername("thai");

//        sender.send("test", user);
//        sender.send("test", user1);
        sender.send("test", "1");

    }
}
