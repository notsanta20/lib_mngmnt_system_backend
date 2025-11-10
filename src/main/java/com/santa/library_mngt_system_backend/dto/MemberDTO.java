package com.santa.library_mngt_system_backend.dto;

import com.santa.library_mngt_system_backend.model.Member;
import com.santa.library_mngt_system_backend.model.Transaction;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class MemberDTO {
    private long id;
    private String name;
    private String email;
    private long phone;
    private String membershipType;
    private LocalDate membershipDate;
    private LocalDate expiryDate;
    private double totalFines;
    private boolean isActive;
    private List<Long> transactionIds;

    public MemberDTO(Member member){
        this.id = member.getId();
        this.name = member.getName();
        this.email = member.getEmail();
        this.phone = member.getPhone();
        this.membershipType = member.getMembershipType();
        this.membershipDate = member.getMembershipDate();
        this.expiryDate = member.getExpiryDate();
        this.totalFines = member.getTotalFines();
        this.isActive = member.isActive();
        this.transactionIds = member.getTransactions()
                .stream()
                .map(t-> t.getId())
                .collect(Collectors.toList());
    }
}
