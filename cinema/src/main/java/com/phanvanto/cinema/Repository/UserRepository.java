package com.phanvanto.cinema.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.phanvanto.cinema.Entity.User;



public interface UserRepository extends JpaRepository<User, Integer>{

	User findByUsername(String username);

		
	
}
