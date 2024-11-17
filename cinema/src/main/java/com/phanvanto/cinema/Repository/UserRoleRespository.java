package com.phanvanto.cinema.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.phanvanto.cinema.Entity.User_Role;



@Repository
public interface UserRoleRespository extends JpaRepository<User_Role, Integer>{

}
