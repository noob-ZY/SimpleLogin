package com.noobzy.simpleLogin.util;

import com.noobzy.simpleLogin.entity.User;
import com.noobzy.simpleLogin.exception.NoUserLoginException;
import com.noobzy.simpleLogin.storage.LoginUserStorage;
import org.springframework.beans.factory.annotation.Autowired;

public class SPUtil{

    private static LoginUserStorage loginUserStorage;

    @Autowired
    private void setLoginUserStorage(LoginUserStorage loginUserStorage) {
        SPUtil.loginUserStorage = loginUserStorage;
    }

    /**
     * 调用前清楚隐私重要信息
     * @param user
     */
    public static void login(User user) {
        loginUserStorage.setLoginUser(user);
        UserContextUtil.putUser(user);
    }

    public static void logout() {
        loginUserStorage.removeLoginUser();
        UserContextUtil.removeUser();
    }

    /**
     * 获取当前登录的用户
     * @return
     * @throws NoUserLoginException 无有效用户登录
     */
    public static User getCurrentUser() throws NoUserLoginException {
        User user = UserContextUtil.getUser();
        if (user == null) {
            throw new NoUserLoginException();
        }
        return user;
    }

    /**
     * 获取当前登录用户的token
     * @return
     * @throws NoUserLoginException
     */
    public static String getToken() throws NoUserLoginException {
        String token = TokenContextUtil.getToken();
        if (token == null || token.isEmpty() || token.isBlank()) {
            throw new NoUserLoginException();
        }
        return token;
    }
}
