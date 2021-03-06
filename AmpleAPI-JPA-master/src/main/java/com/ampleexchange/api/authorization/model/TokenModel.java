package com.ampleexchange.api.authorization.model;

/**
 * @author JL
 * @date 2018/10/29.
 */
public class TokenModel {

    private long userId;

    private String token;

    public TokenModel(long userId, String token) {
        this.userId = userId;
        this.token = token;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
