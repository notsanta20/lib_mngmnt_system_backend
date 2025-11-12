package com.santa.book_service.controller;


import com.santa.book_service.dto.BookDTO;
import com.santa.book_service.model.Book;
import com.santa.book_service.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/books")
public class BookController {

    @Autowired
    private BookService service;

    @GetMapping("/")
    public Page<BookDTO> getBooks(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ){
        return service.getBooks(page, size);
    }

    @PostMapping("/")
    public ResponseEntity<String> addBook(@RequestBody Book book){
        service.addBook(book);

        return new ResponseEntity<>("Book added successfully", HttpStatus.CREATED);
    }

    @GetMapping("/{isbn}")
    public ResponseEntity<BookDTO> getBooksByISBN(@PathVariable String isbn){
        System.out.println("Reach");
        BookDTO book = service.getBookByISBN(isbn);
        return new ResponseEntity<>(book, HttpStatus.OK);
    }

    @PutMapping("/{isbn}")
    public ResponseEntity<String> UpdateBooksByISBN(@PathVariable String isbn,@RequestBody Book book){
        service.updateBook(isbn,book);

        return new ResponseEntity<>("Book updated successfully", HttpStatus.OK);
    }

    @DeleteMapping("/{isbn}")
    public ResponseEntity<String> DeleteBooksByISBN(@PathVariable String isbn){
        service.deleteBookByISBN(isbn);

        return new ResponseEntity<>("Book successfully deleted", HttpStatus.OK);
    }

    @GetMapping("/search")
    public ResponseEntity<BookDTO> searchBooks(@RequestParam String title, @RequestParam String author, @RequestParam String category){
        BookDTO book = service.findBook(title, author, category);

        return new ResponseEntity<>(book, HttpStatus.OK);
    }

    @GetMapping("/available")
    public Page<BookDTO> getAvailableBooks(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ){
        return service.getAvailableBooks(page, size);
    }
}
