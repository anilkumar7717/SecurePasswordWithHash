package com.example.securepassword;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

public class SaltedMD5 {

    public SaltedMD5() {
    }

    public String getSecurePassword(String passwordToHash, byte[] salt) {
        String generatedPassword = null;
        try {
            /*
            Create MessageDigest instance for MD5 or SHA-1,SHA-256,SHA-384,SHA-512
            MD5 (Simple password security using MD5)
            SHA-1 (Stronger then MD5 Simplest one – 160 bits Hash)
            SHA-256 (Stronger than SHA-1 – 256 bits Hash)
            SHA-384 (Stronger than SHA-256 – 384 bits Hash)
            SHA-512 (Stronger than SHA-384 – 512 bits Hash)
            */
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            //Add password bytes to digest
            md.update(salt);
            //Get the hash's bytes
            byte[] bytes = md.digest(passwordToHash.getBytes());
            //This bytes[] has bytes in decimal format;
            //Convert it to hexadecimal format
            StringBuilder sb = new StringBuilder();
            for (byte aByte : bytes) {
                sb.append(Integer.toString((aByte & 0xff) + 0x100, 16).substring(1));
            }
            //Get complete hashed password in hex format
            generatedPassword = sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return generatedPassword;
    }

    //Add salt
    public byte[] getSalt() throws NoSuchAlgorithmException {
        SecureRandom sr = SecureRandom.getInstance("SHA1PRNG");
        byte[] salt = new byte[16];
        sr.nextBytes(salt);
        return salt;
    }
}
