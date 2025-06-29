package com.nompay.bank.solutions.clientService.repositories.enums;

public enum AccountCurrencies {
  USD("USD","United States Dollar"),
  EUR("EUR","Euro"),
  GEL("GEL","Georgian Lari"),
  GBP("GBP","British Pound");


  private final String code;
  private final String accountCurreny;

  AccountCurrencies(String accountCurrency, String code){
    this.accountCurreny = accountCurrency;
    this.code = code;
  }

  public String getAccountCurreny(){
    return getAccountCurreny();
  }
}
