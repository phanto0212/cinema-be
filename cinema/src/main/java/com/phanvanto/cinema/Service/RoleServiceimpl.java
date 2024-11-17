package com.phanvanto.cinema.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.phanvanto.cinema.Entity.Role;
import com.phanvanto.cinema.Repository.RoleRespository;


@Service
public class RoleServiceimpl implements RoleService {

	@Autowired
	private RoleRespository roleRespository;


	@Override
	public Role findByRoleName(String name) {
		
			return roleRespository.findByName(name);
		
	}
	


}

