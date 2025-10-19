package com.example.library_management.controller;

import com.example.library_management.model.Transaction;
import com.example.library_management.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/transactions")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    //  Get all transactions
    @GetMapping
    public ResponseEntity<List<Transaction>> getAllTransactions() {
        return ResponseEntity.ok(transactionService.getAllTransactions());
    }

    //  Borrow a book
    @PostMapping("/borrow")
    public ResponseEntity<String> borrowBook(@RequestParam Long userId, @RequestParam Long bookId) {
        return ResponseEntity.ok(transactionService.borrowBook(userId, bookId));
    }

    //  Return a book
    @PostMapping("/return")
    public ResponseEntity<String> returnBook(@RequestParam Long transactionId) {
        return ResponseEntity.ok(transactionService.returnBook(transactionId));
    }
}
