package com.noobzy.simpleLogin.autoConfig;

import com.noobzy.simpleLogin.util.SPUtil;
import org.springframework.context.annotation.Bean;

public class MainConfiguration {

    @Bean
    public SPUtil spUtil() {
        return new SPUtil();
    }

}
