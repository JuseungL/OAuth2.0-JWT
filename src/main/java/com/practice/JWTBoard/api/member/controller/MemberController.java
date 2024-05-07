package com.practice.JWTBoard.api.member.controller;

import com.practice.JWTBoard.api.member.dto.request.MemberCreatePostRequestDto;
import com.practice.JWTBoard.api.member.dto.request.MemberUpdatePatchRequestDto;
import com.practice.JWTBoard.api.member.dto.response.MemberGetProfileResponseDto;
import com.practice.JWTBoard.api.member.service.MemberService;
import com.practice.JWTBoard.common.response.ApiResponse;
import com.practice.JWTBoard.common.response.SuccessStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/member")
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;

    @PostMapping("/create")
    public ResponseEntity<ApiResponse<Object>> createMember(Principal principal, @RequestBody MemberCreatePostRequestDto memberCreatePostRequestDto) {
        memberService.save(memberCreatePostRequestDto);
        return ApiResponse.success(SuccessStatus.CREATE_MEMBER_SUCCESS);
    }

    @PatchMapping("/update")
    public ResponseEntity<ApiResponse<Object>> updateMember(@RequestBody MemberUpdatePatchRequestDto memberUpdatePatchRequestDto) {
        Long memberId = 1L;
//        Long memberId = MemberUtil.getMemberId(principal);
        memberService.updateMemberProfile(memberId, memberUpdatePatchRequestDto);
        return ApiResponse.success(SuccessStatus.PATCH_MEMBER_PROFILE);
    }

    @GetMapping("/profile/{memberId}")
    public ResponseEntity<ApiResponse<MemberGetProfileResponseDto>> getMemberProfile(Principal principal, @PathVariable(name = "memberId") Long memberId) {
        System.out.println("principal.getName() = " + principal.getName());

        return ApiResponse.success(SuccessStatus.GET_PROFILE_SUCCESS, memberService.getMemberProfile(memberId));
    }
}
