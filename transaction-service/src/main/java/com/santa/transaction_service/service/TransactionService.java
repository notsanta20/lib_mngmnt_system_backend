package com.santa.transaction_service.service;

import com.santa.transaction_service.dto.BookDTO;
import com.santa.transaction_service.dto.MemberDTO;
import com.santa.transaction_service.dto.TransactionDTO;
import com.santa.transaction_service.exception.TransactionNotFoundException;
import com.santa.transaction_service.feign.BookInterface;
import com.santa.transaction_service.feign.MemberInterface;
import com.santa.transaction_service.model.Transaction;
import com.santa.transaction_service.model.TransactionStatus;
import com.santa.transaction_service.repo.TransactionRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
public class TransactionService {

    @Autowired
    private TransactionRepo repo;
    @Autowired
    private MemberInterface memberInterface;
    @Autowired
    private BookInterface transactionInterface;

    public void issueBook(long memberId,long bookId) {
        BookDTO book = transactionInterface.getBooks(bookId).getBody();
        MemberDTO member = memberInterface.getMemberById(memberId).getBody();

        if(book != null && member != null && book.getAvailableCopies() != 0){
            Transaction transaction = new Transaction();

            transaction.setIssueDate(LocalDate.now());
            transaction.setDueDate(LocalDate.now());
            transaction.setReturnDate(LocalDate.now());
            transaction.setFineAmount(0);
            transaction.setStatus(TransactionStatus.ISSUED);
            transaction.setCreatedAt(LocalDate.now());
            transaction.setUpdatedAt(LocalDate.now());
            transaction.setMemberId(memberId);
            transaction.setBookId(bookId);

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
            memberInterface.updateMemberFine(transaction.getMemberId(), fine);
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

    public Page<TransactionDTO> getMemberHistoryByMemberId(long memberId, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Transaction> transactionsPage = repo.findByMemberId(memberId, pageable);

        return transactionsPage.map(TransactionDTO::new);
    }
}
