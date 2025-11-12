package com.santa.transaction_service.model;

import lombok.Data;

@Data
public class Book {
    private long id;
    private String isbn;
    private String title;
    private String author;
    private String category;
    private int availableCopies;
    private double price;
}
