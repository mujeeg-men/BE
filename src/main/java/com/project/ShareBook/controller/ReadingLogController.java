package com.project.ShareBook.controller;

import com.project.ShareBook.common.ApiResponse;
import com.project.ShareBook.common.SuccessType;
import com.project.ShareBook.dto.log.LogRequestDto;
import com.project.ShareBook.dto.log.LogResponseDto;
import com.project.ShareBook.dto.log.LogUpdateRequestDto;
import com.project.ShareBook.jwt.CustomUserDetails;
import com.project.ShareBook.service.ReadingLogService;
import java.time.LocalDate;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
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
    public ResponseEntity<ApiResponse<LogResponseDto>> createLog(
        @AuthenticationPrincipal CustomUserDetails userDetails,
        @PathVariable Long saveBookId,
        @RequestBody LogRequestDto dto) {
        Long userId = userDetails.getUser().getId();
        LogResponseDto readingLog = readingLogService.createReadingLog(userId, saveBookId, dto);
        return ResponseEntity.ok(ApiResponse.success(SuccessType.CREATE_SUCCESS,readingLog));
    }

    @PutMapping("/{logId}")
    public ResponseEntity<ApiResponse<String>> updateLog(
        @AuthenticationPrincipal CustomUserDetails userDetails,
        @PathVariable Long logId,
        @RequestBody LogUpdateRequestDto dto){

        Long userId = userDetails.getUser().getId();
        readingLogService.updateReadingLog(userId,logId,dto);
        return ResponseEntity.ok(ApiResponse.success(SuccessType.UPDATE_SUCCESS,"소감문이 수정되었습니다"));
    }

    @GetMapping("/by-date")
    public ResponseEntity<ApiResponse<List<LogResponseDto>>> getLogsByDate(
        @AuthenticationPrincipal CustomUserDetails userDetails,
        @RequestParam String date // YYYY-MM-DD 형식
    ) {
        Long userId = userDetails.getUser().getId();
        List<LogResponseDto> logs = readingLogService.getLogsByDate(userId, LocalDate.parse(date));
        System.out.println("🎯 조회된 로그 수: " + logs.size());
        for (LogResponseDto log : logs) {
            System.out.println("📝 log = " + log);
        }
        return ResponseEntity.ok(ApiResponse.success(SuccessType.INQUERY_SUCCESS,logs));
    }

    @GetMapping("/by-month")
    public ResponseEntity<ApiResponse<List<LogResponseDto>>> getLogsByMonth(
        @AuthenticationPrincipal CustomUserDetails userDetails,
        @RequestParam int year,
        @RequestParam int month
    ) {
        Long userId = userDetails.getUser().getId();

        LocalDate startDate = LocalDate.of(year, month, 1);
        LocalDate endDate = startDate.withDayOfMonth(startDate.lengthOfMonth());

        List<LogResponseDto> logs = readingLogService.getLogsByMonth(userId, startDate, endDate);
        return ResponseEntity.ok(ApiResponse.success(SuccessType.INQUERY_SUCCESS, logs));
    }

    @DeleteMapping("/{logId}")
    public ResponseEntity<ApiResponse<?>> deleteLog(@AuthenticationPrincipal CustomUserDetails userDetails, @PathVariable Long logId){
        Long userId = userDetails.getUser().getId();
        readingLogService.deleteLog(userId,logId);
        return ResponseEntity.ok(ApiResponse.success(SuccessType.DELETE_SUCCESS,"삭제 성공"));

    }

}
