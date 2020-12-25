package com.epam.rd.java.basic.practice3;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Locale;

public final class Part4 {

    private Part4() {
    }

    public static String hash(final String input, final String algorithm) throws NoSuchAlgorithmException {
        MessageDigest digest = MessageDigest.getInstance(algorithm);
        digest.reset();
        final String encoding = "Cp1251";
        try {
            digest.update(input.getBytes(encoding));
        } catch (final UnsupportedEncodingException e) {
            throw new AssertionError("UTF-8 not supported");
        }
        final byte[] hash = digest.digest();
        final StringBuilder hexString = new StringBuilder();
        final StringBuilder hex = new StringBuilder();
        for (final byte var : hash) {
            final char count = 0xFF;
            hex.append(Integer.toHexString(count & var));
            if (hex.length() == 1) {
                hexString.append('0');
            }
            hexString.append(hex);
            hex.setLength(0);
        }
        hexString.toString();
        final String result = hexString.toString();
        return result.toUpperCase(Locale.ENGLISH);
    }
}
