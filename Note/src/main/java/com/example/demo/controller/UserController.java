package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Response.MessageResponse;
import com.example.demo.services.UserService;
import com.example.demo.view.UserViewModel;

@RestController()
@RequestMapping("/api/user")
@CrossOrigin
public class UserController {

	@Autowired
	UserService  userService;
		
	
	@PostMapping("/login")
		public UserViewModel loginUser(@RequestBody UserViewModel model) {
		System.err.println(model.getEmail() +"----");
		System.err.println(model.getPassword() +"----");
			return this.userService.login(model);
		}
		
	@PostMapping("/register")
	public UserViewModel register(@RequestBody UserViewModel model) {
		System.out.println("In Register");
		return this.userService.Register(model);
	}
		
	@GetMapping("/logout")
	public MessageResponse logout() {
		return new MessageResponse("Sucsses Logout", true);
	}
}
