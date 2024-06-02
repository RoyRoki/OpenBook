package com.openbook.util;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.util.Arrays;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

public class passwords {
	 private static final SecureRandom RANDOM = new SecureRandom();
	    private static final int ITERATION = 10000;
	    private static final int KEY_LENGTH = 256;
	 
	    private passwords() {}

	    public static byte[] getNextSalt() {
	        byte[] salt = new byte[16];
	        RANDOM.nextBytes(salt);/* */
	        return salt;
	    }

	    public static byte[] hash(char[] password, byte[] salt) {
	        PBEKeySpec spec = new PBEKeySpec(password, salt, ITERATION, KEY_LENGTH);
	        Arrays.fill(password, Character.MIN_VALUE);

	        try {
	            SecretKeyFactory skf = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
	            return skf.generateSecret(spec).getEncoded();
	        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
	            throw new AssertionError("Error while hashing a password"+ e.getMessage(), e);
	        } finally {
	            spec.clearPassword();
	        }
	    }

	    public static boolean isExpectedPassword(char[] password, byte[] salt, byte[] expecteHash) {
	        byte[] pwHash = hash(password, salt);
	        if(pwHash.length != expecteHash.length) return false;
	        for(int i = 0; i<pwHash.length; i++) {
	            if(pwHash[i]!=expecteHash[i]) return false;
	        }
	        return true;
	    }
}
