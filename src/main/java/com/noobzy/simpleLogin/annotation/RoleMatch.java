package com.noobzy.simpleLogin.annotation;

import org.springframework.core.annotation.AliasFor;

import java.lang.annotation.*;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.ANNOTATION_TYPE, ElementType.METHOD})
public @interface RoleMatch {

    /**
     * true 用户至少有一个对应角色
     * false 用户不能拥有注解所对应的角色
     * @return
     */
    boolean match() default true;

    @AliasFor("roles")
    String[] value() default {};

    @AliasFor("value")
    String[] roles() default {};

}
