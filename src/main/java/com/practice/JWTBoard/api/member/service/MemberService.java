package com.practice.JWTBoard.api.member.service;

import com.practice.JWTBoard.api.member.domain.Member;
import com.practice.JWTBoard.api.member.dto.request.MemberCreatePostRequestDto;
import com.practice.JWTBoard.api.member.dto.request.MemberUpdatePatchRequestDto;
import com.practice.JWTBoard.api.member.dto.response.MemberGetProfileResponseDto;
import com.practice.JWTBoard.api.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class MemberService {
    private final MemberRepository memberRepository;
    public void save(MemberCreatePostRequestDto memberCreatePostRequestDto) {
        Member newMember = Member.builder()
                            .name(memberCreatePostRequestDto.nickname())
                            .build();
        memberRepository.save(newMember);
    }

    public void updateMemberProfile(Long memberId, MemberUpdatePatchRequestDto memberUpdatePatchRequestDto) {
        Member member = memberRepository.findMemberByIdOrThrow(memberId);

        member.changeNickname(memberUpdatePatchRequestDto.nickname());
        member.changeMemberIntro(memberUpdatePatchRequestDto.memberIntro());
        // Dirty Checking
    }

    public MemberGetProfileResponseDto getMemberProfile(Long memberId) {
        Member member = memberRepository.findMemberByIdOrThrow(memberId);
        return MemberGetProfileResponseDto.of(member);
    }
}
