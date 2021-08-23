package com.mylahir.crudservice;

import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


import com.mylahir.usermodel.UserModel;
import com.mylahir.userrepo.UserRepo;

@Service
public class CrudServices {
	
	@Autowired
	private UserRepo repo;

	private UserModel model;
	
	//PasswordEncoder passwordencoder;

	public CrudServices(UserRepo repo) {
		this.repo = repo;
		//this.passwordencoder=new BCryptPasswordEncoder();
	}

	
	public UserModel save(UserModel model) {
		
		//String encodePassword = this.passwordencoder.encode(model.getPassword());
		//model.setPassword(encodePassword);		
		  this.repo.save(model);
		
		  return model;
	}
	
	public Optional<UserModel> getelements(int id) {
		
		return repo.findById(id);
	}
	
	
}
