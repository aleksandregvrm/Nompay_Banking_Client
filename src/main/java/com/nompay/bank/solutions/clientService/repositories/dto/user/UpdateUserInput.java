package com.nompay.bank.solutions.clientService.repositories.dto.user;

public record UpdateUserInput(
    String email,
    String username,
    String password,
    String name,
    String surname
) {}
