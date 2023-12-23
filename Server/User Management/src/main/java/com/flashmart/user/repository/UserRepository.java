package com.flashmart.user.repository;

import com.flashmart.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<User, String> {

    @Query("SELECT CASE WHEN COUNT(u) > 0 THEN true ELSE false END FROM User u WHERE u.type = ?1 AND u.email = ?2")
    Boolean verifyEmail(int type, String email);

    User findByTypeAndEmail(int type, String email);
}
