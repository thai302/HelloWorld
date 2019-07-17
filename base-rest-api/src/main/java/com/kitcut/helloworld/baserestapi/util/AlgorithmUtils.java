package com.kitcut.helloworld.baserestapi.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

public class AlgorithmUtils {
    public static String generateUniqueToken() {
        SecureRandom rand = new SecureRandom();
        return getSHA2(rand.nextInt() + String.valueOf(System.currentTimeMillis()));
    }

    public static String getSHA2(String data) {
        return getDigest(data, "SHA-256");
    }

    private static String getDigest(String data, String algorithm) {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance(algorithm);

            messageDigest.update(data.getBytes());
            byte[] digest = messageDigest.digest();
            StringBuffer sb = new StringBuffer();
            for (byte b : digest) {
                sb.append(Integer.toHexString(b & 0xff));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException ex) {
            throw new RuntimeException(ex);
        }
    }
}
