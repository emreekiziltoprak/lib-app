package com.emrekiziltoprak.libraryapp.utils;

import java.util.regex.Pattern;

public final class EmailValidator {

    private static final Pattern EMAIL_PATTERN = Pattern.compile("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9._%+-]+\\.[A-Za-z]{2,}$");

    private EmailValidator() {}

    public static boolean isValid(String email) {
        if(email == null || email.isBlank()){
            return false;
        }

        return EMAIL_PATTERN.matcher(email.trim()).matches();
    }

}
