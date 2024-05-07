package com.practice.JWTBoard.api.content.service;

import com.practice.JWTBoard.api.content.domain.Content;
import com.practice.JWTBoard.api.content.dto.ContentCreatePostRequestDto;
import com.practice.JWTBoard.api.content.repository.ContentRepository;
import jakarta.servlet.Servlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcProperties;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class ContentCommandService {
    private ContentRepository contentRepository;

    public void save(ContentCreatePostRequestDto contentCreatePostRequestDto) {
        Content newContent = Content.builder()
                .title(contentCreatePostRequestDto.title())
                .contentText(contentCreatePostRequestDto.contentText())
                .build();
        contentRepository.save(newContent);
    }

}
