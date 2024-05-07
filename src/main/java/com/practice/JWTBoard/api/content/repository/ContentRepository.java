package com.practice.JWTBoard.api.content.repository;

import com.practice.JWTBoard.api.content.domain.Content;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContentRepository extends JpaRepository<Content, Long> {
}
