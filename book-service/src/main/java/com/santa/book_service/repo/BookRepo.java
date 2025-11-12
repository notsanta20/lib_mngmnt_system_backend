package com.santa.book_service.repo;

import com.santa.book_service.model.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BookRepo extends JpaRepository<Book, Long> {
    Optional<Book> findByIsbn(String isbn);

    @Query("SELECT b FROM Book b WHERE LOWER(b.title) = LOWER(:title) AND LOWER(b.author) = LOWER(:author) AND LOWER(b.category) = LOWER(:category)")
    Optional<Book>  findBook(@Param("title") String title, @Param("author") String author, @Param("category") String category);

    @Query("SELECT b FROM Book b WHERE b.availableCopies > 0")
    Page<Book> findAllByAvailableCopies(Pageable pageable);
}
