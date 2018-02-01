package com.javens.security;

import org.apache.commons.codec.binary.Hex;

import java.security.GeneralSecurityException;
import java.security.MessageDigest;
import java.security.SecureRandom;

public class PasswordEntrypt {

    private static final String SHA1 = "SHA-1";
    private static final String MD5 = "MD5";

    private static SecureRandom random = new SecureRandom();

    public static byte[] generateSalt(int numBytes) {
        if (numBytes <= 0) {
            throw new IllegalArgumentException("numBytes argument must be a positive integer (1 or larger)");
        }
        byte[] bytes = new byte[numBytes];
        random.nextBytes(bytes);
        return bytes;
    }

    public static String md5(byte[] plainPassword, byte[] salt) {
        return md5(plainPassword, salt, 1);
    }

    public static String md5(byte[] plainPassword, byte[] salt, int iterations) {
        return Hex.encodeHexString(digest(plainPassword, MD5, salt, iterations));
    }

    public static String sha1(byte[] plainPassword, byte[] salt) {
        return sha1(plainPassword, salt, 1);
    }

    public static String sha1(byte[] plainPassword, byte[] salt, int iterations) {
        return Hex.encodeHexString(digest(plainPassword, SHA1, salt, iterations));
    }

    private static byte[] digest(byte[] input, String algorithm, byte[] salt, int iterations) {
        try {
            MessageDigest digest = MessageDigest.getInstance(algorithm);

            if (salt != null) {
                digest.update(salt);
            }

            byte[] result = digest.digest(input);

            for (int i = 1; i < iterations; i++) {
                digest.reset();
                result = digest.digest(result);
            }
            return result;
        } catch (GeneralSecurityException e) {
            throw unchecked(e);
        }
    }

    /**
     * 将CheckedException转换为UncheckedException.
     */
    public static RuntimeException unchecked(Throwable ex) {
        if (ex instanceof RuntimeException) {
            return (RuntimeException) ex;
        } else {
            return new RuntimeException(ex);
        }
    }

}
