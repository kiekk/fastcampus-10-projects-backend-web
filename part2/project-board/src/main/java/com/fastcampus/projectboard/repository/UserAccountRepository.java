package com.fastcampus.projectboard.repository;

import com.fastcampus.projectboard.domain.user.UserAccount;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserAccountRepository extends JpaRepository<UserAccount, String> {
}