package com.santa.library_mngt_system_backend.repo;

import com.santa.library_mngt_system_backend.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepo extends JpaRepository<Book, Long> {
    public Book findByIsbn(String isbn);

    @Query("SELECT b FROM Book b WHERE LOWER(b.title) = LOWER(:title) AND LOWER(b.author) = LOWER(:author) AND LOWER(b.category) = LOWER(:category)")
    public Book findBook(@Param("title") String title, @Param("author") String author, @Param("category") String category);

    @Query("SELECT b FROM Book b WHERE b.availableCopies > 1")
    public List<Book> findAllAvailableBooks();
}
