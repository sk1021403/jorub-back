package com.example.jorub.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Getter
public class Club extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 500, nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT", nullable = true)
    private String contents;

    @Column(nullable = false)
    private String url;

    /* 그룹 수정 메소드 */
    public void update(String title, String contents, String url) {
        this.title = title;
        this.contents = contents;
        this.url = url;
    }

}
