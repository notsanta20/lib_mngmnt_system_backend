package com.santa.transaction_service.feign;

import com.santa.transaction_service.dto.BookDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient("BOOK-SERVICE")
public interface BookInterface {
    @GetMapping("api/books/{id}")
    public ResponseEntity<BookDTO> getBooks(@PathVariable long id);
}
