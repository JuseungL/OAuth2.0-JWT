package com.practice.JWTBoard.api.member.dto.request;


public record MemberCreatePostRequestDto (
        String nickname,
        String memberIntro
){}
