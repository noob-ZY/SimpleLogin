package com.noobzy.simpleLogin.autoConfig;

import com.noobzy.simpleLogin.aspect.RoleAspect;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Bean;

public class AopConfiguration {

    @Bean
    @ConditionalOnClass(Aspect.class)
    public RoleAspect roleAspect() {
        return new RoleAspect();
    }

}
