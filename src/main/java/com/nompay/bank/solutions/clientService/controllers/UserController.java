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


@Controller
public class UserController {

  @Autowired
  private UserServiceImpl userService;

  @MutationMapping(name = "createUser")
  public UserEntity createUser(@Argument("input") CreateUserInput input) throws Exception {
    return this.userService.registerUser(input);
  }

  @QueryMapping(name = "getUser")
  public UserEntity getUser(@Argument("id") int id) throws BadRequestException {
    return this.userService.getUser(id);
  }

  @MutationMapping(name = "updateUser")
  public UserEntity updateUser(@Argument("userId") int userId, @Argument("input") UpdateUserInput input) throws Exception {
    return this.userService.updateUser(userId, input);
  }

  @MutationMapping(name = "deleteUser")
  public String deleteUser(@Argument("userId") int userId) throws BadRequestException{
    this.userService.deleteUser(userId);
    return "User Deleted";
  }

}

