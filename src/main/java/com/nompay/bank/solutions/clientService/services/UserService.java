package com.nompay.bank.solutions.clientService.services;

import com.nompay.bank.solutions.clientService.repositories.dto.user.CreateUserInput;
import com.nompay.bank.solutions.clientService.repositories.dto.user.UpdateUserInput;
import com.nompay.bank.solutions.clientService.repositories.entities.UserEntity;
import org.apache.coyote.BadRequestException;

public interface UserService {
    UserEntity registerUser(CreateUserInput input) throws Exception;

    UserEntity updateUser(int userId, UpdateUserInput user) throws Exception;

    UserEntity getUser(int userId) throws BadRequestException;

    void deleteUser(int userId) throws BadRequestException;
}
