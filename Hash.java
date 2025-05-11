package com.bean;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Hash {

	
	public static void main(String[] args) {
        String input = "Hello, World!";
        
        try {
            // MD5
            String md5Hash = hashString(input, "MD5");
            System.out.println("MD5: " + md5Hash);
            
            // SHA-1
            String sha1Hash = hashString(input, "SHA-1");
            System.out.println("SHA-1: " + sha1Hash);
            
            // SHA-256
            String sha256Hash = hashString(input, "SHA-256");
            System.out.println("SHA-256: " + sha256Hash);
            
        } catch (NoSuchAlgorithmException e) {
            System.err.println("Hash algorithm not found: " + e.getMessage());
        }
    }
    
    public static String hashString(String input, String algorithm) throws NoSuchAlgorithmException {
        MessageDigest messageDigest = MessageDigest.getInstance(algorithm);
        byte[] hashBytes = messageDigest.digest(input.getBytes());
        StringBuilder hashString = new StringBuilder();
        
        for (byte b : hashBytes) {
            hashString.append(String.format("%02x", b));
        }
        
        return hashString.toString();
    }
}
