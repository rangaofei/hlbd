package com.hanlinbode.hlbd.util;

public class QuestionUtil {
    public static boolean isForCorrect(int questionTypeId) {
        switch (questionTypeId) {
            case 2:
            case 3:
            case 4:
            case 5:
            case 6:
            case 7:
            case 39:
            case 66:
            case 78:
                return true;
            default:
                return false;
        }
    }
}
