package com.nompay.bank.solutions.clientService.errors.custom;

public class TransactionException extends RuntimeException {
  public TransactionException(String message) {
    super(message);
  }
}
