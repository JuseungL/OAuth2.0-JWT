package com.practice.JWTBoard.api.content.dto;

import jakarta.validation.constraints.NotBlank;

public record ContentCreatePostRequestDto(
        String title,
        @NotBlank String contentText

){}
