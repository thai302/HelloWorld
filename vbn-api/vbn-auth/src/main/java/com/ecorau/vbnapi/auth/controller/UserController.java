package com.ecorau.vbnapi.auth.controller;

import com.ecorau.vbnapi.auth.service.UserDetailsServiceImpl;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {
    @RequestMapping(value = "/change-password", method = RequestMethod.POST)
    public void home(@RequestParam String password) {
        UserDetailsServiceImpl.AppUser appUser = UserDetailsServiceImpl.users.get(1);
        appUser.setPassword(password);
    }
}
