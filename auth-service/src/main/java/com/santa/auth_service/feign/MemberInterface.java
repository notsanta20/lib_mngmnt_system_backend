package com.santa.auth_service.feign;

import com.santa.auth_service.model.Member;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient("MEMBER-SERVICE")
public interface MemberInterface {
    @PostMapping("api/members/")
    ResponseEntity<Member> addMember(@RequestBody Member member);

}
