package com.backend1.repository;

import com.backend1.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface BackendServer1TransactionRepository extends JpaRepository<Transaction, Long> {
    List<Transaction> findByAccountNumberAndStatus(String accountNumber, String status);
}
