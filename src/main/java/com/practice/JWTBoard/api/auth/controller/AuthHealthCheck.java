package com.practice.JWTBoard.api.auth.controller;

import com.practice.JWTBoard.api.member.dto.request.MemberCreatePostRequestDto;
import com.practice.JWTBoard.common.response.ApiResponse;
import com.practice.JWTBoard.common.response.SuccessStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
public class AuthHealthCheck {
    @PostMapping("/create")
    public String checkAuth(Principal principal) {
        System.out.println("principal.getName() = " + principal.getName());
        return "Success";
    }
}
