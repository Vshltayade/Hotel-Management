package com.vish.app.dao;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.vish.app.Entity.Role;

import jakarta.persistence.EntityManager;

@Repository
public class RoleDaoImpl implements RoleDao {
	
	// send / receive data to / from db
	
	// entity manager to manage role
	@Autowired
	private EntityManager entityManager;

	// db operation to retrieve role
	@Override
	public Role findRoleByName(String role) {
		
		Query<Role> query = currentSession().createQuery("from Role where role_name = :roleName", Role.class);
		query.setParameter("roleName", role);
		
		Role role2 = null;
		try {
			role2 = query.getSingleResult();
		}
		catch (Exception e) {
			role2 = null;
		}
		
		return role2;
	}
	
	// hib session
	private Session currentSession() {
		return entityManager.unwrap(Session.class);
	}

}
