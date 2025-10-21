package com.stc.studentcourse.util;

import java.util.Random;
import java.util.regex.Pattern;

public class Utils {

    private static final Random random = new Random();

    public static String generateUniversityId() {
        long num = (long)(random.nextDouble() * 1_000_000_0000L);
        return String.format("%010d", num);
    }

    public static boolean isValidMobile(String mobile) {
        if (mobile == null) return false;
        return Pattern.matches("00966\\d{8}", mobile) || Pattern.matches("05\\d{8}", mobile);
    }

    // Validate course code format: 2 letters + 3 digits
    public static boolean isValidCourseCode(String code) {
        if (code == null) return false;
        return Pattern.matches("[A-Za-z]{2}\\d{3}", code);
    }

    public static boolean isValidTitle(String title) {
        return "Associate professor".equalsIgnoreCase(title)
                || "Assistant professor".equalsIgnoreCase(title)
                || "Lecturer".equalsIgnoreCase(title);
    }
}
