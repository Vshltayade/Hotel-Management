package com.vish.app.dao;

import com.vish.app.Entity.User;

public interface UserDao {

	public User findUserByEmail(String email);

    public User findUserByUsername(String username);
    
    public void saveUser(User user);
        
}
