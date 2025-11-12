package com.santa.transaction_service.feign;

import com.santa.transaction_service.dto.MemberDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient("MEMBER-SERVICE")
public interface MemberInterface {

    @GetMapping("api/members/{id}")
    public ResponseEntity<MemberDTO> getMemberById(@PathVariable long id);

    @PutMapping("api/members/{id}/fine")
    public ResponseEntity<String> updateMemberFine(@PathVariable long id, @RequestBody double fine);
}
