package com.project.ShareBook.service;

import com.project.ShareBook.Entity.Book;
import com.project.ShareBook.Entity.SaveBook;
import com.project.ShareBook.Entity.User;
import com.project.ShareBook.dto.SaveBookResponseDto;
import com.project.ShareBook.repository.BookRepository;
import com.project.ShareBook.repository.SaveBookRepository;
import com.project.ShareBook.repository.UserRepository;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class SaveBookService {

    private final BookRepository bookRepository;
    private final SaveBookRepository saveBookRepository;


    public void SaveBook(User user, Long bookId){
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
    public List<SaveBookResponseDto> userSaveBook(User user){
        List<SaveBook> savedBooks = saveBookRepository.findByUserId(user.getId());
        return savedBooks.stream().map(saveBook ->
            new SaveBookResponseDto(
                saveBook.getBook().getBookName(),
                saveBook.getBook().getBookPublisher(),
                saveBook.getBook().getBookIsbn(),
                saveBook.getBook().getBookImageUrl(),
                saveBook.getReadAt()
            )
        ).toList();
    }
    public void deleteSaveBook(Long saveBookId,User user){
        SaveBook saveBook = saveBookRepository.findById(saveBookId)
            .orElseThrow(()->new IllegalAccessError("저장하지 않은 책입니다"));

        if (!saveBook.getUser().getId().equals(user.getId())) {
            throw new IllegalAccessError("삭제 권한이 없습니다.");
        }
        saveBookRepository.delete(saveBook);
    }



}
