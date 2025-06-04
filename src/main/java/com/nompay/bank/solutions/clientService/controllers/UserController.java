package com.nompay.bank.solutions.clientService.controllers;

import com.nompay.bank.solutions.clientService.repositories.dto.user.CreateUserInput;
import com.nompay.bank.solutions.clientService.repositories.dto.user.UpdateUserInput;
import com.nompay.bank.solutions.clientService.repositories.entities.UserEntity;
import com.nompay.bank.solutions.clientService.services.impl.UserServiceImpl;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import static java.lang.System.out;

@Controller
public class UserController {

    @Autowired
    private UserServiceImpl userService;

    @MutationMapping
    public UserEntity createUser(@Argument("input") CreateUserInput input) throws Exception {
        out.println("asdasda");
        out.println(input);
        return this.userService.registerUser(input);
    }

    @QueryMapping
    public UserEntity getSingleUser(@Argument int userId) throws BadRequestException {
        out.println(userId);
        return this.userService.getUser(userId);
    }

    @QueryMapping
    public UserEntity updateUser(@Argument int userId, @Argument("input") UpdateUserInput input) throws Exception {
        out.println(userId);
        return this.userService.updateUser(userId, input);
    }

}
