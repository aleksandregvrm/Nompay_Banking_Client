package com.nompay.bank.solutions.clientService.utils.impl;

import com.nompay.bank.solutions.clientService.repositories.dto.user.UpdateUserInput;
import com.nompay.bank.solutions.clientService.repositories.entities.UserEntity;
import com.nompay.bank.solutions.clientService.utils.UserHelperService;
import org.springframework.stereotype.Component;

@Component
public class UserHelperServiceImpl implements UserHelperService {
  private PasswordServiceImpl passwordServiceImpl;

  public UserHelperServiceImpl(PasswordServiceImpl passwordServiceImpl) {
    this.passwordServiceImpl = passwordServiceImpl;
  }

  @Override
  public void userDataComparator(UserEntity user, UpdateUserInput input) throws Exception {
    if (input.email() != null) {
      user.setEmail(input.email());
    }
    if (input.username() != null) {
      user.setUsername(input.username());
    }
    if (input.password() != null) {
      String encryptedPassword = passwordServiceImpl.encryptPassword(input.password());
      user.setPassword(encryptedPassword);
    }
    if (input.name() != null) {
      user.setName(input.name());
    }
    if (input.surname() != null) {
      user.setSurname(input.surname());
    }
  }
}
