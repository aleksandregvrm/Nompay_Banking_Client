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

  @MutationMapping(name = "createUser")
  public UserEntity createUser(@Argument("input") CreateUserInput input) throws Exception {
    System.out.println("createUser input: " + input);
    return this.userService.registerUser(input);
  }

  @QueryMapping(name = "getUser")
  public UserEntity getUser(@Argument("id") int id) throws BadRequestException {
    System.out.println("getUser id: " + id);
    return this.userService.getUser(id);
  }

  @MutationMapping(name = "updateUser")
  public UserEntity updateUser(@Argument("userId") int userId, @Argument("input") UpdateUserInput input) throws Exception {
    System.out.println("updateUser userId: " + userId);
    return this.userService.updateUser(userId, input);
  }

  @QueryMapping(name = "graphTest")
  public String graphTest(@Argument("userId") String userId) {
    out.println("there is some error going in here in here in here.,.......");
    return userId;
  }
}

