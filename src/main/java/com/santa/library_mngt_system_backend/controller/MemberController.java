package com.santa.library_mngt_system_backend.controller;

import com.santa.library_mngt_system_backend.dto.MemberDTO;
import com.santa.library_mngt_system_backend.dto.TransactionDTO;
import com.santa.library_mngt_system_backend.model.Member;
import com.santa.library_mngt_system_backend.model.Transaction;
import com.santa.library_mngt_system_backend.repo.MemberRepo;
import com.santa.library_mngt_system_backend.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class MemberController {

    @Autowired
    private MemberService service;

    @GetMapping("/members")
    public ResponseEntity<List<MemberDTO>> getAllMembers(){
        List<MemberDTO> membersList = service.getAllMembers();

        return new ResponseEntity<>(membersList, HttpStatus.OK);
    }

    @PostMapping("/members")
    public ResponseEntity<String> addMember(@RequestBody Member member){
        service.addMember(member);

        return new ResponseEntity<>("Member registered successfully", HttpStatus.OK);
    }

    @GetMapping("/members/{id}")
    public ResponseEntity<MemberDTO> getMemberById(@PathVariable long id){
        MemberDTO member = service.getMemberById(id);

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

    @GetMapping("/members/{id}/history")
    public ResponseEntity<List<TransactionDTO>> getMemberHistoryById(@PathVariable long id){
        List<TransactionDTO> memberHistory = service.getMemberHistoryById(id);

        return new ResponseEntity<>(memberHistory, HttpStatus.OK);
    }
}
