package com.example.jorub.service;

import com.example.jorub.domain.Club;
import com.example.jorub.domain.dto.ClubDetailResponseDto;
import com.example.jorub.domain.dto.ClubRequestDto;
import com.example.jorub.domain.dto.ClubResponseDto;
import com.example.jorub.repository.ClubRepository;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Builder
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ClubService {

    private final ClubRepository clubRepository;

    /* 그룹 생성 후 저장 */
    @Transactional
    public ClubResponseDto save(ClubRequestDto clubRequestDto) {
        Club club = clubRequestDto.toEntity();
        clubRepository.save(club);
        return new ClubResponseDto(club);
//        return clubRequestDto;
    }

    /* 그룹 상세 조회 */
    public ClubDetailResponseDto findById(Long clubId) {
        Club club = clubRepository.findById(clubId).orElseThrow(() ->
                new IllegalArgumentException("해당 그룹이 존재하지 않습니다"));

        return new ClubDetailResponseDto(club);
    }

    /* 그룹 전체 리스트 */
    public List<ClubResponseDto> getClubList() {
        List<Club> clubs = clubRepository.findAll();
        List<ClubResponseDto> clubDtoList = new ArrayList<>();
        clubs.forEach(s -> clubDtoList.add(new ClubResponseDto(s)));
        return clubDtoList;
    }
    
    /* 그룹 정보 수정 */
    @Transactional
    public void update(Long clubId, ClubRequestDto clubRequestDto) {
        Club club = clubRepository.findById(clubId).orElseThrow(() ->
                new IllegalArgumentException("해당 그룹이 존재하지 않습니다"));

        club.update(clubRequestDto.getTitle(), clubRequestDto.getContents(),
                clubRequestDto.getUrl());
    }

    /* 그룹 삭제 */
    @Transactional
    public void delete(Long clubId) {
        Club club = clubRepository.findById(clubId).orElseThrow(() ->
                new IllegalArgumentException("해당 그룹이 존재하지 않습니다"));

        clubRepository.delete(club);
    }

    /* 그룹 제목으로 검색 */
    @Transactional
    public List<ClubResponseDto> search(String keyword) {
        List<Club> clubs = clubRepository.findByTitleContaining(keyword);
        List<ClubResponseDto> clubDtoList = new ArrayList<>();

        if(clubs.isEmpty()) return clubDtoList;

        clubs.forEach(s -> clubDtoList.add(new ClubResponseDto(s)));
        return clubDtoList;

    }



}
