package com.practice.JWTBoard.common.jwt;

import com.practice.JWTBoard.api.auth.dto.OAuth2MemberDTO;
import com.practice.JWTBoard.api.auth.service.CustomOAuth2User;
import com.practice.JWTBoard.api.member.domain.Member;
import com.practice.JWTBoard.api.member.domain.RoleType;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@RequiredArgsConstructor
public class JWTFilter extends OncePerRequestFilter {
    private final JWTUtil jwtUtil;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        // Authorization 헤더에서 JWT 토큰 추출
        String authorization = request.getHeader("Authorization");
        if (authorization != null && authorization.startsWith("Bearer ")) {
            String token = authorization.substring(7); // "Bearer " 이후의 부분만 추출

            // 토큰 소멸 시간 검증
            if (jwtUtil.isExpired(token)) {
                System.out.println("Token expired");
                filterChain.doFilter(request, response);
                return;
            }

            // 토큰에서 username과 role 획득
            String oAuth2Id = jwtUtil.getOAuth2Id(token);
            String role = jwtUtil.getRole(token);

            OAuth2MemberDTO JwtInfo = OAuth2MemberDTO.fromResponse(oAuth2Id, role);
            // UserDetails에 회원 정보 객체 담기
            CustomOAuth2User customOAuth2User = new CustomOAuth2User(JwtInfo);

            // 스프링 시큐리티 인증 토큰 생성
            Authentication authToken = new UsernamePasswordAuthenticationToken(customOAuth2User, null, customOAuth2User.getAuthorities());
            // 세션에 사용자 등록
            SecurityContextHolder.getContext().setAuthentication(authToken);
        }

        filterChain.doFilter(request, response);
    }

}
