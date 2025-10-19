
package com.example.library_management.model;

import jakarta.persistence.*;
        import lombok.*;

@Entity
@Table(name = "books")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String author;

    @Column(nullable = false)
    private boolean available = true; // true means book is available to borrow
}
