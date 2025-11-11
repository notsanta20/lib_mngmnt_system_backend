package com.santa.library_mngt_system_backend.dto;

import com.santa.library_mngt_system_backend.model.Transaction;
import com.santa.library_mngt_system_backend.model.TransactionStatus;
import jakarta.persistence.*;
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

    public TransactionDTO(Transaction transaction){
        this.id = transaction.getId();
        this.issueDate = transaction.getIssueDate();
        this.dueDate = transaction.getDueDate();
        this.returnDate = transaction.getReturnDate();
        this.fineAmount = transaction.getFineAmount();
        this.status = transaction.getStatus();
        this.memberId = transaction.getMember().getId();
        this.bookId = transaction.getBook().getId();
    }

}
