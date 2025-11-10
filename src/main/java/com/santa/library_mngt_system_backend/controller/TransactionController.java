package com.santa.library_mngt_system_backend.controller;

import com.santa.library_mngt_system_backend.dto.IssueBookDTO;
import com.santa.library_mngt_system_backend.dto.TransactionDTO;
import com.santa.library_mngt_system_backend.model.Transaction;
import com.santa.library_mngt_system_backend.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class TransactionController {

    @Autowired
    private TransactionService service;

    @PostMapping("/transactions/issue")
    public ResponseEntity<String> issueBook(@RequestBody IssueBookDTO issueBookDTO){
        service.issueBook(issueBookDTO.getMemberId(), issueBookDTO.getBook());

        return new ResponseEntity<>("Book issued successfully", HttpStatus.OK);
    }

    @PostMapping("/transactions/return")
    public ResponseEntity<Transaction> returnBook(@RequestBody long transactionId){
        Transaction transaction = service.returnBook(transactionId);

        return new ResponseEntity<>(transaction, HttpStatus.OK);
    }

    @GetMapping("/transactions")
    public ResponseEntity<List<TransactionDTO>> getAllTransactions(){
        List<TransactionDTO> transactions = service.getAllTransactions();

        return new ResponseEntity<>(transactions, HttpStatus.OK);
    }

    @GetMapping("/transactions/overdue")
    public ResponseEntity<List<TransactionDTO>> getOverdueBooks(){
        List<TransactionDTO> overdueTransactions = service.getOverdueTransactions();

        return new ResponseEntity<>(overdueTransactions, HttpStatus.OK);
    }
}
