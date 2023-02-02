package com.example.jorub.controller;

import com.example.jorub.domain.Club;
import com.example.jorub.domain.dto.ClubDetailResponseDto;
import com.example.jorub.domain.dto.ClubRequestDto;
import com.example.jorub.domain.dto.ClubResponseDto;
import com.example.jorub.service.ClubService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/clubs")
@RestController
public class ClubController {

    private final ClubService clubService;

    /* 새로운 그룹 생성 */
    @PostMapping("/new")
    public ResponseEntity<Long> createClub (@RequestBody ClubRequestDto clubRequestDto) {
        return ResponseEntity.ok(clubService.save(clubRequestDto));
    }

    /* 그룹 상세 조회 */
    @GetMapping("/{clubId}")
    public ResponseEntity<ClubDetailResponseDto> getClubDetail(@PathVariable Long clubId) {
        return ResponseEntity.ok(clubService.findById(clubId));
    }

    /* 전체 그룹 리스트 조회 */
    @GetMapping
    public ResponseEntity<List<ClubResponseDto>> getClubList() {
        return ResponseEntity.ok(clubService.getClubList());
    }

    /* 그룹 정보 업데이트 */
    @PutMapping("/{clubId}")
    public ResponseEntity<Long> updateClub(@PathVariable Long clubId, @RequestBody ClubRequestDto clubRequestDto) {
        clubService.update(clubId, clubRequestDto);
        return ResponseEntity.ok(clubId);
    }

    /* 그룹 삭제 */
    @DeleteMapping("/{clubId}")
    public ResponseEntity<Long> deleteClub(@PathVariable Long clubId) {
        clubService.delete(clubId);
        return ResponseEntity.ok(clubId);
    }

    /* 그룹 검색 */
    @GetMapping("/search")
    public ResponseEntity<List<ClubResponseDto>> search(String keyword) {
        return ResponseEntity.ok(clubService.search(keyword));
    }



}
