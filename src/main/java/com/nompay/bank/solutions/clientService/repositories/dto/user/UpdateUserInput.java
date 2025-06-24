package com.nompay.bank.solutions.clientService.repositories.dto.user;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UpdateUserInput(
    @NotBlank
    @Size(min = 5, max = 30, message = "Email must be at least 5 characters long, less than 30")
    String email,

    @NotBlank
    @Size(min = 5, max = 25, message = "Username must be at least 5 characters long, less than 25")
    String username,

    @NotBlank
    @Size(min = 5, max = 35, message = "Password must be at least 5 characters long, less than 35")
    String password,

    @NotBlank
    @Size(min = 3, max = 25, message = "Name must be at least 3 characters long, less than 25")
    String name,

    @NotBlank
    @Size(min = 2, max = 25, message = "Surname must be at least 2 characters long, less than 25")
    String surname
) {
}
