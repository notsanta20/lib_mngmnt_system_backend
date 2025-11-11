package com.santa.library_mngt_system_backend.service;

import com.santa.library_mngt_system_backend.dto.BookDTO;
import com.santa.library_mngt_system_backend.model.Book;
import com.santa.library_mngt_system_backend.repo.BookRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedModel;
import org.springframework.stereotype.Service;

import java.util.List;

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
        Book book = repo.findByIsbn(isbn);

        return new BookDTO(book);
    }

    public void updateBook(String isbn ,Book book) {
        Book result = repo.findByIsbn(isbn);

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
        Book deletedBook =  repo.findByIsbn(isbn);
        repo.delete(deletedBook);
    }

    public BookDTO findBook(String title, String author, String category) {
        Book book = repo.findBook(title, author, category);
        return new BookDTO(book);
    }

    public List<BookDTO> getAvailableBooks() {
        return repo.findAllAvailableBooks()
                .stream()
                .map(BookDTO::new)
                .toList();
    }
}
