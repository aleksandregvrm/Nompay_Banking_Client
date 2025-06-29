package com.nompay.bank.solutions.clientService.services.impl;

import com.nompay.bank.solutions.clientService.repositories.dto.user.CreateUserInput;
import com.nompay.bank.solutions.clientService.repositories.dto.user.UpdateUserInput;
import com.nompay.bank.solutions.clientService.repositories.entities.AccountEntity;
import com.nompay.bank.solutions.clientService.repositories.entities.UserEntity;
import com.nompay.bank.solutions.clientService.repositories.entities.jpa.AccountEntityJpa;
import com.nompay.bank.solutions.clientService.repositories.entities.jpa.BlockedAccountsEntityJpa;
import com.nompay.bank.solutions.clientService.repositories.entities.jpa.UserEntityJpa;
import com.nompay.bank.solutions.clientService.services.UserService;
import com.nompay.bank.solutions.clientService.utils.impl.PasswordServiceImpl;
import com.nompay.bank.solutions.clientService.utils.impl.UserHelperServiceImpl;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ValidationException;
import jakarta.validation.Validator;
import org.apache.coyote.BadRequestException;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.logging.Logger;

import static java.lang.System.out;

@Service
public class UserServiceImpl implements UserService {

  private static final Logger logger = Logger.getLogger(UserServiceImpl.class.getName()); // Logger used for logging,,,
  private final UserEntityJpa userRepository;
  private final AccountEntityJpa accountRepository;
  private final BlockedAccountsEntityJpa blockedAccountRepository;
  private final Validator validator;

  @Lazy
  private final PasswordServiceImpl passwordServiceImpl; // Lazy initializing the password service...

  @Lazy
  private final UserHelperServiceImpl userHelperServiceImpl; // Lazy initializing the user helpers service...

  public UserServiceImpl(UserEntityJpa userRepository, AccountEntityJpa accountRepository, BlockedAccountsEntityJpa blockedAccountRepository, PasswordServiceImpl passwordServiceImpl, Validator validator, UserHelperServiceImpl userHelpersServiceImpl) {
    this.userRepository = userRepository;
    this.accountRepository = accountRepository;
    this.blockedAccountRepository = blockedAccountRepository;
    this.passwordServiceImpl = passwordServiceImpl;
    this.validator = validator;
    this.userHelperServiceImpl = userHelpersServiceImpl;
  }

  @Override
  public UserEntity registerUser(CreateUserInput input) throws Exception {
    Set<ConstraintViolation<CreateUserInput>> inputViolations = validator.validate(input);
    if (!inputViolations.isEmpty()) {
      StringBuilder sb = new StringBuilder();
      for (ConstraintViolation<CreateUserInput> violation : inputViolations) {
        sb.append(violation.getPropertyPath()).append(": ").append(violation.getMessage()).append("; ");
      }
      throw new IllegalArgumentException("Validation failed: " + sb.toString());
    }

    String encryptedPassword = this.passwordServiceImpl.encryptPassword(input.password()); // Encrypt first
    UserEntity user = new UserEntity();
    user.setName(input.name());
    user.setSurname(input.surname());
    user.setUsername(input.username());
    user.setEmail(input.email());
    user.setPassword(encryptedPassword); // Set encrypted password before persisting

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

    userHelperServiceImpl.userDataComparator(user, input); // reads all non null values and updates the user values...

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

  @Override
  public void deleteUser(int userId) throws BadRequestException {
    Optional<UserEntity> user = this.userRepository.findById((long) userId);
    if (user.isEmpty()) {
      throw new BadRequestException("No user found with that id");
    }
    UserEntity userToDelete = user.get();

    List<AccountEntity> accounts = userToDelete.getAccounts();

    if (accounts.isEmpty()) {
      this.userRepository.deleteById((long) userId);
    } else {
      throw new BadRequestException("You need to Delete the accounts of this user first...");
    }

  }


}
