package com.project.ShareBook.repository;

import com.project.ShareBook.Entity.ReadingLog;
import java.time.LocalDate;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReadingLogRepository extends JpaRepository<ReadingLog, Long> {

    List<ReadingLog> findAllByDateAndSaveBook_User_Id(LocalDate date, Long userId);
}
