package com.santa.library_mngt_system_backend.service;

import com.santa.library_mngt_system_backend.dto.TransactionDTO;
import com.santa.library_mngt_system_backend.exception.BookNotFoundException;
import com.santa.library_mngt_system_backend.exception.MemberNotFoundException;
import com.santa.library_mngt_system_backend.exception.TransactionNotFoundException;
import com.santa.library_mngt_system_backend.model.Book;
import com.santa.library_mngt_system_backend.model.Member;
import com.santa.library_mngt_system_backend.model.Transaction;
import com.santa.library_mngt_system_backend.model.TransactionStatus;
import com.santa.library_mngt_system_backend.repo.BookRepo;
import com.santa.library_mngt_system_backend.repo.MemberRepo;
import com.santa.library_mngt_system_backend.repo.TransactionRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
public class TransactionService {

    @Autowired
    private TransactionRepo repo;
    @Autowired
    private MemberRepo memberRepo;
    @Autowired
    private BookRepo bookRepo;

    public void issueBook(long memberId,long bookId) {
        Book book = bookRepo.findById(bookId).orElseThrow(()-> new BookNotFoundException(bookId));
        Member member = memberRepo.findById(memberId).orElseThrow(()-> new MemberNotFoundException(memberId));

        if(book.getAvailableCopies() != 0){
            Transaction transaction = new Transaction();

            transaction.setIssueDate(LocalDate.now());
            transaction.setDueDate(LocalDate.now());
            transaction.setReturnDate(LocalDate.now());
            transaction.setFineAmount(0);
            transaction.setStatus(TransactionStatus.ISSUED);
            transaction.setCreatedAt(LocalDate.now());
            transaction.setUpdatedAt(LocalDate.now());
            transaction.setMember(member);
            transaction.setBook(book);

            repo.save(transaction);
            book.setAvailableCopies(book.getAvailableCopies()-1);
        }
    }

    public void returnBook(long transactionId) {
        Transaction transaction = repo.findById(transactionId).orElseThrow(()-> new TransactionNotFoundException(transactionId));
        transaction.setReturnDate(LocalDate.now());
        transaction.setUpdatedAt(LocalDate.now());

        long daysLate = ChronoUnit.DAYS.between(transaction.getDueDate(),transaction.getReturnDate());
        if (daysLate > 0){
            double finePerDay = 2.0;
            double fine = daysLate * finePerDay;
            transaction.setFineAmount(fine);
            Member member = transaction.getMember();
            member.setTotalFines(member.getTotalFines() + fine);
        }
        repo.save(transaction);
    }

    public List<TransactionDTO> getAllTransactions() {
        return repo.findAll()
                .stream()
                .map(TransactionDTO::new)
                .toList();
    }

    public List<TransactionDTO> getOverdueTransactions() {
        return repo.findAll()
                .stream()
                .filter(t-> t.getReturnDate() == null && t.getDueDate().isBefore(LocalDate.now()))
                .map(TransactionDTO::new)
                .toList();
    }
}
