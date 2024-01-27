package com.aadim.project.validator;


import java.util.regex.Pattern;

public class EmailValidator {

    private static final String EMAIL_PATTERN = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$";

    public static boolean isValid(String email) {
        return Pattern.matches(EMAIL_PATTERN, email);
    }
}