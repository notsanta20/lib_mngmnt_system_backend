package com.santa.member_service.service;

import com.santa.member_service.dto.TransactionDTO;
import com.santa.member_service.exception.MemberNotFoundException;
import com.santa.member_service.feign.TransactionInterface;
import com.santa.member_service.model.Member;
import com.santa.member_service.repo.MemberRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class MemberService {

    @Autowired
    private MemberRepo repo;
    @Autowired
    private TransactionInterface transactionInterface;

    public Page<Member> getAllMembers(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return repo.findAll(pageable);
    }

    public Member addMember(Member member) {
        return repo.save(member);
    }

    public Member getMemberById(long id) {
        return repo.findById(id).orElseThrow(()->new MemberNotFoundException(id));
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

    public void updateMemberFine(long id, double fine) {
        Member member = repo.findById(id).orElseThrow(()->new MemberNotFoundException(id));

        member.setTotalFines(member.getTotalFines() + fine);
        repo.save(member);
    }

    public Page<TransactionDTO> getMemberHistoryById(long id,int page, int size) {
        Optional<Member> member = repo.findById(id);

        if(member.isEmpty()){
            throw new MemberNotFoundException(id);
        }

        return transactionInterface.getMemberHistoryByMemberId(id, page, size);
    }
}
