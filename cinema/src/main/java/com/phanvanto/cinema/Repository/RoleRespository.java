package com.phanvanto.cinema.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.phanvanto.cinema.Entity.Role;


@Repository
public interface RoleRespository extends JpaRepository<Role, Integer> {

	Role findByName(String name);
}
