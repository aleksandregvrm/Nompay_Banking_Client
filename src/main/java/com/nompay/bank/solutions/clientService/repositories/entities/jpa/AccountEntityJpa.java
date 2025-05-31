package com.nompay.bank.solutions.clientService.repositories.entities.jpa;

import com.nompay.bank.solutions.clientService.repositories.entities.AccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountEntityJpa extends JpaRepository<Long, AccountEntity> {
}
