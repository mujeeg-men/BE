package com.project.ShareBook.controller;

import com.project.ShareBook.dto.BookSaveDto;
import com.project.ShareBook.repository.SaveBookRepository;
import com.project.ShareBook.service.SaveBookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/book/save")
@RequiredArgsConstructor
public class SaveBookController {

    private final SaveBookRepository saveBookRepository;
    private final SaveBookService saveBookService;



    @PostMapping("/{userId}/{bookId}")
    public ResponseEntity<String> bookSave(@PathVariable Long userId, @PathVariable Long bookId){
        saveBookService.SaveBook(userId,bookId);
        return ResponseEntity.ok("책 저장 완료");

    }

}
