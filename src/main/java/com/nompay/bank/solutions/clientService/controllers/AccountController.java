package com.nompay.bank.solutions.clientService.controllers;

import com.nompay.bank.solutions.clientService.repositories.dto.account.CreateAccountInput;
import com.nompay.bank.solutions.clientService.repositories.entities.AccountEntity;
import com.nompay.bank.solutions.clientService.services.impl.AccountServiceImpl;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.stereotype.Controller;

@Controller
public class AccountController {

  @Autowired
  private AccountServiceImpl accountServiceImpl;


  @MutationMapping(name = "createAccount")
  public AccountEntity createUser(@Argument("input") CreateAccountInput input) throws BadRequestException {
    return this.accountServiceImpl.createAccount(input);
  }

  @MutationMapping(name = "updateAccount")
  public String updateAccount() {

    return "success";
  }

}
