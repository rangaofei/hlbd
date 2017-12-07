package com.hanlinbode.hlbd.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexUtil {
    public static boolean isMobile(final String str) {
        Pattern p = Pattern.compile("^[1][3-8][0-9]{9}$"); // 验证手机号
        Matcher m = p.matcher(str);
        return m.matches();
    }
}
