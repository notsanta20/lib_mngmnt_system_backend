package com.santa.member_service.service;

import com.santa.member_service.dto.MemberDTO;
import com.santa.member_service.exception.MemberNotFoundException;
import com.santa.member_service.model.Member;
import com.santa.member_service.repo.MemberRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


@Service
public class MemberService {

    @Autowired
    private MemberRepo repo;

    public Page<MemberDTO> getAllMembers(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Member> memberPage = repo.findAll(pageable);

        return memberPage.map(MemberDTO::new);
    }

    public MemberDTO addMember(Member member) {
        Member registerMember = repo.save(member);

        return new MemberDTO(registerMember);
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

    public void updateMemberFine(long id, double fine) {
        Member member = repo.findById(id).orElseThrow(()->new MemberNotFoundException(id));

        member.setTotalFines(member.getTotalFines() + fine);
        repo.save(member);
    }

//    public List<TransactionDTO> getMemberHistoryById(long id) {
//        Member member = repo.findById(id).orElseThrow(()->new MemberNotFoundException(id));
//
//        return member.getTransactions()
//                .stream()
//                .map(TransactionDTO::new)
//                .toList();
//    }
}
