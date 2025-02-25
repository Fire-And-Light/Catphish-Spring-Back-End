package com.example.demo.utils;

import java.math.BigInteger;
import java.security.SecureRandom;

public class Salt {
    public static String generate() {
        SecureRandom random = new SecureRandom();
        byte[] byteSalt = new byte[16];
        random.nextBytes(byteSalt);
        BigInteger num = new BigInteger(1, byteSalt);
        String stringSalt = num.toString(16);
        
        return stringSalt;
    }
}