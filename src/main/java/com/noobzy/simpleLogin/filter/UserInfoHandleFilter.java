package com.noobzy.simpleLogin.filter;

import com.noobzy.simpleLogin.entity.User;
import com.noobzy.simpleLogin.storage.LoginUserStorage;
import com.noobzy.simpleLogin.util.UserContextUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

public abstract class UserInfoHandleFilter implements Filter {

    @Autowired
    private LoginUserStorage loginUserStorage;

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        //获取用户信息
        User currentUserInfo = getUser();

        //放入用户信息上下文
        setCurrentUser(currentUserInfo);

        //doFilter
        try {
            filterChain.doFilter(servletRequest, servletResponse);
        } finally {
            //移除上下文当前用户信息
            removeCurrentUser();
        }

    }

    protected User getUser() {
        return loginUserStorage.getLoginUser();
    }

    protected void setCurrentUser(User user) {
        UserContextUtil.putUser(user);
    }

    protected void removeCurrentUser() {
        UserContextUtil.removeUser();
    }
}
