package com.tinybit.util;

import java.security.MessageDigest;

public class HashUtil {

    public static MessageDigest getDigest() {
        try {
            return MessageDigest.getInstance("SHA-256");
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
