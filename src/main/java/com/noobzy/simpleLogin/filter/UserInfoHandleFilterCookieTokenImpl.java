package com.noobzy.simpleLogin.filter;

import com.noobzy.simpleLogin.entity.User;
import com.noobzy.simpleLogin.storage.LoginUserStorageCookieTokenImpl;
import com.noobzy.simpleLogin.util.HttpUtil;
import com.noobzy.simpleLogin.util.TokenContextUtil;

public class UserInfoHandleFilterCookieTokenImpl extends UserInfoHandleFilter{

    @Override
    protected void setCurrentUser(User user) {
        super.setCurrentUser(user);
        String token = HttpUtil.getCookieFromRequest(HttpUtil.getRequest(), LoginUserStorageCookieTokenImpl.TOKEN);
        TokenContextUtil.setToken(token);
    }

    @Override
    protected void removeCurrentUser() {
        super.removeCurrentUser();
        TokenContextUtil.removeToken();
    }
}
