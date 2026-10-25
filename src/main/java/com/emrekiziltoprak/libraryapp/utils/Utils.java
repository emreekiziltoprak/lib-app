package com.emrekiziltoprak.libraryapp.utils;

import java.time.Year;

public final class Utils {

    private Utils() {
        throw new AssertionError("Utility class");
    }

    public static String requireNotBlank(String value, String field) {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException(field + " must not be blank");
        }
        return value;
    }

    public  static int requireValidYear(int year) {
        if (year > Year.now().getValue()) {
            throw new IllegalArgumentException("publishYear cannot be in the future");
        }
        return year;
    }
}
