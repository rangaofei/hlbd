package com.hanlinbode.hlbd.bean;

import com.google.gson.Gson;
import com.hanlinbode.hlbd.ConstData;
import com.hanlinbode.hlbd.util.JWTUtil;

import javax.json.JsonObject;

public class Token {
    private String accessToken;
    private String refreshToken;

    public Token() {
    }

    public Token(String object) {
        setAccessToken(JWTUtil.createJWT(object, ConstData.JWT_TTL));
        setRefreshToken(JWTUtil.createJWT(object,ConstData.JWT_REFRESH_TTL));

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
