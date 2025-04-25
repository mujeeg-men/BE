package com.project.ShareBook.controller;

import com.project.ShareBook.dto.LoginRequestDto;
import com.project.ShareBook.dto.log.LogRequestDto;
import com.project.ShareBook.dto.log.LogResponseDto;
import com.project.ShareBook.dto.log.LogUpdateRequestDto;
import com.project.ShareBook.jwt.CustomUserDetails;
import com.project.ShareBook.service.ReadingLogService;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/log")
@RequiredArgsConstructor
public class ReadingLogController {
    private final ReadingLogService readingLogService;

    @PostMapping("/{saveBookId}")
    public ResponseEntity<String> createLog(
        @AuthenticationPrincipal CustomUserDetails userDetails,
        @PathVariable Long saveBookId,
        @RequestBody LogRequestDto dto) {
        Long userId = userDetails.getUser().getId();
        readingLogService.createReadingLog(userId,saveBookId,dto);
        return ResponseEntity.ok("기록 저장 완료");
    }
    @PutMapping("/{logId}")
    public ResponseEntity<String> updateLog(
        @AuthenticationPrincipal CustomUserDetails userDetails,
        @PathVariable Long logId,
        @RequestBody LogUpdateRequestDto dto){

        Long userId = userDetails.getUser().getId();
        readingLogService.updateReadingLog(userId,logId,dto);
        return ResponseEntity.ok("소감문 수정이 완료 되었습니다");
    }

    @GetMapping("/by-date")
    public ResponseEntity<List<LogResponseDto>> getLogsByDate(
        @AuthenticationPrincipal CustomUserDetails userDetails,
        @RequestParam String date // YYYY-MM-DD 형식
    ) {
        Long userId = userDetails.getUser().getId();
        List<LogResponseDto> logs = readingLogService.getLogsByDate(userId, LocalDate.parse(date));
        return ResponseEntity.ok(logs);
    }

}
