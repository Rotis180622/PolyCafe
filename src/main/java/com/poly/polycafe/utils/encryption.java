/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.poly.polycafe.utils;

import java.security.SecureRandom;
import java.util.Base64;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.GCMParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;

/**
 *
 * @author Gaudomun
 */
public class encryption {
    private static final String PASSPHRASE = "change_this_should_be_secret_and_long";
    private static final byte[] SALT = "some_static_salt".getBytes(); // có thể thay bằng salt động lưu ở prefs
    private static final int PBKDF2_ITER = 65536;
    private static final int KEY_LEN = 256; // bits
    
    private SecretKey deriveKey(char[] passphrase, byte[] salt) throws Exception {
        PBEKeySpec spec = new PBEKeySpec(passphrase, salt, PBKDF2_ITER, KEY_LEN);
        SecretKeyFactory skf = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
        byte[] keyBytes = skf.generateSecret(spec).getEncoded();
        return new SecretKeySpec(keyBytes, "AES");
    }

    private String encrypt(String plain) throws Exception {
        SecretKey key = deriveKey(PASSPHRASE.toCharArray(), SALT);
        Cipher cipher = Cipher.getInstance("AES/GCM/NoPadding");
        byte[] iv = new byte[12]; // 96-bit IV recommended for GCM
        SecureRandom rnd = new SecureRandom();
        rnd.nextBytes(iv);
        GCMParameterSpec spec = new GCMParameterSpec(128, iv); // 128-bit tag
        cipher.init(Cipher.ENCRYPT_MODE, key, spec);
        byte[] ct = cipher.doFinal(plain.getBytes("UTF-8"));

        // Lưu iv + ciphertext base64 (iv||ct)
        byte[] out = new byte[iv.length + ct.length];
        System.arraycopy(iv, 0, out, 0, iv.length);
        System.arraycopy(ct, 0, out, iv.length, ct.length);
        return Base64.getEncoder().encodeToString(out);
    }

    private String decrypt(String b64) throws Exception {
        byte[] all = Base64.getDecoder().decode(b64);
        byte[] iv = new byte[12];
        System.arraycopy(all, 0, iv, 0, iv.length);
        byte[] ct = new byte[all.length - iv.length];
        System.arraycopy(all, iv.length, ct, 0, ct.length);

        SecretKey key = deriveKey(PASSPHRASE.toCharArray(), SALT);
        Cipher cipher = Cipher.getInstance("AES/GCM/NoPadding");
        GCMParameterSpec spec = new GCMParameterSpec(128, iv);
        cipher.init(Cipher.DECRYPT_MODE, key, spec);
        byte[] pt = cipher.doFinal(ct);
        return new String(pt, "UTF-8");
    }

}
