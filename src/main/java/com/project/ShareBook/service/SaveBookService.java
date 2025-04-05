package com.project.ShareBook.service;

import com.project.ShareBook.Entity.Book;
import com.project.ShareBook.Entity.SaveBook;
import com.project.ShareBook.Entity.User;
import com.project.ShareBook.repository.BookRepository;
import com.project.ShareBook.repository.SaveBookRepository;
import com.project.ShareBook.repository.UserRepository;
import java.time.LocalDateTime;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SaveBookService {

    private final UserRepository userRepository;
    private final BookRepository bookRepository;
    private final SaveBookRepository saveBookRepository;


    public void SaveBook(Long userId, Long bookId){
        User user = userRepository.findById(userId)
            .orElseThrow(()->new IllegalAccessError("존재하지 않는 유저"));
        Book book = bookRepository.findById(bookId)
            .orElseThrow(()->new IllegalAccessError("존재하지 않는 책"));

        // 이미 저장했는지 중복 체크 (선택)
        boolean alreadyRead = saveBookRepository.existsByUserAndBook(user, book);
        if (alreadyRead) {
            throw new IllegalStateException("이미 읽은 책으로 등록됨");
        }

        SaveBook saveBook = SaveBook.builder()
            .user(user)
            .book(book)
            .readAt(LocalDateTime.now())
            .build();

        saveBookRepository.save(saveBook);
    }


}
