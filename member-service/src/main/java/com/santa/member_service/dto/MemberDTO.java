package com.santa.member_service.dto;

import com.santa.member_service.model.Member;
import lombok.Data;

import java.time.LocalDate;

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
    }
}
