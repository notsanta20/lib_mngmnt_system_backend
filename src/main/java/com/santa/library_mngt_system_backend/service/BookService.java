package com.santa.library_mngt_system_backend.service;

import com.santa.library_mngt_system_backend.model.Book;
import com.santa.library_mngt_system_backend.repo.BookRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Service
public class BookService {

    @Autowired
    private BookRepo repo;

    public List<Book> getBooks() {
        return repo.findAll();
    }

    public void addBook(Book book) {
        repo.save(book);
    }

    public Book getBookByISBN(String isbn) {
        return repo.findByIsbn(isbn);
    }

    public void updateBook(String isbn ,Book book) {
        Book result = getBookByISBN(isbn);

        result.setIsbn(book.getIsbn());
        result.setTitle(book.getTitle());
        result.setAuthor(book.getAuthor());
        result.setCategory(book.getCategory());
        result.setTotalCopies(book.getTotalCopies());
        result.setAvailableCopies(book.getAvailableCopies());
        result.setPrice(book.getPrice());

        repo.save(result);
    }

    public void deleteBookByISBN(String isbn) {
        Book deletedBook = getBookByISBN(isbn);
        repo.delete(deletedBook);
    }

    public Book findBook(String title, String author, String category) {
        return repo.findBook(title, author, category);
    }

    public List<Book> getAvailableBooks() {
        List<Book> books = repo.findAllAvailableBooks();

        return books;
    }
}
