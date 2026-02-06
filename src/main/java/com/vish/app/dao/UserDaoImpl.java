package com.vish.app.dao;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.vish.app.Entity.User;

import jakarta.persistence.EntityManager;

@Repository
public class UserDaoImpl implements UserDao {
	
	// send / receive data to / from db
	
	// entity manager to manage user
	@Autowired
	private EntityManager entityManager;

	// get user by email
	@Override
	public User findUserByEmail(String email) {
		
		Query<User> query = currentSession().createQuery("from User where user_email = :email", User.class);
		query.setParameter("email", email);
		
		User user = null;
		try {
			user = query.getSingleResult();
		}
		catch (Exception e) {
			user = null;
		}
		
		return user;
	}

	// get user by username
	@Override
	public User findUserByUsername(String username) {

		Query<User> query = currentSession().createQuery("from User where user_username = :username", User.class);
		query.setParameter("username", username);
		
		User user = null;
		try {
			user = query.getSingleResult();
		}
		catch (Exception e) {
			user = null;
		}
		
		return user;
	}

	// save user / update
	@Override
	public void saveUser(User user) {
		currentSession().merge(user);
	}
	
	// get current hibernate session
	private Session currentSession() {
		return entityManager.unwrap(Session.class);
	}

}
