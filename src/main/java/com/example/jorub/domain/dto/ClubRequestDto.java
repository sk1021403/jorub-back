package com.example.jorub.domain.dto;

import com.example.jorub.domain.Club;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ClubRequestDto {

//    private Long id;
    private String title;
    private String contents;
    private String url;
    private String createdDate;


    /* dto -> entity */
    public Club toEntity() {
        return Club.builder()
//                .id(id)
                .title(title)
                .contents(contents)
                .url(url)
                .build();
    }
}
