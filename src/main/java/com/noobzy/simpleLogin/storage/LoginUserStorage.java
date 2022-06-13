package com.noobzy.simpleLogin.storage;

import com.noobzy.simpleLogin.entity.User;

public interface LoginUserStorage {

    User getLoginUser();

    boolean setLoginUser(User user);

    void removeLoginUser();

}
