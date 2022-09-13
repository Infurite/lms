package com.temchik.lms.common.util;

import org.apache.commons.lang3.RandomStringUtils;

public final class StringUtils {

    public static String generateRandomString(int length) {
        return RandomStringUtils.randomAlphanumeric(length);
    }
}
