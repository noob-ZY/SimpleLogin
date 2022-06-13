package com.noobzy.simpleLogin.util;

import com.noobzy.simpleLogin.entity.User;

public class UserContextUtil{

    private static final ThreadLocal<User> tlInstance = new ThreadLocal<User>();

    public static User getUser() {
        return tlInstance.get();
    }

    public static void putUser(User user) {
        tlInstance.set(user);
    }

    public static void removeUser() {
        tlInstance.remove();
    }
}
