package com.hanlinbode.hlbd.util;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

public class UUIDUtil {
    private static AtomicLong atomicLong=new AtomicLong();

    public static String generateId() {
        return String.valueOf(System.currentTimeMillis()) + atomicLong.getAndIncrement();
    }

    public static String generatedTeamId() {
        return String.valueOf(System.currentTimeMillis()).substring(4) + atomicLong.getAndIncrement();
    }
}
