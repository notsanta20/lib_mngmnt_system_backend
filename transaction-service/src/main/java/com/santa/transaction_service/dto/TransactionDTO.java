package com.santa.transaction_service.dto;

import com.santa.transaction_service.model.Transaction;
import com.santa.transaction_service.model.TransactionStatus;
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
        this.memberId = transaction.getMemberId();
        this.bookId = transaction.getBookId();
    }

}
