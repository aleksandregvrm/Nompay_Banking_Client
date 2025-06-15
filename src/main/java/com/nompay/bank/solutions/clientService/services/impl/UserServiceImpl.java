package com.nompay.bank.solutions.clientService.services.impl;

import com.nompay.bank.solutions.clientService.repositories.dto.user.CreateUserInput;
import com.nompay.bank.solutions.clientService.repositories.dto.user.UpdateUserInput;
import com.nompay.bank.solutions.clientService.repositories.entities.UserEntity;
import com.nompay.bank.solutions.clientService.repositories.entities.jpa.AccountEntityJpa;
import com.nompay.bank.solutions.clientService.repositories.entities.jpa.BlockedAccountsEntityJpa;
import com.nompay.bank.solutions.clientService.repositories.entities.jpa.UserEntityJpa;
import com.nompay.bank.solutions.clientService.services.UserService;
import com.nompay.bank.solutions.clientService.utils.impl.PasswordServiceImpl;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ValidationException;
import jakarta.validation.Validator;
import org.apache.coyote.BadRequestException;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

import static java.lang.System.out;

@Service
public class UserServiceImpl implements UserService {

  private final UserEntityJpa userRepository;
  private final AccountEntityJpa accountRepository;
  private final BlockedAccountsEntityJpa blockedAccountRepository;
  private final Validator validator;

  @Lazy
  private final PasswordServiceImpl passwordServiceImpl; // Lazy initializing the password service...

  public UserServiceImpl(UserEntityJpa userRepository, AccountEntityJpa accountRepository, BlockedAccountsEntityJpa blockedAccountRepository, PasswordServiceImpl passwordServiceImpl, Validator validator) {
    this.userRepository = userRepository;
    this.accountRepository = accountRepository;
    this.blockedAccountRepository = blockedAccountRepository;
    this.passwordServiceImpl = passwordServiceImpl;
    this.validator = validator;
  }

  //Registering user
  @Override
  public UserEntity registerUser(CreateUserInput input) throws Exception {
    UserEntity user = new UserEntity();
    user.setName(input.getName());
    user.setSurname(input.getSurname());
    user.setUsername(input.getUsername());
    user.setEmail(input.getEmail());
    String encryptedPassword = this.passwordServiceImpl.encryptPassword(input.getPassword()); // Encrypting the password...
    user.setPassword(encryptedPassword);
    // Validating all constraints here...
    Set<ConstraintViolation<UserEntity>> violations = validator.validate(user);
    if (!violations.isEmpty()) {
      StringBuilder sb = new StringBuilder();
      for (ConstraintViolation<UserEntity> violation : violations) {
        sb.append(violation.getPropertyPath()).append(": ").append(violation.getMessage()).append("; ");
      }
      throw new IllegalArgumentException("Validation failed: " + sb.toString());
    }
    try {
      this.userRepository.save(user);
    } catch (ValidationException exception) {
      throw exception;
    }
    return user;
  }

  // Updating user data...
  @Override
  public UserEntity updateUser(int userId, UpdateUserInput input) throws Exception {
    Optional<UserEntity> userOptional = userRepository.findById((long) userId);
    if (userOptional.isEmpty()) {
      throw new IllegalArgumentException("User not found with ID: " + userId);
    }

    UserEntity user = userOptional.get();

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

    // ✅ Optionally validate the updated entity
    Set<ConstraintViolation<UserEntity>> violations = validator.validate(user);
    if (!violations.isEmpty()) {
      StringBuilder sb = new StringBuilder();
      for (ConstraintViolation<UserEntity> violation : violations) {
        sb.append(violation.getPropertyPath()).append(": ").append(violation.getMessage()).append("; ");
      }
      throw new IllegalArgumentException("Validation failed: " + sb.toString());
    }

    // 💾 Save updated user
    return userRepository.save(user);
  }

  // Getting single user
  @Override
  public UserEntity getUser(int userId) throws BadRequestException {
    Optional<UserEntity> user = this.userRepository.findById((long) userId);
    if (user.isEmpty()) {
      throw new BadRequestException("No user found with that id");
    }
    return user.get();
  }
}
