package com.nompay.bank.solutions.clientService.services;

import com.nompay.bank.solutions.clientService.repositories.dto.account.CreateAccountInput;
import com.nompay.bank.solutions.clientService.repositories.entities.AccountEntity;
import org.apache.coyote.BadRequestException;

public interface AccountService {
  public AccountEntity createAccount(CreateAccountInput input) throws BadRequestException;

  public void updateAccount() throws BadRequestException;
}
