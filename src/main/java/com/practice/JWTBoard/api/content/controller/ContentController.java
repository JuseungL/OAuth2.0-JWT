package com.practice.JWTBoard.api.content.controller;

import com.practice.JWTBoard.api.content.dto.ContentCreatePostRequestDto;
import com.practice.JWTBoard.api.content.service.ContentCommandService;
import com.practice.JWTBoard.common.response.ApiResponse;
import com.practice.JWTBoard.common.response.SuccessStatus;
import jakarta.persistence.Access;
import jakarta.servlet.FilterChain;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/content")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class ContentController {
    private ContentCommandService contentCommandService;
    @PostMapping("/create")
    public ResponseEntity<ApiResponse<Object>> createContent(@RequestBody ContentCreatePostRequestDto contentCreatePostRequestDto){
        contentCommandService.save(contentCreatePostRequestDto);
        return ApiResponse.success(SuccessStatus. POST_CONTENT_SUCCESS);
    }

}
