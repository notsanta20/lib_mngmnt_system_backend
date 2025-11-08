package com.santa.library_mngt_system_backend.repo;

import com.santa.library_mngt_system_backend.model.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepo extends JpaRepository<Member, Long> {
}
