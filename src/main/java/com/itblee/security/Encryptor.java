package com.itblee.security;

import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.UUID;

public class Encryptor {

    public static String getSalt() {
        try {
            String keyID = UUID.randomUUID().toString();
            MessageDigest salt = MessageDigest.getInstance("SHA-256");
            salt.update(keyID.getBytes(StandardCharsets.UTF_8));
            return Long.toHexString(ByteBuffer.wrap(salt.digest()).getLong());
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return getSalt();
        }
    }

    public static String applySha256(String str, String strSalt) {
        String hashString;
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update(strSalt.getBytes());
            byte[] bytes = md.digest(str.getBytes());
            StringBuilder sb = new StringBuilder();
            for (byte aByte : bytes)
                sb.append(Integer.toString((aByte & 0xff) + 0x100, 16).substring(1));
            hashString = sb.toString();
        }
        catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return "";
        }
        return hashString;
    }

    public static String encode(String plain) {
        try {
            return Base64.getEncoder().encodeToString(plain.getBytes());
        } catch (Exception e) {
            return null;
        }
    }

    public static String decode(String script) {
        try {
            return new String(Base64.getDecoder().decode(script));
        } catch (Exception e) {
            return null;
        }
    }
}
