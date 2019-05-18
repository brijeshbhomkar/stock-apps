package com.charting.controllers;

import org.springframework.stereotype.Controller;

import com.charting.pojo.User;
import com.charting.utils.RestfulSupport;

@Controller
public class LoginController extends RestfulSupport {
	
	public void login(final User user) {
		restTemplate.postForEntity("", user, User.class);
	}
}
