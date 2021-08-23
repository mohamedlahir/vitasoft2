package com.mylahir.userrepo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mylahir.usermodel.UserModel;

public interface UserRepo extends JpaRepository<UserModel, Integer> {
	
	UserModel findByUsername(String username);
		
}
