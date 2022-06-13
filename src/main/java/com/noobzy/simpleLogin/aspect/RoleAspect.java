package com.noobzy.simpleLogin.aspect;

import com.noobzy.simpleLogin.annotation.RoleMatch;
import com.noobzy.simpleLogin.entity.User;
import com.noobzy.simpleLogin.exception.AuthorityException;
import com.noobzy.simpleLogin.util.UserContextUtil;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;

import java.util.Set;

@Aspect
public class RoleAspect {

    @Pointcut("@annotation(com.noobzy.simpleLogin.annotation.RoleMatch)")
    public void pointCut(){};

    @Before("pointCut()")
    public void doBefore(JoinPoint joinPoint) {
        //注解信息
        RoleMatch roleMatch = ((MethodSignature) joinPoint.getSignature()).getMethod().getAnnotation(RoleMatch.class);
        boolean match = roleMatch.match();
        String[] roles = roleMatch.roles();
        //用户信息
        User currentUser = UserContextUtil.getUser();
        Set<String> userRoles = currentUser == null ? null : currentUser.getUserRoles();

        if (match) {

            if (userRoles == null) {
                throw new AuthorityException();
            }
            for (String aRole : roles) {
                if (userRoles.contains(aRole)) {
                    return;
                }
            }

        } else {

            if (userRoles == null) {
                return;
            }

            for (String aRole : roles) {
                if (userRoles.contains(aRole)) {
                    throw new AuthorityException();
                }
            }

        }
    }

}
