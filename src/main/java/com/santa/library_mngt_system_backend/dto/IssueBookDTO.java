package com.santa.library_mngt_system_backend.dto;

import com.santa.library_mngt_system_backend.model.Book;
import lombok.Data;

@Data
public class IssueBookDTO {
    private long memberId;
    private Book book;
}
