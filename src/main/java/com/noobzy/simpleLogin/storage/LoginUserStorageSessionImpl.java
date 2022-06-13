package com.noobzy.simpleLogin.storage;

import com.noobzy.simpleLogin.entity.User;
import com.noobzy.simpleLogin.util.HttpUtil;

public class LoginUserStorageSessionImpl implements LoginUserStorage{

    private static final String CURRENT_USER_IN_SESSION = "UserInfo";

    @Override
    public User getLoginUser() {
        return (User) HttpUtil.getRequest().getAttribute(CURRENT_USER_IN_SESSION);
    }

    @Override
    public boolean setLoginUser(User user) {
        HttpUtil.getSession().setAttribute(CURRENT_USER_IN_SESSION, user);
        return true;
    }

    @Override
    public void removeLoginUser() {
        HttpUtil.getSession().removeAttribute(CURRENT_USER_IN_SESSION);
    }
}
