package com.charting.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.charting.controllers.LoginController;
import com.charting.pojo.User;

@Service
public class LoginService {
	
	@Autowired
	private LoginController loginController;
	
	public boolean login(final String username, final String password) {
		loginController.login(new User(username, password)); 
		return true;
	}
}
