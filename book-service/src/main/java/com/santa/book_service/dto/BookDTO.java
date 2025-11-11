package com.santa.book_service.dto;

import com.santa.book_service.model.Book;
import lombok.Data;


@Data
public class BookDTO {
    private long id;
    private String isbn;
    private String title;
    private String author;
    private String category;
    private int availableCopies;
    private double price;

    public BookDTO(Book book){
        this.id = book.getId();
        this.isbn = book.getIsbn();
        this.title = book.getTitle();
        this.author = book.getAuthor();
        this.availableCopies = book.getAvailableCopies();
        this.price = book.getPrice();
    }
}
