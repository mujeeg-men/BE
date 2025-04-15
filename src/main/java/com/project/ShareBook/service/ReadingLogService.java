package com.project.ShareBook.service;

import com.project.ShareBook.Entity.ReadingLog;
import com.project.ShareBook.Entity.SaveBook;
import com.project.ShareBook.dto.log.LogRequestDto;
import com.project.ShareBook.repository.ReadingLogRepository;
import com.project.ShareBook.repository.SaveBookRepository;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReadingLogService {
    private final ReadingLogRepository readingLogRepository;
    private final SaveBookRepository saveBookRepository;

    public void createReadingLog(LogRequestDto request){
        SaveBook saveBook = saveBookRepository.findById(request.getSaveBookId())
            .orElseThrow(()->new IllegalArgumentException("관심있는 책으로 저장되지 않은 책입니다"));

        ReadingLog log = ReadingLog.builder()
            .date(request.getDate())
            .pageRead(request.getPageRead())
            .comment(request.getComment())
            .saveBook(saveBook)
            .build();
        readingLogRepository.save(log);
    }

}
