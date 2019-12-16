package com.example.demo.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.User;

public interface UserRepositry extends JpaRepository<User, Long> {
	//User findByEmailAndUserPassword(String email,String password);
	User findByEmail(String email);
}
