package com.nompay.bank.solutions.clientService.services.impl;

import com.nompay.bank.solutions.clientService.repositories.dto.account.CreateAccountInput;
import com.nompay.bank.solutions.clientService.repositories.entities.AccountEntity;
import com.nompay.bank.solutions.clientService.services.AccountService;
import org.apache.coyote.BadRequestException;
import org.springframework.stereotype.Service;

@Service
public class AccountServiceImpl implements AccountService {

  @Override
  public AccountEntity createAccount(CreateAccountInput input) throws BadRequestException {
    return null;
  }

  @Override
  public void updateAccount() throws BadRequestException {

  }
}
