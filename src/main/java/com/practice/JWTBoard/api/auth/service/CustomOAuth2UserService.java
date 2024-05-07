package com.practice.JWTBoard.api.auth.service;

import com.practice.JWTBoard.api.auth.dto.response.GoogleResponse;
import com.practice.JWTBoard.api.auth.dto.response.NaverResponse;
import com.practice.JWTBoard.api.auth.dto.OAuth2MemberDTO;
import com.practice.JWTBoard.api.auth.dto.response.OAuth2Response;
import com.practice.JWTBoard.api.member.domain.Member;
import com.practice.JWTBoard.api.member.domain.RoleType;
import com.practice.JWTBoard.api.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomOAuth2UserService extends DefaultOAuth2UserService {
    private final MemberRepository memberRepository;


    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2User oAuth2User = super.loadUser(userRequest); // 부모 클래스의 loadUser 메소드 사용
        System.out.println(oAuth2User.getAttributes());

        String registrationId = userRequest.getClientRegistration().getRegistrationId(); // Provider 얻기

        OAuth2Response oAuth2Response = null;
        // 각 Provider에 따라 응답 규격이 다름.
        if (registrationId.equals("naver")) {
            oAuth2Response = new NaverResponse(oAuth2User.getAttributes());

        }
        else if (registrationId.equals("google")) {
            oAuth2Response = new GoogleResponse(oAuth2User.getAttributes());
        }
        else {

            return null;
        }
        String oAuth2Id = oAuth2Response.getProvider()+" "+oAuth2Response.getProviderId();
        Optional<Member> existData = memberRepository.findByoAuth2Id(oAuth2Id);

        if (!existData.isPresent()) {
            // 존재하지 않는 경우
            Member newMember = Member.builder()
                    .oAuth2Id(oAuth2Id)
                    .name(oAuth2Response.getName())
                    .email(oAuth2Response.getEmail())
                    .roleType(RoleType.USER)
                    .build();
            memberRepository.save(newMember);
        } else {
            // 존재할 경우 업데이트(더티 체킹) 등 추가 처리
            Member existingMember = existData.get();
            existingMember.changeNickname(oAuth2Response.getName()); // 닉네임 변경 등 추가 작업 가능
        }
        OAuth2MemberDTO oAuth2MemberDTO = OAuth2MemberDTO.fromResponse(oAuth2Id, RoleType.USER.toString());
        return new CustomOAuth2User(oAuth2MemberDTO);
    }
}
