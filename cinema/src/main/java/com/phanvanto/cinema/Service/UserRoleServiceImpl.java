package com.phanvanto.cinema.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.phanvanto.cinema.Entity.User_Role;
import com.phanvanto.cinema.Repository.UserRoleRespository;


@Service
public class UserRoleServiceImpl implements UserRoleService {

	@Autowired
	private UserRoleRespository userRoleRespository;
	
	@Override
	public void save(User_Role user_role) {
		try {
			userRoleRespository.save(user_role);
		}
		catch(Exception e){
			e.printStackTrace();
			throw e;
		}
		
	}

}
