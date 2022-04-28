package com.lab.lab9.utils;

import org.mindrot.jbcrypt.BCrypt;

import java.security.NoSuchAlgorithmException;

public class Hashing {
    public static String hashPassword(String password) throws NoSuchAlgorithmException {
        return BCrypt.hashpw(password, BCrypt.gensalt(12));
    }
    public static boolean checkPassword(String password, String passwordHash){
        if(passwordHash.length() < 60) return false;
        return BCrypt.checkpw(password, passwordHash);
    }
}
