package com.project.ShareBook.repository;

import com.project.ShareBook.Entity.User;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {

//    List<User>save(User user);
}
