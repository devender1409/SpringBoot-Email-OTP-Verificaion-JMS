package com.devender.emailotpverification.repository;

import com.devender.emailotpverification.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User, Long> {
    User findByEmail(String email);
}
