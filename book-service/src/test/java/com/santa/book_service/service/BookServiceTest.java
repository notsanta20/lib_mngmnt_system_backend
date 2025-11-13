package com.santa.book_service.service;

import com.santa.book_service.dto.BookDTO;
import com.santa.book_service.model.Book;
import com.santa.book_service.repo.BookRepo;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class BookServiceTest {
    @Mock
    BookRepo bookRepo;

    @InjectMocks
    BookService bookService;

    Book mockBook1 = new Book(1,"1234-1344-1234",
            "Shoe Dog", "Nike", "Non-Fiction",
            20, 14.99);
    Book mockBook2 = new Book(2,"1243-1344-1234",
            "Cat", "Puma", "Non-Fiction",
            0, 14.99);


    @Test
    void shouldGetAllBooks(){
        List<Book> bookList = List.of(mockBook1,mockBook2);
        Pageable pageable = PageRequest.of(0,10);
        Page<Book> mockPage = new PageImpl<>(bookList, pageable, bookList.size());

        when(bookRepo.findAll(isA(Pageable.class))).thenReturn(mockPage);
        Page<BookDTO> bookPage = bookService.getBooks(0,10);

        assertNotNull(bookPage);
        assertEquals(2, bookPage.getTotalElements());
        assertEquals(mockBook1.getIsbn(), bookPage.getContent().getFirst().getIsbn());
    }

    @Test
    void shouldAddBook(){
        when(bookRepo.save(mockBook1)).thenReturn(mockBook1);
        bookService.addBook(mockBook1);

        verify(bookRepo, times(1)).save(mockBook1);
    }

    @Test
    void shouldGetBookByISBN(){
        when(bookRepo.findByIsbn("1234-1344-1234")).thenReturn(Optional.ofNullable(mockBook1));
        BookDTO book = bookService.getBookByISBN("1234-1344-1234");

        assertEquals(mockBook1.getIsbn(), book.getIsbn());
    }

    @Test
    void shouldUpdateBook(){
        when(bookRepo.findByIsbn("1234-1344-1234")).thenReturn(Optional.ofNullable(mockBook1));
        when(bookRepo.save(mockBook1)).thenReturn(mockBook1);
        bookService.updateBook("1234-1344-1234", mockBook1);

       verify(bookRepo, times(1)).save(mockBook1);
    }

    @Test
    void shouldDeleteBook(){
        when(bookRepo.findByIsbn("1234-1344-1234")).thenReturn(Optional.ofNullable(mockBook1));
        doNothing().when(bookRepo).delete(mockBook1);
        bookService.deleteBookByISBN("1234-1344-1234");
        verify(bookRepo, times(1)).delete(mockBook1);
    }

    @Test
    void shouldFindBook(){
        when(bookRepo.findBook("Shoe Dog",
                "Nike", "Non-Fiction")).thenReturn(Optional.ofNullable(mockBook1));
        BookDTO book = bookService.findBook("Shoe Dog",
                "Nike", "Non-Fiction");

        assertEquals(mockBook1.getIsbn(), book.getIsbn());
    }

    @Test
    void shouldGetAllAvailableBooks(){
        List<Book> bookList = List.of(mockBook1);
        Pageable pageable = PageRequest.of(0,10);
        Page<Book> mockPage = new PageImpl<>(bookList, pageable, bookList.size());

        when(bookRepo.findAllByAvailableCopies(pageable)).thenReturn(mockPage);
        Page<BookDTO> books = bookService.getAvailableBooks(0,10);

        assertEquals(mockBook1.getIsbn(), books.getContent().getFirst().getIsbn());
        assertNotEquals(2, books.getContent().size());

    }
}
