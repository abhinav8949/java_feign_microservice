package com.backend2.repository;

import com.backend2.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BackendServer2TransactionRepository extends JpaRepository<Transaction, Long> {
    List<Transaction> findByAccountNumberAndStatus(String accountNumber, String status);
}
