package com.noobzy.simpleLogin.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.noobzy.simpleLogin.entity.User;
import com.noobzy.simpleLogin.exception.NoUserLoginException;
import com.noobzy.simpleLogin.exception.UserExpiredException;
import org.springframework.beans.factory.annotation.Value;

import java.lang.reflect.ParameterizedType;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

public abstract class JWTUtil<E extends User> {

    @Value("${simpleLogin.JWT.Secret}")
    private String JWTSecret;

    @Value("${simpleLogin.JWT.ISSUER}")
    private String ISSUER;

    private static final String TOKEN_USERID = "UserId";

    private static final String TOKEN_USERNAME = "UserName";

    public String createToken(E user) throws JsonProcessingException {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime expireDateTime = now.plusDays(1);
        ObjectMapper mapper = new ObjectMapper();
        String userStr = mapper.writeValueAsString(user);
        String token = JWT.create()
                .withIssuer(ISSUER)
                .withIssuedAt(Date.from(now.atZone(ZoneId.systemDefault()).toInstant()))
                .withExpiresAt(Date.from(expireDateTime.atZone(ZoneId.systemDefault()).toInstant()))
                .withAudience(userStr)
                .sign(Algorithm.HMAC256(JWTSecret));
        return token;
    }

    public E analyzeToken(String token){
        try {
            if (token == null || token.isEmpty() || token.isBlank()) {
                throw new NoUserLoginException();
            }

            JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256(JWTSecret)).build();

            DecodedJWT decodedJWT = jwtVerifier.verify(token);

            //获取Token中用户信息
            String userStr = decodedJWT.getAudience().get(0);

            //Json 转型
            ObjectMapper mapper = new ObjectMapper();
            E user = mapper.readValue(userStr, getActualType());

            return user;
        } catch (TokenExpiredException e) {
            throw new UserExpiredException();
        } catch (JsonProcessingException e) {
            throw new RuntimeException("JsonProcessingException");
        }
    }

    public Class<E> getActualType() {
        try {
            ParameterizedType genericSuperclass = (ParameterizedType) this.getClass().getGenericSuperclass();
            Class actualTypeArgument = (Class) genericSuperclass.getActualTypeArguments()[0];
            return actualTypeArgument;
        } catch (Exception e) {
            return (Class<E>) User.class;
        }
    }

}
