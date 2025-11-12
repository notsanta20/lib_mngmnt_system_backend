package com.santa.transaction_service.repo;

import com.santa.transaction_service.model.Transaction;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionRepo extends JpaRepository<Transaction, Long> {
    Page<Transaction> findByMemberId(long memberId, Pageable pageable);
}
