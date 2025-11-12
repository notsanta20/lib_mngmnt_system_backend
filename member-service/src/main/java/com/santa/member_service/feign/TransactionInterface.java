package com.santa.member_service.feign;

import com.santa.member_service.dto.TransactionDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient("TRANSACTION-SERVICE")
public interface TransactionInterface {
    @GetMapping("/transactions/history/{memberId}")
    Page<TransactionDTO> getMemberHistoryByMemberId(
            @PathVariable long memberId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size);
}
