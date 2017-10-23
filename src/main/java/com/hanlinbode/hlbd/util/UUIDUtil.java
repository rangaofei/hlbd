package com.hanlinbode.hlbd.util;

import java.util.Random;
import java.util.UUID;

public class UUIDUtil {


    public static String generateId() {
        return String.valueOf(System.currentTimeMillis()) + new Random().nextInt(10);
    }

    public static String generatedTeamId() {
        return String.valueOf(System.currentTimeMillis()).substring(4) + new Random().nextInt(10);

    }
}
