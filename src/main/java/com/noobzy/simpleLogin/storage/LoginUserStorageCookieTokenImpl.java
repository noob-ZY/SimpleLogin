package com.noobzy.simpleLogin.storage;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.noobzy.simpleLogin.entity.User;
import com.noobzy.simpleLogin.util.HttpUtil;
import com.noobzy.simpleLogin.util.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.Cookie;
import java.util.Arrays;
import java.util.Optional;

public class LoginUserStorageCookieTokenImpl implements LoginUserStorage{

    @Autowired
    private JWTUtil jwtUtil;

    public static final String TOKEN = "token";

    @Override
    public User getLoginUser() {
        //获取cookie
        String token = HttpUtil.getCookieFromRequest(HttpUtil.getRequest(),  TOKEN);
        if (token == null || token.isEmpty() || token.isBlank()) {
            return null;
        }
        return jwtUtil.analyzeToken(token);
    }

    @Override
    public boolean setLoginUser(User user) {
        try {
            //创建token
            String token = jwtUtil.createToken(user);
            //创建cookie
            Cookie tokenCookie = new Cookie(TOKEN, token);
            tokenCookie.setPath("/");
            tokenCookie.setMaxAge(24 * 60 * 60);
            //添加cookie
            HttpUtil.getResponse().addCookie(tokenCookie);
            return true;
        } catch (JsonProcessingException e) {
            //TODO log
            //e.printStackTrace();
            return false;
        }
    }

    @Override
    public void removeLoginUser() {
        Cookie tokenCookie = new Cookie(TOKEN, null);
        tokenCookie.setPath("/");
        tokenCookie.setMaxAge(0);
        HttpUtil.getResponse().addCookie(tokenCookie);
    }
}
