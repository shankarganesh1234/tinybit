package com.tinybit.service.hash;

import com.tinybit.constants.Constants;
import com.tinybit.util.HashUtil;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.Base64;
import java.util.Random;

public class HashServiceImpl implements HashService {

    @Override
    public String generateKey(String value) throws Exception {

        if(value == null)
            return null;

        MessageDigest digest = HashUtil.getDigest();

        if (digest == null)
            throw new Exception("Could not get digest");

        byte[] hash = digest.digest(value.getBytes(StandardCharsets.UTF_8));
        String hashStr = new String(Base64.getUrlEncoder().encode(hash));
        return getTruncatedHash(hashStr);
    }

    /**
     *
     * @param hashStr
     * @return
     */
    private String getTruncatedHash(String hashStr) {

        int len = hashStr.length();
        Random rand = new Random();
        StringBuilder sbr = new StringBuilder();
        String truncatedHash = null;

        for (int i = 0; i < Constants.KEY_LENGTH; i++) {
            sbr.append(hashStr.charAt(rand.nextInt(len)));
        }

        truncatedHash = sbr.toString();
        return truncatedHash.toLowerCase();
    }
}
