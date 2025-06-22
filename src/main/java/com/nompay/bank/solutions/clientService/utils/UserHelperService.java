package com.nompay.bank.solutions.clientService.utils;

import com.nompay.bank.solutions.clientService.repositories.dto.user.UpdateUserInput;
import com.nompay.bank.solutions.clientService.repositories.entities.UserEntity;

public interface UserHelperService {
  void userDataComparator(UserEntity user, UpdateUserInput updateUser) throws Exception;
}
