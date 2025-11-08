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
    public ResponseEntity<List<Book>> getBooks(){
        List<Book> books = service.getBooks();

        return new ResponseEntity<>(books, HttpStatus.OK);
    }

    @PostMapping("/books")
    public ResponseEntity<String> addBook(@RequestBody Book book){
        service.addBook(book);

        return new ResponseEntity<>("Book added successfully", HttpStatus.CREATED);
    }

    @GetMapping("/books/{isbn}")
    public ResponseEntity<Book> getBooksByISBN(@PathVariable String isbn){
        Book book = service.getBookByISBN(isbn);
        return new ResponseEntity<>(book, HttpStatus.OK);
    }

    @PutMapping("/books/{isbn}")
    public ResponseEntity<String> UpdateBooksByISBN(@PathVariable String isbn,@RequestBody Book book){
        service.updateBook(isbn,book);

        return new ResponseEntity<>("Book updated successfully", HttpStatus.OK);
    }

    @DeleteMapping("/books/{isbn}")
    public ResponseEntity<String> DeleteBooksByISBN(@PathVariable String isbn){
        service.deleteBookByISBN(isbn);

        return new ResponseEntity<>("Book successfully deleted", HttpStatus.OK);
    }

    @GetMapping("/books/search")
    public ResponseEntity<Book> searchBooks(@RequestParam String title, @RequestParam String author, @RequestParam String category){
        Book book = service.findBook(title, author, category);

        return new ResponseEntity<>(book, HttpStatus.OK);
    }

    @GetMapping("/books/available")
    public ResponseEntity<List<Book>> getAvailableBooks(){
        List<Book> books = service.getAvailableBooks();

        return new ResponseEntity<>(books, HttpStatus.OK);
    }
}
