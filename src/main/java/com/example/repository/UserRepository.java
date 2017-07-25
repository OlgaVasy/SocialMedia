package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.entities.User;

public interface UserRepository extends JpaRepository<User, Long>{

	

}
