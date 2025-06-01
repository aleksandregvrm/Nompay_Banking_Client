package com.nompay.bank.solutions.clientService.services;

import com.nompay.bank.solutions.clientService.repositories.dto.CreateUserInput;
import com.nompay.bank.solutions.clientService.repositories.entities.UserEntity;

public interface UserService {
    UserEntity registerUser(CreateUserInput input) throws Exception;
    UserEntity updateUser(UserEntity user);
}
