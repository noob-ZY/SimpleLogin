package com.noobzy.simpleLogin.util;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Arrays;
import java.util.Optional;

public class HttpUtil {

    public static HttpServletRequest getRequest() {
        return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
    }

    public static HttpServletResponse getResponse() {
        return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getResponse();
    }

    public static HttpSession getSession() {
        return getRequest().getSession();
    }

    public static String getCookieFromRequest(HttpServletRequest request, String key) {
        //获取cookie
        String value = null;
        Cookie[] cookies = request.getCookies();

        if (cookies == null) {
            return null;
        }

        Optional<String> tokenOptional = Arrays.stream(cookies)
                .filter(cookie -> key.equals(cookie.getName()))
                .findFirst()
                .map(Cookie::getValue);
        if (tokenOptional.isPresent()) {
            value = tokenOptional.get();
        }
        return value;
    }

}
