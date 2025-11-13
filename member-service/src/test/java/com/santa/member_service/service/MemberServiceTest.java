package com.santa.member_service.service;

import com.santa.member_service.dto.TransactionDTO;
import com.santa.member_service.model.Member;
import com.santa.member_service.repo.MemberRepo;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class MemberServiceTest {

    @Mock
    MemberRepo memberRepo;

    @InjectMocks
    MemberService memberService;

    Member mockMember1 = new Member(1,"santa", "santa@ex.com",
            123456789L, "BASIC",
            LocalDate.now(), LocalDate.now(), 0.0, true);

    Member mockMember2 = new Member(2,"rock", "rock@ex.com",
            987654321L, "BASIC",
            LocalDate.now(), LocalDate.now(), 5.0, true);

    @Test
    void shouldGetAllMembers(){
        List<Member> memberList = List.of(mockMember1,mockMember2);
        Pageable pageable = PageRequest.of(0,10);
        Page<Member> mockPage = new PageImpl<>(memberList, pageable, memberList.size());

        when(memberRepo.findAll(isA(Pageable.class))).thenReturn(mockPage);
        Page<Member> members = memberService.getAllMembers(0,10);

        assertEquals(mockMember1.getId(), members.getContent().getFirst().getId());
        assertEquals(2, members.getContent().size());
    }

    @Test
    void shouldAddMember(){
        when(memberRepo.save(mockMember1)).thenReturn(mockMember1);
        memberService.addMember(mockMember1);

        verify(memberRepo, timeout(1)).save(mockMember1);
    }

    @Test
    void shouldGetMemberById(){
        when(memberRepo.findById(1L)).thenReturn(Optional.ofNullable(mockMember1));
        Member member = memberService.getMemberById(1L);

        assertEquals(mockMember1, member);
    }

    @Test
    void shouldUpdateMemberById(){
        when(memberRepo.findById(1L)).thenReturn(Optional.ofNullable(mockMember1));
        when(memberRepo.save(mockMember1)).thenReturn(mockMember1);
        memberService.updateMemberById(1L, mockMember1);

        verify(memberRepo, timeout(1)).findById(1L);
        verify(memberRepo, timeout(1)).save(mockMember1);
    }

    @Test
    void shouldDeleteMemberById(){
        doNothing().when(memberRepo).deleteById(1L);
        memberService.deleteMemberById(1L);

        verify(memberRepo, times(1)).deleteById(1L);
    }

    @Test
    void shouldUpdateMemberFine(){
        when(memberRepo.findById(2L)).thenReturn(Optional.ofNullable(mockMember2));
        when(memberRepo.save(mockMember2)).thenReturn(mockMember2);
        memberService.updateMemberFine(2L, 2.0);

        verify(memberRepo, times(1)).findById(2L);
        verify(memberRepo, times(1)).save(mockMember2);
    }

    //tested on transactions service
    void shouldGetMemberHistoryById(){

    }
}
