package com.dichotomy.google.auth.util;

import java.util.UUID;

import org.apache.commons.lang3.RandomStringUtils;

public final class RandomUtil {

    private static final int APP_KEY_COUNT = 15;
    private static final int PRIMARY_KEY_COUNT = 9;
    private static final int PASSWORD_COUNT = 16;

    private RandomUtil() {
    }

    public static String generatePassword() {
        return RandomStringUtils.randomAlphanumeric(PASSWORD_COUNT);
    }

    public static String generateToken() {
        return RandomStringUtils.randomNumeric(8);
    }

    public static String generatePrimaryKey() {
        return String.format("%s%s", RandomStringUtils.randomAlphabetic(1).toUpperCase(), RandomStringUtils.randomAlphanumeric(PRIMARY_KEY_COUNT).toUpperCase());
    }

    public static String generateAppKey() {
        return String.format("%s%s", RandomStringUtils.randomAlphabetic(1).toLowerCase(), RandomStringUtils.randomAlphanumeric(APP_KEY_COUNT).toLowerCase());
    }

    public static String generateUUIDKey() {
        return UUID.randomUUID().toString();
    }

    public static String generateResourceContentKey() {
        return UUID.randomUUID().toString().replace("-", "");
    }

    public static String generatePrimaryKeyWithPrefix(String prefix) {
        return String.format("%s%s", prefix.toUpperCase(), RandomStringUtils.randomAlphanumeric(PRIMARY_KEY_COUNT).toUpperCase());
    }
}
