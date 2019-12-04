package com.example.creditor.repos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.creditor.domain.User;

public interface UserRepo extends JpaRepository<User, Long> {
	User findByUsername(String username);
	List<User> findByAdmin(boolean admin);
}
