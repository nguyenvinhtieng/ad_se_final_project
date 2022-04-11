package com.lab.lab9.utils;

import java.nio.charset.Charset;
import java.util.Random;

public class GenerateId {
    public static String getRandomString(int length) {
        String AlphaNumericString
                = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
                + "0123456789"
                + "abcdefghijklmnopqrstuvxyz";
        StringBuilder sb = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            int index = (int)(AlphaNumericString.length() * Math.random());
            sb.append(AlphaNumericString.charAt(index));
        }
        return sb.toString();
    }
}
