package com.hanlinbode.hlbd;

public class ConstData {
    public static final int GET_SUCCESS = 200;
    public static final int POST_SUCCESS = 201;
    public static final int NO_RESULT = 414;
    public static final int WRONG_PARAM = 415;


    public static final long JWT_TTL = 7 * 24 * 60 * 60 * 1000;  //millisecond
    public static final long JWT_REFRESH_INTERVAL = 55 * 60 * 1000;  //millisecond
    public static final long JWT_REFRESH_TTL = 30 * JWT_TTL;  //millisecond

}
