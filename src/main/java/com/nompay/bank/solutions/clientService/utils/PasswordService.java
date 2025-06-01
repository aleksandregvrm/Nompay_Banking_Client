package com.nompay.bank.solutions.clientService.utils;

public interface PasswordService {
    String encryptPassword (String passwordToEncrypt) throws Exception;
    public String decryptPassword();
}
