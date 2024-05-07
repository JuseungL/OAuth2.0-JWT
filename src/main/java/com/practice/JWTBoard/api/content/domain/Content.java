package com.practice.JWTBoard.api.content.domain;

import com.practice.JWTBoard.api.comment.domain.Comment;
import com.practice.JWTBoard.api.member.domain.Member;
import com.practice.JWTBoard.common.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.antlr.v4.runtime.misc.NotNull;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Content extends BaseEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @NotNull
    private String title;

    @NotNull
    @Lob
    private String contentText;

    @Enumerated(EnumType.STRING)
    private ContentCategory category;

    @OneToMany(mappedBy = "content", cascade =  CascadeType.REMOVE)
    private List<LikeContent> likeContents = new ArrayList<>();

    @OneToMany(mappedBy = "content", cascade = CascadeType.REMOVE)
    private List<Comment> comments = new ArrayList<>();

    @Builder
    public Content(Member member, String title, String contentText) {
        this.member = member;
        this.title = title;
        this.contentText = contentText;
    }
}
