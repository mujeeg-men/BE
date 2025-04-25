package com.project.ShareBook.dto.log;

import com.project.ShareBook.Entity.ReadingLog;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class LogResponseDto {
    private Long id;
    private String bookTitle;
    private Integer pageRead;
    private String comment;

    public static LogResponseDto from(ReadingLog log) {
        return LogResponseDto.builder()
            .id(log.getId())
            .bookTitle(log.getSaveBook().getBook().getBookName()) // 관계 매핑 필요
            .pageRead(log.getPageRead())
            .comment(log.getComment())
            .build();
    }
}
