package com.example.library_management.repository;

import com.example.library_management.model.Transaction;
import com.example.library_management.model.User;
import com.example.library_management.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    List<Transaction> findByUser(User user);
    List<Transaction> findByBook(Book book);
}
