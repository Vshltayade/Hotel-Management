package com.vish.app.Services;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.vish.app.Entity.User;
import com.vish.app.temp.CurrentUser;

public interface UserService extends UserDetailsService{

	// methods
	public User findUserByEmail(String email);
	public void saveUser(CurrentUser currentUser);
	public int getLoggedUserId();
}
