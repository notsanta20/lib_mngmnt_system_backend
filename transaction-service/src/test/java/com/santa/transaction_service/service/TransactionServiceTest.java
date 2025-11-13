package com.santa.transaction_service.service;

import com.santa.transaction_service.dto.BookDTO;
import com.santa.transaction_service.dto.MemberDTO;
import com.santa.transaction_service.dto.TransactionDTO;
import com.santa.transaction_service.feign.BookInterface;
import com.santa.transaction_service.feign.MemberInterface;
import com.santa.transaction_service.model.Book;
import com.santa.transaction_service.model.Member;
import com.santa.transaction_service.model.Transaction;
import com.santa.transaction_service.model.TransactionStatus;
import com.santa.transaction_service.repo.TransactionRepo;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TransactionServiceTest {

    @Mock
    TransactionRepo transactionRepo;

    @Mock
    BookInterface bookInterface;

    @Mock
    MemberInterface memberInterface;

    @InjectMocks
    TransactionService transactionService;

    Transaction mockTransaction1 = new Transaction(1, LocalDate.now(),
            LocalDate.now().minusDays(10),null, 2.0, TransactionStatus.ISSUED,
            LocalDate.now(),LocalDate.now(),1,1);


    @Test
    void shouldIssueBook(){
        BookDTO mockBook = new BookDTO(new Book());
        MemberDTO mockMember = new MemberDTO(new Member());

        mockBook.setId(1);
        mockBook.setAvailableCopies(20);
        when(bookInterface.getBooks(1)).thenReturn(new ResponseEntity<>(mockBook, HttpStatus.OK));

        mockMember.setId(1);
        when(memberInterface.getMemberById(1)).thenReturn(new ResponseEntity<>(mockMember, HttpStatus.OK));

        transactionService.issueBook(1,1);

        verify(transactionRepo, times(1)).save(any(Transaction.class));
    }

    @Test
    void shouldReturnBook(){
        when(transactionRepo.findById(1L)).thenReturn(Optional.ofNullable(mockTransaction1));
        when(transactionRepo.save(mockTransaction1)).thenReturn(mockTransaction1);

        transactionService.returnBook(1L);

        verify(transactionRepo, times(1)).findById(1L);
        verify(transactionRepo, times(1)).save(mockTransaction1);
    }

    @Test
    void shouldGetAllTransactions(){
        List<Transaction> mockList = List.of(mockTransaction1);

        when(transactionRepo.findAll()).thenReturn(mockList);

        List<TransactionDTO> transactionDTOS = transactionService.getAllTransactions();

        assertEquals(mockTransaction1.getId(), transactionDTOS.getFirst().getId());
    }

    @Test
    void shouldGetOverdueTransactions(){
        List<Transaction> mockList = List.of(mockTransaction1);
        when(transactionRepo.findAll()).thenReturn(mockList);

        List<TransactionDTO> transactionDTOS = transactionService.getOverdueTransactions();

        assertEquals(mockTransaction1.getDueDate(), transactionDTOS.getFirst().getDueDate());
    }

    @Test
    void shouldGetMemberHistoryByMemberId(){
        List<Transaction> transactionList = List.of(mockTransaction1);
        Pageable pageable = PageRequest.of(0,10);
        Page<Transaction> mockPage = new PageImpl<>(transactionList, pageable, transactionList.size());

        when(transactionRepo.findByMemberId(1L,pageable)).thenReturn(mockPage);
        Page<TransactionDTO> transactionDTOPage = transactionService
                .getMemberHistoryByMemberId(1L,0,10);

        assertEquals(mockTransaction1.getMemberId(), transactionDTOPage.getContent().getFirst().getId());
        assertEquals(1, transactionDTOPage.getContent().size());
    }
}
