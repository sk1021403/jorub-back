package com.example.jorub.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "user_master")
public class User extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_code") // 자동 생성 id
    private Long userCode;

    @Column(name = "kaoao_id")
    private Long kakaoId;

    @Column(name = "kakao_nickname")
    private String kakaoNickname;

    @Column(name = "user_role")
    private String userRole;

    @Builder
    public User(Long kakaoId, String kakaoNickname, String userRole) {

        this.kakaoId = kakaoId;
        this.kakaoNickname = kakaoNickname;
        this.userRole = userRole;
    }

}