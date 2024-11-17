package com.phanvanto.cinema.Repository;

import java.util.List;

import com.phanvanto.cinema.Entity.User;


public interface UserRepositoryCustom {

	List<User> getList();
	void addOrUpdate (User user);
	User getUserById(int id);
	void deleteUserByid(int id);
	User getUserbyEmail(String email);
}
