package com.phanvanto.cinema.Service;

import java.util.List;

import com.phanvanto.cinema.Entity.User;



public interface UserService {
	 User getUserByUsername(String username);
	 List<User> getList();
	 void AddOrUpdate(User user);
	 User getUserById(int id);
	 void deleteById(int id);
	 User getUserByEmail(String email);
}
