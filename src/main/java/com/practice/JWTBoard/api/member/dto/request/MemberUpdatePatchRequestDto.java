package com.practice.JWTBoard.api.member.dto.request;

public record MemberUpdatePatchRequestDto(
        String nickname,
        String memberIntro
) {}
