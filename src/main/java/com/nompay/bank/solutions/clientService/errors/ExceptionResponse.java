package com.nompay.bank.solutions.clientService.errors;

public class ExceptionResponse {
  private int status;
  private String message;
  private long timestamp;

  public ExceptionResponse(int status, String message) {
    this.status = status;
    this.message = message;
    this.timestamp = System.currentTimeMillis();
  }
}
