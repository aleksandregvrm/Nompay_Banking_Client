package com.nompay.bank.solutions.clientService.repositories.entities.jpa;

import com.nompay.bank.solutions.clientService.repositories.entities.BlockedAccountsEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BlockedAccountsEntityJpa extends JpaRepository<BlockedAccountsEntity, Long> {
}
