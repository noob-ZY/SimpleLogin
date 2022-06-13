package com.noobzy.simpleLogin.autoConfig;

import com.noobzy.simpleLogin.filter.UserInfoHandleFilter;
import com.noobzy.simpleLogin.filter.UserInfoHandleFilterCookieTokenImpl;
import com.noobzy.simpleLogin.filter.UserInfoHandleFilterSessionImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.web.servlet.ConditionalOnMissingFilterBean;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;

public class FilterConfiguration {

    @Autowired
    private UserInfoHandleFilter userInfoHandleFilter;

    @Bean
    @ConditionalOnMissingFilterBean(UserInfoHandleFilter.class)
    @ConditionalOnProperty(prefix = "simpleLogin", name = "storageType", havingValue = "session", matchIfMissing = true)
    public UserInfoHandleFilter userInfoHandleFilterSessionImpl() {
        return new UserInfoHandleFilterSessionImpl();
    }

    @Bean
    @ConditionalOnMissingFilterBean(UserInfoHandleFilter.class)
    @ConditionalOnProperty(prefix = "simpleLogin", name = "storageType", havingValue = "cookieToken")
    public UserInfoHandleFilter userInfoHandleFilterCookieTokenImpl() {
        return new UserInfoHandleFilterCookieTokenImpl();
    }

    @Bean
    public FilterRegistrationBean Filter(){
        FilterRegistrationBean filterRegistrationBean=new FilterRegistrationBean();
        filterRegistrationBean.setFilter(userInfoHandleFilter);//设置过滤器
        filterRegistrationBean.addUrlPatterns("/*");//配置过滤规则
        filterRegistrationBean.setOrder(999); //order的数值越小 则优先级越高
        return filterRegistrationBean;
    }

}
