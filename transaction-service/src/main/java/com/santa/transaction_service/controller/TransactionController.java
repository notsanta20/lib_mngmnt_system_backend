package com.santa.transaction_service.controller;

import com.santa.transaction_service.dto.IssueBookDTO;
import com.santa.transaction_service.dto.TransactionDTO;
import com.santa.transaction_service.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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
        service.issueBook(issueBookDTO.getMemberId(), issueBookDTO.getBookId());

        return new ResponseEntity<>("Book issued successfully", HttpStatus.OK);
    }

    @PostMapping("/transactions/return")
    public ResponseEntity<String> returnBook(@RequestBody long transactionId){
        service.returnBook(transactionId);

        return new ResponseEntity<>("Book returned", HttpStatus.OK);
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

    @GetMapping("/transactions/history/{memberId}")
    public Page<TransactionDTO> getMemberHistoryByMemberId(
            @PathVariable long memberId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size){

        return service.getMemberHistoryByMemberId(memberId, page, size);
    }
}
