package com.project.ShareBook.common;

import org.springframework.http.HttpStatus;

public interface ErrorCode {
    String name();
    String getDesc();
    HttpStatus getStatus();
}