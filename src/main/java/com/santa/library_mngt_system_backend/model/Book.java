package com.santa.library_mngt_system_backend.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "books")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(unique = true, nullable = false)
    private String isbn;
    private String title;
    private String author;
    private String category;
    private int totalCopies;
    private int availableCopies;
    private double price;

    @OneToMany(mappedBy = "book", cascade = CascadeType.ALL)
    private List<Transaction> transactions;

}
