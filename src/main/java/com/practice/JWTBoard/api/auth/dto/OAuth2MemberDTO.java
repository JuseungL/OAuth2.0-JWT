package com.practice.JWTBoard.api.auth.dto;


public record OAuth2MemberDTO(
        String oAuth2Id,
        String roleType
) {
    public static OAuth2MemberDTO fromResponse(String oAuth2Id, String roleType) {
        return new OAuth2MemberDTO(oAuth2Id,roleType);
    }
}
