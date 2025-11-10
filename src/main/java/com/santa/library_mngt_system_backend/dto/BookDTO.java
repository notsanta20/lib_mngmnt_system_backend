package com.santa.library_mngt_system_backend.dto;

import com.santa.library_mngt_system_backend.model.Book;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

@Data
public class BookDTO {
    private long id;
    private String isbn;
    private String title;
    private String author;
    private String category;
    private int totalCopies;
    private int availableCopies;
    private double price;
    private List<Long> transactionIds;

    public BookDTO(Book book){
        this.id = book.getId();
        this.isbn = book.getIsbn();
        this.title = book.getTitle();
        this.author = book.getAuthor();
        this.totalCopies = book.getTotalCopies();
        this.availableCopies = book.getAvailableCopies();
        this.price = book.getPrice();
        this.transactionIds = book.getTransactions()
                .stream()
                .map(t->t.getId())
                .collect(Collectors.toList());
    }
}
