package com.project.ShareBook.controller;

import com.project.ShareBook.dto.LoginRequestDto;
import com.project.ShareBook.dto.log.LogRequestDto;
import com.project.ShareBook.dto.log.LogResponseDto;
import com.project.ShareBook.service.ReadingLogService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/log")
@RequiredArgsConstructor
public class ReadingLogController {
    private final ReadingLogService readingLogService;

    @PostMapping
    public ResponseEntity<String> createLog(@RequestBody LogRequestDto dto) {
        readingLogService.createReadingLog(dto);
        return ResponseEntity.ok("기록 저장 완료");
    }
//    @PutMapping("/{LogId}")
//    public ResponseEntity<String> updateLog(@PathVariable logId @Request)

}
