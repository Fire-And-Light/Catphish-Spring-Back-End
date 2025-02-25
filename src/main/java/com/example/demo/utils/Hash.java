package com.example.demo.utils;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Hash {
    public static String SHA384toString(String input) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-384");
            byte[] messageDigest = md.digest(input.getBytes());
            BigInteger num = new BigInteger(1, messageDigest);
            String hash = num.toString(16);

            return hash;

        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }
}