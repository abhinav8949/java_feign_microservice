package com.backend3.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TransactionTest {

    @Test
    void createTransactionWithNonNullValues_shouldCreateTransaction() {
        Long transactionId = 1L;
        String status = "success";
        String amount = "100.00";
        String date = "2023-01-01";
        String accountNumber = "123";
        Transaction transaction = new Transaction(transactionId, status, amount, date, accountNumber);
        assertNotNull(transaction);
        assertEquals(transactionId, transaction.getTransactionId());
        assertEquals(status, transaction.getStatus());
        assertEquals(amount, transaction.getAmount());
        assertEquals(date, transaction.getDate());
        assertEquals(accountNumber, transaction.getAccountNumber());
    }

    @Test
    void createTransactionWithRequiredNonNullValues_shouldCreateTransaction() {
        String status = "success";
        String amount = "100.00";
        String date = "2023-01-01";
        String accountNumber = "123";
        Transaction transaction = new Transaction(status, amount, date, accountNumber);
        assertNotNull(transaction);
        assertNull(transaction.getTransactionId()); // generated value
        assertEquals(status, transaction.getStatus());
        assertEquals(amount, transaction.getAmount());
        assertEquals(date, transaction.getDate());
        assertEquals(accountNumber, transaction.getAccountNumber());
    }

    @Test
    void createTransactionWithNullValues_shouldThrowException() {
        assertThrows(NullPointerException.class, () -> new Transaction(null, "100.00", "2023-01-01", "123"));
        assertThrows(NullPointerException.class, () -> new Transaction("success", null, "2023-01-01", "123"));
        assertThrows(NullPointerException.class, () -> new Transaction("success", "100.00", null, "123"));
        assertThrows(NullPointerException.class, () -> new Transaction("success", "100.00", "2023-01-01", null));
    }
}
