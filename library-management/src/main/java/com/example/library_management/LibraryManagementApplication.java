package com.example.library_management;

import com.example.library_management.model.Book;
import com.example.library_management.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LibraryManagementApplication implements CommandLineRunner {

	@Autowired
	private BookService bookService;

	public static void main(String[] args) {
		SpringApplication.run(LibraryManagementApplication.class, args);
	}

	@Override
	public void run(String... args) {
		bookService.addBook(new Book(null, "The Pragmatic Programmer", "Andrew Hunt", true));
		bookService.addBook(new Book(null, "Clean Code", "Robert Martin", true));

		bookService.getAllBooks().forEach(System.out::println);
	}
}
