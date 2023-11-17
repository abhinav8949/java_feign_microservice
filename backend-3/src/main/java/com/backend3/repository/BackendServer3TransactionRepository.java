package com.backend3.repository;

import com.backend3.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BackendServer3TransactionRepository extends JpaRepository<Transaction, Long> {
    List<Transaction> findByAccountNumberAndStatus(String accountNumber, String status);
}
