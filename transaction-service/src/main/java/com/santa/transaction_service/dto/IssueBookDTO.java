package com.santa.transaction_service.dto;

import lombok.Data;

@Data
public class IssueBookDTO {
    private long memberId;
    private long bookId;
}
