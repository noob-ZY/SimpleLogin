package com.noobzy.simpleLogin.entity;

import lombok.Data;

import java.util.Set;

@Data
public class User {

    private String userId;

    private String username;

    private String password;

    private Set<String> userRoles;


}
