package com.santa.member_service.controller;


import com.santa.member_service.dto.TransactionDTO;
import com.santa.member_service.model.Member;
import com.santa.member_service.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class MemberController {

    @Autowired
    private MemberService service;

    @GetMapping("/members")
    public Page<Member> getAllMembers(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ){
        return service.getAllMembers(page, size);
    }

    @PostMapping("/members")
    public ResponseEntity<Member> addMember(@RequestBody Member member){
        Member registerMember = service.addMember(member);

        return new ResponseEntity<>(registerMember, HttpStatus.OK);
    }

    @GetMapping("/members/{id}")
    public ResponseEntity<Member> getMemberById(@PathVariable long id){
        Member member = service.getMemberById(id);

        return new ResponseEntity<>(member, HttpStatus.OK);
    }

    @PutMapping("/members/{id}")
    public ResponseEntity<String> updateMemberById(@PathVariable long id, @RequestBody Member member){
        service.updateMemberById(id, member);

        return new ResponseEntity<>("Member updated successfully", HttpStatus.OK);
    }

    @DeleteMapping("/members/{id}")
    public ResponseEntity<String> deleteMemberById(@PathVariable long id){
        service.deleteMemberById(id);

        return new ResponseEntity<>("Member deleted successfully", HttpStatus.OK);
    }

    @PutMapping("/members/{id}/fine")
    public ResponseEntity<String> updateMemberFine(@PathVariable long id, @RequestBody double fine){
        service.updateMemberFine(id, fine);

        return new ResponseEntity<>("Member updated successfully", HttpStatus.OK);
    }

    @GetMapping("/members/{id}/history")
    public Page<TransactionDTO> getMemberHistoryById(
            @PathVariable long id,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size){

        return service.getMemberHistoryById(id, page, size);
    }
}
