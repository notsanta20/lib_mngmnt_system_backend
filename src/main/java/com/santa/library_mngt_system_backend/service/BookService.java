package com.santa.library_mngt_system_backend.service;

import com.santa.library_mngt_system_backend.model.Book;
import com.santa.library_mngt_system_backend.repo.BookRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
