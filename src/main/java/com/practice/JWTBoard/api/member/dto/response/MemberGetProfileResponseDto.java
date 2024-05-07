package com.practice.JWTBoard.api.member.dto.response;

import com.practice.JWTBoard.api.member.domain.Member;

public record MemberGetProfileResponseDto(
        Long memberId,
        String nickname,
        String memberIntro
) {
    public static MemberGetProfileResponseDto of(Member member){
        return new MemberGetProfileResponseDto(
                member.getId(),
                member.getNickname(),
                member.getMemberIntro()
        );
    }
}
