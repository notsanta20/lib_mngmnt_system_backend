package com.santa.library_mngt_system_backend.controller;

import com.santa.library_mngt_system_backend.model.Book;
import com.santa.library_mngt_system_backend.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class BookController {

    @Autowired
    private BookService service;

    @GetMapping("/books")
    public List<Book> getBooks(){
        return service.getBooks();
    }

    @PostMapping("/books")
    public ResponseEntity<String> addBook(@RequestBody Book book){
        System.out.println(book);
        service.addBook(book);

        return new ResponseEntity<>("Book added successfully", HttpStatus.CREATED);
    }

    @GetMapping("/books/{isbn}")
    public String getBooksByISBN(){
        return "All books";
    }

    @PutMapping("/books/{isbn}")
    public String UpdateBooksByISBN(){
        return "All books";
    }

    @DeleteMapping("/books/{isbn}")
    public String DeleteBooksByISBN(){
        return "All books";
    }

    @GetMapping("/books/search")
    public String searchBooks(){
        return "All books";
    }

    @GetMapping("/books/available")
    public String getAvailableBooks(){
        return "All books";
    }
}
