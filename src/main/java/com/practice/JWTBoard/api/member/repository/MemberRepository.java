package com.practice.JWTBoard.api.member.repository;

import com.practice.JWTBoard.api.member.domain.Member;
import com.practice.JWTBoard.common.response.ErrorStatus;
import com.practice.JWTBoard.common.exception.NotFoundException;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {
    Optional<Member> findMemberById(Long memberId);
    default Member findMemberByIdOrThrow(Long memberId) {
        return findMemberById(memberId)
                .orElseThrow(() -> new NotFoundException(ErrorStatus.NOT_FOUND_MEMBER.getMessage()));
    }
    Optional<Member> findByoAuth2Id(String oAuth2Id);

}
