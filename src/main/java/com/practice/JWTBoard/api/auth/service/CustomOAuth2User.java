package com.practice.JWTBoard.api.auth.service;

import com.practice.JWTBoard.api.auth.dto.OAuth2MemberDTO;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

public class CustomOAuth2User implements OAuth2User {

    private final OAuth2MemberDTO oAuth2MemberDTO;
    public CustomOAuth2User(OAuth2MemberDTO oAuth2MemberDTO) {
        this.oAuth2MemberDTO = oAuth2MemberDTO;
    }


    @Override
    public Map<String, Object> getAttributes() {
        return null;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> collection = new ArrayList<>();

        collection.add(new GrantedAuthority() {
            @Override
            public String getAuthority() {
                return oAuth2MemberDTO.roleType().toString();
            }
        });
        return collection;
    }

    @Override
    public String getName() {
        return oAuth2MemberDTO.oAuth2Id();
    }
}
