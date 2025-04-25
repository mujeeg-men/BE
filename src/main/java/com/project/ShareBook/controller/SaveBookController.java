package com.project.ShareBook.controller;

import com.project.ShareBook.Entity.User;
import com.project.ShareBook.dto.BookResponseDto;
import com.project.ShareBook.dto.BookSaveDto;
import com.project.ShareBook.dto.SaveBookResponseDto;
import com.project.ShareBook.jwt.CustomUserDetails;
import com.project.ShareBook.repository.SaveBookRepository;
import com.project.ShareBook.service.SaveBookService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
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

    @PostMapping("/{bookId}")
    public ResponseEntity<String> saveBook(@AuthenticationPrincipal CustomUserDetails userDetails, @PathVariable Long bookId){
        User user = userDetails.getUser();
        saveBookService.SaveBook(user,bookId);
        return ResponseEntity.ok("책 저장 완료");
    }

    // 유저가 저장한 책 반환 api
    @GetMapping("/find")
    public ResponseEntity<List<SaveBookResponseDto>>findUserSaveBook(@AuthenticationPrincipal CustomUserDetails userDetails){
        User user = userDetails.getUser();
        List<SaveBookResponseDto> saveBookResponseDto = saveBookService.userSaveBook(user);
        return ResponseEntity.ok(saveBookResponseDto);
    }

    //책 저장 취소 api
    @DeleteMapping("/delete/{saveBookId}")
    public ResponseEntity<?> deleteSaveBook(@PathVariable Long saveBookId,@AuthenticationPrincipal CustomUserDetails userDetails){
        User user = userDetails.getUser();
        saveBookService.deleteSaveBook(saveBookId,user);
        return ResponseEntity.ok("삭제가 완료 되었습니다");
    }

}
