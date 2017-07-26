package com.example.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.entities.User;

public interface UserRepository extends JpaRepository<User, Integer>{
	
	public List<User>findByIsAvailableTrue();
	public User findByCredentialsUsername(String username);
	public User findByCredentialsUsernameAndIsAvailableTrue(String username);
	

	

}
