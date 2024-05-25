package com.televideocom.videoteca.security;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

public class CustomPasswordEncoder {
    private static final int STRENGTH = 10;

    public static String encode(CharSequence rawPassword) {
        byte[] salt = generateSalt();
        byte[] hash = hash(rawPassword, salt);
        return combineSaltAndHash(salt, hash);
    }

    public static boolean matches(CharSequence rawPassword, String encodedPassword) {
        String[] parts = encodedPassword.split(":");
        byte[] salt = decodeHex(parts[0]);
        byte[] expectedHash = decodeHex(parts[1]);

        byte[] actualHash = hash(rawPassword, salt);
        return slowEquals(actualHash, expectedHash);
    }

    private static byte[] generateSalt() {
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[16];
        random.nextBytes(salt);
        return salt;
    }

    private static byte[] hash(CharSequence rawPassword, byte[] salt) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-512");
            md.update(salt);
            byte[] hashedPassword = md.digest(rawPassword.toString().getBytes());
            return hashedPassword;
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Error while hashing password", e);
        }
    }

    private static String combineSaltAndHash(byte[] salt, byte[] hash) {
        return toHex(salt) + ":" + toHex(hash);
    }

    private static byte[] decodeHex(String hex) {
        int len = hex.length();
        byte[] result = new byte[len / 2];
        for (int i = 0; i < len; i += 2) {
            result[i / 2] = (byte) ((Character.digit(hex.charAt(i), 16) << 4)
                    + Character.digit(hex.charAt(i + 1), 16));
        }
        return result;
    }

    private static String toHex(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (byte b : bytes) {
            sb.append(String.format("%02x", b));
        }
        return sb.toString();
    }

    private static boolean slowEquals(byte[] a, byte[] b) {
        int diff = a.length ^ b.length;
        for (int i = 0; i < a.length && i < b.length; i++) {
            diff |= a[i] ^ b[i];
        }
        return diff == 0;
    }
}



