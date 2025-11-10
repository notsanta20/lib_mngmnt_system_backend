package com.santa.library_mngt_system_backend.service;

import com.santa.library_mngt_system_backend.dto.TransactionDTO;
import com.santa.library_mngt_system_backend.model.Book;
import com.santa.library_mngt_system_backend.model.Member;
import com.santa.library_mngt_system_backend.model.Transaction;
import com.santa.library_mngt_system_backend.model.TransactionStatus;
import com.santa.library_mngt_system_backend.repo.MemberRepo;
import com.santa.library_mngt_system_backend.repo.TransactionRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TransactionService {

    @Autowired
    private TransactionRepo repo;
    @Autowired
    private MemberRepo memberRepo;

    public void issueBook(long memberId,Book book) {
        Transaction transaction = new Transaction();

        transaction.setIssueDate(LocalDate.now());
        transaction.setDueDate(LocalDate.now());
        transaction.setReturnDate(LocalDate.now());
        transaction.setFineAmount(0);
        transaction.setStatus(TransactionStatus.ISSUED);
        transaction.setCreatedAt(LocalDate.now());
        transaction.setUpdatedAt(LocalDate.now());

        Member member = memberRepo.findById(memberId).orElse(new Member());

        transaction.setMember(member);
        transaction.setBook(book);
    }

    public Transaction returnBook(long transactionId) {
        Transaction transaction = repo.findById(transactionId).orElse(new Transaction());
        transaction.setReturnDate(LocalDate.now());

        if(transaction.getReturnDate().isAfter(transaction.getDueDate())){
            transaction.setFineAmount(1);
        }

        System.out.println(transaction.getDueDate() + " " + transaction.getReturnDate());
        return transaction;
    }

    public List<TransactionDTO> getAllTransactions() {
        return repo.findAll()
                .stream()
                .map(TransactionDTO::new)
                .collect(Collectors.toList());
    }

    public List<TransactionDTO> getOverdueTransactions() {
        return repo.findAll()
                .stream()
                .filter(t-> t.getReturnDate() == null && t.getDueDate().isBefore(LocalDate.now()))
                .map(TransactionDTO::new)
                .collect(Collectors.toList());
    }
}
