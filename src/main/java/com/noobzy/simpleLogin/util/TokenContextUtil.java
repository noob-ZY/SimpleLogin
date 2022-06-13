package com.noobzy.simpleLogin.util;

public class TokenContextUtil {


    private static final ThreadLocal<String> tlToken = new ThreadLocal<>();

    public static String getToken() {
        return tlToken.get();
    }

    public static void setToken(String token) {
        tlToken.set(token);
    }

    public static void removeToken() {
        tlToken.remove();
    }

}
