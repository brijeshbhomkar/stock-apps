package com.charting.views;

import java.io.IOException;
import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import com.charting.services.LoginService;

@ManagedBean(name = "loginForm")
@SessionScoped
public class LoginForm implements Serializable {

	private static final long serialVersionUID = 5718825605454912977L;

	@Inject
	private LoginService loginService;

	private String username;
	private String password;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void login() {
	    ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        try {
        	//TOOD: Need to go through pretty config
			externalContext.redirect("/charting-service/main/dashboard.xhtml");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
