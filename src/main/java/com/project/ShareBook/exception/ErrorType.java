package com.project.ShareBook.exception;

import com.project.ShareBook.common.ErrorCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ErrorType implements ErrorCode {
    MEMBER_NOT_FOUND(HttpStatus.NOT_FOUND, "존재하지 않는 회원입니다."),
    TEST_ERROR(HttpStatus.BAD_REQUEST, "테스트 에러");

    private final HttpStatus status;
    private final String desc;
}
