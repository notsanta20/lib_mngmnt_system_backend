package com.santa.member_service.dto;

import com.santa.member_service.model.TransactionStatus;
import lombok.Data;

import java.time.LocalDate;

@Data
public class TransactionDTO {
    private long id;
    private LocalDate issueDate;
    private LocalDate dueDate;
    private LocalDate returnDate;
    private double fineAmount;
    private TransactionStatus status;
    private long memberId;
    private long bookId;
}
