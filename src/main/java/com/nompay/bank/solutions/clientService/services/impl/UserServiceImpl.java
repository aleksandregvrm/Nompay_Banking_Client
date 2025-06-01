package com.nompay.bank.solutions.clientService.services.impl;

import com.nompay.bank.solutions.clientService.repositories.dto.CreateUserInput;
import com.nompay.bank.solutions.clientService.repositories.entities.UserEntity;
import com.nompay.bank.solutions.clientService.repositories.entities.jpa.AccountEntityJpa;
import com.nompay.bank.solutions.clientService.repositories.entities.jpa.BlockedAccountsEntityJpa;
import com.nompay.bank.solutions.clientService.repositories.entities.jpa.UserEntityJpa;
import com.nompay.bank.solutions.clientService.services.UserService;
import com.nompay.bank.solutions.clientService.utils.impl.PasswordServiceImpl;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

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

    @Override
    public UserEntity registerUser(CreateUserInput input) throws Exception {
        out.println(input);
        UserEntity user = new UserEntity();
        user.setName(input.getName());
        user.setSurname(input.getEmail());
        user.setEmail(input.getEmail());
        String encryptedPassword = this.passwordServiceImpl.encryptPassword(input.getPassword()); // Encrypting the password...
        user.setPassword(encryptedPassword);

        // ✅ Validate user entity before saving
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
        } catch (Exception exception) {
            throw exception;
        }
        return user;
    }

    @Override
    public UserEntity updateUser(UserEntity user) {
        return null;
    }
}
