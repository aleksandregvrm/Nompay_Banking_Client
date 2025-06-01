package com.nompay.bank.solutions.clientService.utils.impl;

import com.nompay.bank.solutions.clientService.utils.PasswordService;
import org.springframework.stereotype.Component;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

@Component
public class PasswordServiceImpl implements PasswordService {

    private static final String PASSWORD_SECRET_KEY = "randompassworEncryptionKey";

    public PasswordServiceImpl() {
    }

    @Override
    public String encryptPassword(String passwordToEncrypt) throws Exception {
        try {
            SecretKeySpec secretKey = new SecretKeySpec(PASSWORD_SECRET_KEY.getBytes(), "AES");

            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);
            byte[] encrypted = cipher.doFinal(passwordToEncrypt.getBytes());
            return Base64.getEncoder().encodeToString(encrypted);
        } catch (Exception exception) {
            throw exception;
        }
    }

    @Override
    public String decryptPassword() {
        return "";
    }
}
