package com.hanlinbode.hlbd.composbean;

import com.hanlinbode.hlbd.util.ConstData;
import com.hanlinbode.hlbd.util.JWTUtil;

public class Token {
    private String accessToken;
    private String refreshToken;

    public Token() {
    }

    public Token(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public static Token generateToken(String name) {
        Token token = new Token();
        token.setAccessToken(JWTUtil.createJWT(name, ConstData.JWT_TTL));
        token.setRefreshToken(JWTUtil.createJWT(name, ConstData.JWT_REFRESH_TTL));
        return token;
    }

    @Override
    public String toString() {
        return "Token{" +
                "accessToken='" + accessToken + '\'' +
                ", refreshToken='" + refreshToken + '\'' +
                '}';
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }
}
