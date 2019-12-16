package com.example.demo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.MyMapper;
import com.example.demo.Repository.UserRepositry;
import com.example.demo.exception.ExceptionFiledRequired;
import com.example.demo.exception.NotfoundResult;
import com.example.demo.model.User;
import com.example.demo.view.UserViewModel;

@Service
public class UserService {
	
	@Autowired
	private UserRepositry userRepositry;
	
	@Autowired
	MyMapper mapper;
	
	
	public UserViewModel login(UserViewModel model) {
		User entity = this.mapper.convertToUserEntity(model); 
		System.err.println(entity.getEmail()+"\n" +entity.getUserPassword());
		
		User user = this.userRepositry.findByEmail(entity.getEmail());
		if(user == null)
			throw new NotfoundResult("This User Not Found");
		return mapper.convertToUserViewModel(user);
	}
	public UserViewModel Register(UserViewModel model) {
		if(model.getEmail().isEmpty() || model.getName().isEmpty()||model.getPassword().isEmpty())
		{
			throw new ExceptionFiledRequired("Name and email and password are required");		
		}
		
		User user = this.userRepositry.save(this.mapper.convertToUserEntity(model));
		return this.mapper.convertToUserViewModel(user);
	}	
	
}
