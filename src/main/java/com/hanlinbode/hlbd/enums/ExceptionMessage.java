package com.hanlinbode.hlbd.enums;

public enum ExceptionMessage {
    RESULTNOTFOUND(410,"班级未找到");
    private int code;
    private String msg;

    ExceptionMessage(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
