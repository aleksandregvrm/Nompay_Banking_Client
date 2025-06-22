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
    if (input.getEmail() != null) {
      user.setEmail(input.getEmail());
    }
    if (input.getUsername() != null) {
      user.setUsername(input.getUsername());
    }
    if (input.getPassword() != null) {
      String encryptedPassword = passwordServiceImpl.encryptPassword(input.getPassword());
      user.setPassword(encryptedPassword);
    }
    if (input.getName() != null) {
      user.setName(input.getName());
    }
    if (input.getSurname() != null) {
      user.setSurname(input.getSurname());
    }
  }
}
