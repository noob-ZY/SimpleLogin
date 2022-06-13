package com.noobzy.simpleLogin.autoConfig;

import com.noobzy.simpleLogin.entity.User;
import com.noobzy.simpleLogin.storage.LoginUserStorage;
import com.noobzy.simpleLogin.storage.LoginUserStorageCookieTokenImpl;
import com.noobzy.simpleLogin.storage.LoginUserStorageSessionImpl;
import com.noobzy.simpleLogin.util.JWTUtil;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;

public class LoginUserStorageConfiguration {

    /**
     * 默认使用session
     * @return
     */
    @Bean
    @ConditionalOnProperty(prefix = "simpleLogin", name = "storageType", havingValue = "session", matchIfMissing = true)
    public LoginUserStorage sessionImpl() {
        return new LoginUserStorageSessionImpl();
    }

    /**
     * cookieToken
     * @return
     */
    @Bean
    @ConditionalOnProperty(prefix = "simpleLogin", name = "storageType", havingValue = "cookieToken")
    public LoginUserStorage cookieTokenImpl() {
        return new LoginUserStorageCookieTokenImpl();
    }

    @Bean
    @ConditionalOnMissingBean(JWTUtil.class)
    public JWTUtil jwtUtil() {
        return new JWTUtil<User>(){};
    }

}
