package com.ecorau.vbnapi.core.service.impl;

import com.ecorau.vbnapi.core.exception.BadRequestException;
import com.ecorau.vbnapi.core.service.AuthenService;
import com.ecorau.vbnapi.core.service.TokenService;
import com.ecorau.vbnapi.core.service.UserService;
import com.ecorau.vbnapi.core.bean.UserSession;
import com.ecorau.vbnapi.core.dto.request.authen.LoginRequest;
import com.ecorau.vbnapi.core.entity.TokenEntity;
import com.ecorau.vbnapi.core.entity.UserEntity;
import com.ecorau.vbnapi.core.util.AlgorithmUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service
@Transactional
public class AuthenServiceImpl implements AuthenService {

    @Autowired
    private UserSession userSession;

    @Autowired
    private UserService userService;

    @Autowired
    private TokenService tokenService;

    @Override
    public String login(LoginRequest loginRequest) {
        UserEntity userEntity = userService.findByName(loginRequest.getUserName());
        if (userEntity == null) {
            throw new BadRequestException("User is not exist");
        } else {
            if (!userEntity.getPassword().equals(loginRequest.getPassword())) {
                throw new BadRequestException("Password is not correct");
            } else {
                TokenEntity tokenEntity = new TokenEntity();
                tokenEntity.setUserId(userEntity.getId());
                tokenEntity.setToken(AlgorithmUtils.generateUniqueToken());
                tokenEntity.setExpiredTime(DateUtils.addMinutes(new Date(), 20));
                tokenService.save(tokenEntity);

                return tokenEntity.getToken();
            }
        }
    }

    @Override
    public void logout() {
        TokenEntity tokenEntity = userSession.getTokenEntity();
        tokenEntity.setExpiredTime(new Date());
        tokenService.save(tokenEntity);
    }
}
