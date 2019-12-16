package com.example.demo.configration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.demo.Repository.UserRepositry;
import com.example.demo.model.User;
@Service	
public class MyUserDetails implements UserDetailsService {

	@Autowired
	UserRepositry userRepositry;
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
			User user = userRepositry.findByEmail(username);
			if(user == null)
				throw new UsernameNotFoundException("User Not Found");
		 return new UserPrinciple(user);
	}

	
}
