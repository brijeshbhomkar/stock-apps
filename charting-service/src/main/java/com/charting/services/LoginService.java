package com.charting.services;

import org.springframework.stereotype.Service;

import com.charting.utils.RestfulSupport;

@Service
public class LoginService extends RestfulSupport {
	
	public boolean login(final String username, final String password) {
		return true;
	}
}
