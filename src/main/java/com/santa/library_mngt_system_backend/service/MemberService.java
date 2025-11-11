package com.santa.library_mngt_system_backend.service;

import com.santa.library_mngt_system_backend.dto.MemberDTO;
import com.santa.library_mngt_system_backend.dto.TransactionDTO;
import com.santa.library_mngt_system_backend.exception.MemberNotFoundException;
import com.santa.library_mngt_system_backend.model.Member;
import com.santa.library_mngt_system_backend.repo.MemberRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MemberService {

    @Autowired
    private MemberRepo repo;

    public List<MemberDTO> getAllMembers() {
        return repo.findAll()
                .stream()
                .map(MemberDTO::new)
                .toList();
    }

    public void addMember(Member member) {
        repo.save(member);
    }

    public MemberDTO getMemberById(long id) {
        Member member = repo.findById(id).orElseThrow(()->new MemberNotFoundException(id));
        return new MemberDTO(member);
    }

    public void updateMemberById(long id, Member member) {
        Member updateMember = repo.findById(id).orElseThrow(()->new MemberNotFoundException(id));

        updateMember.setName(member.getName());
        updateMember.setEmail(member.getEmail());
        updateMember.setPhone(member.getPhone());
        updateMember.setMembershipType(member.getMembershipType());
        updateMember.setMembershipDate(member.getMembershipDate());
        updateMember.setExpiryDate(member.getExpiryDate());
        updateMember.setTotalFines(member.getTotalFines());
        updateMember.setActive(member.isActive());

        repo.save(updateMember);
    }

    public void deleteMemberById(long id) {
        repo.deleteById(id);
    }

    public List<TransactionDTO> getMemberHistoryById(long id) {
        Member member = repo.findById(id).orElseThrow(()->new MemberNotFoundException(id));

        return member.getTransactions()
                .stream()
                .map(TransactionDTO::new)
                .toList();
    }
}
