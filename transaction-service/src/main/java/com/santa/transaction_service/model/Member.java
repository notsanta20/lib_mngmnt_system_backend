package com.santa.transaction_service.model;

import lombok.Data;
import java.time.LocalDate;

@Data
public class Member {
    private long id;
    private String name;
    private String email;
    private long phone;
    private String membershipType;
    private LocalDate membershipDate;
    private LocalDate expiryDate;
    private double totalFines;
    private boolean isActive;
}
