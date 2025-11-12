package com.santa.book_service.service;


import com.santa.book_service.dto.BookDTO;
import com.santa.book_service.exception.BookNotFoundException;
import com.santa.book_service.model.Book;
import com.santa.book_service.repo.BookRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class BookService {

    @Autowired
    private BookRepo repo;

    public Page<BookDTO> getBooks(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Book> bookPage = repo.findAll(pageable);

        return bookPage.map(BookDTO::new);
    }

    public void addBook(Book book) {
        repo.save(book);
    }

    public BookDTO getBookByISBN(String isbn) {
        Book book = repo.findByIsbn(isbn).orElseThrow(()->new BookNotFoundException(isbn));

        return new BookDTO(book);
    }

    public void updateBook(String isbn ,Book book) {
        Book result = repo.findByIsbn(isbn).orElseThrow(()->new BookNotFoundException(isbn));

        result.setIsbn(book.getIsbn());
        result.setTitle(book.getTitle());
        result.setAuthor(book.getAuthor());
        result.setCategory(book.getCategory());
        result.setAvailableCopies(book.getAvailableCopies());
        result.setPrice(book.getPrice());

        repo.save(result);
        System.out.println(result);
    }

    public void deleteBookByISBN(String isbn) {
        Book deletedBook =  repo.findByIsbn(isbn).orElseThrow(()->new BookNotFoundException(isbn));
        repo.delete(deletedBook);
    }

    public BookDTO findBook(String title, String author, String category) {
        Book book = repo.findBook(title, author, category).orElseThrow(()->new BookNotFoundException(""));
        return new BookDTO(book);
    }

    public Page<BookDTO> getAvailableBooks(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Book> bookPage = repo.findAllByAvailableCopies(pageable);

        return bookPage.map(BookDTO::new);

    }
}
