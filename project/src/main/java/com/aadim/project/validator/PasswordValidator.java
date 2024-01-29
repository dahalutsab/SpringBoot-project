package com.aadim.project.validator;

import java.util.regex.Pattern;

public class PasswordValidator {

    private static final String PASSWORD_PATTERN = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*#?&])[A-Za-z\\d@$!%*#?&]{8,}$";

    public static boolean isValid(String password) {
        return Pattern.matches(PASSWORD_PATTERN, password);
    }
}
