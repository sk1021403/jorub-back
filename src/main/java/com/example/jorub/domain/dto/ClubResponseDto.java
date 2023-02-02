package com.example.jorub.domain.dto;

import com.example.jorub.domain.Club;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ClubResponseDto {

    private Long id;
    private String title;
//    private String writer;
    private String createdDate;

    /* entity -> dto */
    public ClubResponseDto (Club club) {
        this.id = club.getId();
        this.title = club.getTitle();
        this.createdDate = club.getCreatedDate();
    }
}
