package com.example.library_management.service;

import com.example.library_management.model.*;
import com.example.library_management.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private UserRepository userRepository;

    public List<Transaction> getAllTransactions() {
        return transactionRepository.findAll();
    }

    // Borrow a book
    public String borrowBook(Long userId, Long bookId) {
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new RuntimeException("Book not found"));

        if (!book.isAvailable()) {
            return " Book is already borrowed.";
        }

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Transaction tx = new Transaction();
        tx.setBook(book);
        tx.setUser(user);
        tx.setBorrowDate(LocalDateTime.now());
        transactionRepository.save(tx);

        // Mark book as unavailable
        book.setAvailable(false);
        bookRepository.save(book);

        return " Book borrowed successfully!";
    }

    // Return a book
    public String returnBook(Long transactionId) {
        Transaction tx = transactionRepository.findById(transactionId)
                .orElseThrow(() -> new RuntimeException("Transaction not found"));

        if (tx.getReturnDate() != null) {
            return " Book already returned.";
        }

        tx.setReturnDate(LocalDateTime.now());
        transactionRepository.save(tx);

        // Mark book as available again
        Book book = tx.getBook();
        book.setAvailable(true);
        bookRepository.save(book);

        return " Book returned successfully!";
    }
}
