package com.andreasmlbngaol.utils;

import org.mindrot.jbcrypt.BCrypt;

public class PasswordManager {

    private static final int SALT_LENGTH = 12;

    public static String hashPassword(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt(SALT_LENGTH));
    }

    public static boolean verifyPassword(String password, String hashedPassword) {
        return BCrypt.checkpw(password, hashedPassword);
    }
}
