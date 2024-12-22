package com.xCorporation.creditCardSystem.repository;

import com.xCorporation.creditCardSystem.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
}
