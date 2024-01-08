package com.example.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.Entity.UserInfo;

public interface UserRepository extends JpaRepository<UserInfo, Integer> {
	UserInfo findByEmail(String email);
	boolean existsByEmail(String email);
	public UserInfo findByUsername(String username);

}
