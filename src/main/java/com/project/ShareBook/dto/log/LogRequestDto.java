package com.project.ShareBook.dto.log;

import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LogRequestDto {
    private LocalDate date;
    private int pageRead;
    private String comment;
}
