package com.practice.JWTBoard.api.member.domain;

import com.practice.JWTBoard.common.entity.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.*;

import javax.management.relation.Role;
import java.time.LocalDateTime;

import static lombok.AccessLevel.PROTECTED;

@Entity
@Getter
@NoArgsConstructor(access = PROTECTED)
public class Member extends BaseTimeEntity {
    private static final long ACCOUNT_RETENTION_PERIOD = 30L;   // 계정 삭제 후 보유기간 30일로 설정

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String oAuth2Id;

    @Column(nullable = false)
    private String name;

    @Column(unique = true)
    private String nickname;

    @Column(unique = true)
    private String email;

    @Column(name = "member_intro")
    private String memberIntro;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private RoleType roleType;

//    @Column(name = "is_deleted", columnDefinition = "BOOLEAN DEFAULT false")
//    private boolean isDeleted;
//
//    @Column(name = "deleted_at")
//    private LocalDateTime deleteAt;

    @Builder
    public Member(String oAuth2Id, String name, String email, RoleType roleType) {
        this.oAuth2Id = oAuth2Id;
        this.name = name;
        this.email = email;
        this.roleType = roleType;
    }


    public void changeNickname(String newNickname) {
        this.nickname = newNickname;
    }

    public void changeMemberIntro(String newMemberIntro) {
        this.memberIntro = newMemberIntro;
    }

//    public void softDelete() {
//        this.isDeleted = true;
//        this.deleteAt = LocalDateTime.now().plusDays(ACCOUNT_RETENTION_PERIOD);
//    }
}
