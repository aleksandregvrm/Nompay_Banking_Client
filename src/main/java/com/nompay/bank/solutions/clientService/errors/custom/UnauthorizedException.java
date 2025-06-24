package com.nompay.bank.solutions.clientService.errors.custom;

public class UnauthorizedException extends RuntimeException {
  public UnauthorizedException(String message) {
    super(message);
  }
}
