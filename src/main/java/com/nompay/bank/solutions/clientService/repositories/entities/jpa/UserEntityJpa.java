package com.nompay.bank.solutions.clientService.repositories.entities.jpa;

import com.nompay.bank.solutions.clientService.repositories.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserEntityJpa extends JpaRepository<UserEntity, Long> {
}
