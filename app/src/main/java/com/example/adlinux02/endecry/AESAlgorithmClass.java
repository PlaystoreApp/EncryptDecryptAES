package com.example.adlinux02.endecry;

import java.util.Base64;
import java.util.Formatter;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/**
 * Created by adlinux02 on 13/3/18.
 */

public class AESAlgorithmClass {
    public static String encrypt(String key, String initVector, String value) {
        try {
            IvParameterSpec iv = new IvParameterSpec(initVector.getBytes("UTF-8"));
            SecretKeySpec skeySpec = new SecretKeySpec(key.getBytes("UTF-8"), "AES");
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
            cipher.init(Cipher.ENCRYPT_MODE, skeySpec, iv);
            byte[] encrypted = cipher.doFinal(value.getBytes());
            return bytesToHex(encrypted);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return null;
    }

    public static String decrypt(String key, String initVector, String encrypted) {
        try {
            IvParameterSpec iv = new IvParameterSpec(initVector.getBytes("UTF-8"));
            SecretKeySpec skeySpec = new SecretKeySpec(key.getBytes("UTF-8"), "AES");
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
            cipher.init(Cipher.DECRYPT_MODE, skeySpec, iv);
            byte[] original = cipher.doFinal(hexStringToByteArray(encrypted));
            return new String(original);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return null;
    }

    public static void main(String[] args) {
        String key = ""; // 128 bit key
        String initVector = "RandomInitVector"; // 16 bytes IV

        System.out.println(decrypt(key, initVector,
                encrypt(key, initVector, "Hello World")));
    }

    public static String bytesToHex(byte[] hash) {
        Formatter formatter = new Formatter();
        for (byte b : hash) {
            formatter.format("%02x", b);
        }
        String result = formatter.toString();
        formatter.close();
        return result;
    }

    public static byte[] hexStringToByteArray(String s) {
        byte[] byteArray = new byte[s.length()/2];
        String[] strBytes = new String[s.length()/2];
        int k = 0;
        for (int i = 0; i < s.length(); i=i+2) {
            int j = i+2;
            strBytes[k] = s.substring(i,j);
            byteArray[k] = (byte)Integer.parseInt(strBytes[k], 16);
            k++;
        }
        return byteArray;

        /*int len = s.length();
        byte[] data = new byte[len / 2];
        for (int i = 0; i < len; i += 2) {
            data[i / 2] = (byte) ((Character.digit(s.charAt(i), 16) << 4)
                    + Character.digit(s.charAt(i + 1), 16));
        }
        return data;*/
    }

}
