package com.nompay.bank.solutions.clientService.controllers;

import com.nompay.bank.solutions.clientService.repositories.dto.CreateUserInput;
import com.nompay.bank.solutions.clientService.repositories.entities.UserEntity;
import com.nompay.bank.solutions.clientService.services.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.stereotype.Controller;

import static java.lang.System.out;

@Controller
public class UserController {

    @Autowired
    private UserServiceImpl userService;

    @MutationMapping
    public UserEntity createUser(@Argument("input") CreateUserInput input) throws Exception{
        out.println(input);
        return this.userService.registerUser(input);
    }

}
