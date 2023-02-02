package com.example.jorub.domain.dto;

import com.example.jorub.domain.Club;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ClubDetailResponseDto {

    private Long id;
    private String title;
    private String writer;
    private String contents;
    private String url;
    private String createdDate;

    /* entity -> dto */
    public ClubDetailResponseDto (Club club) {
        this.id = club.getId();
        this.title = club.getTitle();
        this.contents = club.getContents();
        this.url = club.getUrl();
        this.createdDate = club.getCreatedDate();
    }
}
