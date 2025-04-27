package com.project.ShareBook.service;

import com.project.ShareBook.Entity.ReadingLog;
import com.project.ShareBook.Entity.SaveBook;
import com.project.ShareBook.Entity.User;
import com.project.ShareBook.dto.log.LogRequestDto;
import com.project.ShareBook.dto.log.LogResponseDto;
import com.project.ShareBook.dto.log.LogUpdateRequestDto;
import com.project.ShareBook.repository.ReadingLogRepository;
import com.project.ShareBook.repository.SaveBookRepository;
import com.project.ShareBook.repository.UserRepository;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReadingLogService {
    private final ReadingLogRepository readingLogRepository;
    private final SaveBookRepository saveBookRepository;
    private final UserRepository userRepository;

    public LogResponseDto createReadingLog(Long userId,Long saveBookId,LogRequestDto request){
        SaveBook saveBook = saveBookRepository.findByIdAndUserId(saveBookId, userId)
            .orElseThrow(() -> new IllegalArgumentException("해당 책을 찾을 수 없습니다."));

        ReadingLog log = ReadingLog.builder()
            .date(request.getDate())
            .pageRead(request.getPageRead())
            .comment(request.getComment())
            .saveBook(saveBook)
            .build();
        readingLogRepository.save(log);

        return LogResponseDto.from(log);
    }
    public List<LogResponseDto> getLogsByDate(Long userId, LocalDate date) {
        List<ReadingLog> logs = readingLogRepository.findAllByDateAndSaveBook_User_Id(date, userId);
        return logs.stream().map(LogResponseDto::from).toList();
    }

    public void updateReadingLog(Long userId,Long logId, LogUpdateRequestDto request){
        ReadingLog readingLog = readingLogRepository.findById(logId)
            .orElseThrow(()->new IllegalArgumentException("잘못된 소감문 접근입니다"));

        if(!readingLog.getUser().getId().equals(userId)){
            throw new IllegalArgumentException("소감문 수정 권한이 없습니다");
        }
        readingLog.update(request);
    }

    public void deleteLog(Long userId, Long logId){
        User user = userRepository.findById(userId).orElseThrow(()-> new IllegalArgumentException("존재하지 않는 회원"));
        ReadingLog log = readingLogRepository.findById(logId).orElseThrow(()-> new IllegalArgumentException("존재하지 않는 로그"));

        if(!user.getId().equals(log.getUser().getId())){
            throw new IllegalArgumentException("삭제 권한이 없습니다");
        }
        readingLogRepository.deleteById(log.getId());
    }


}
