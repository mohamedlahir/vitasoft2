package com.mylahir.MyUserDetailServices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.mylahir.myuserprincipal.MyUserPrincipal;
import com.mylahir.usermodel.UserModel;
import com.mylahir.userrepo.UserRepo;

@Service
@ComponentScan
public class MyUserDetailService implements UserDetailsService {

	@Autowired
	private UserRepo repo;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserModel user = repo.findByUsername(username);
		if (user == null) {
			throw new UsernameNotFoundException("User not found");
		}

		return new MyUserPrincipal(user);
	}

}
