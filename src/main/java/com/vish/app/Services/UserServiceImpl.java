package com.vish.app.Services;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.vish.app.Entity.Role;
import com.vish.app.Entity.User;
import com.vish.app.Repository.RoleRepo;
import com.vish.app.Repository.UserRepo;
import com.vish.app.temp.CurrentUser;

import jakarta.transaction.Transactional;

@Service
public class UserServiceImpl implements UserService {

	// handle service operations
	
	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private RoleRepo roleRepo;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	public UserServiceImpl(UserRepo userRepo, RoleRepo roleRepo, BCryptPasswordEncoder passwordEncoder) {
		this.userRepo = userRepo;
		this.roleRepo = roleRepo;
		this.passwordEncoder = passwordEncoder;
	}
	
	
	
	// check email
	@Override
	@Transactional
	public User findUserByEmail(String email) {
		return userRepo.findByEmail(email);
	}
	
	
	// save user from current user
	@Override
	@Transactional
	public void saveUser(CurrentUser currentUser) {
		User user = new User();
		user.setUsername(currentUser.getUsername());
		user.setEmail(currentUser.getEmail());
		user.setPassword(passwordEncoder.encode(currentUser.getPassword()));
		user.setRoles(Arrays.asList(roleRepo.findByName("ROLE_EMPLOYEE")));
		userRepo.save(user);
	}
	
	
	// get logged in user id
	@Override
	@Transactional
	public int getLoggedUserId() {
		User user = userRepo.findByUsername(loggedUserEmail());
		return user.getId();
	}
	
	// logged user email using security principal
	private String loggedUserEmail() {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if(principal instanceof UserDetails) {
			return ((UserDetails) principal).getUsername();
		}
		return principal.toString();
	}
	
	
	// check valid username and role
	@Override
	@Transactional
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException{
		User user = userRepo.findByEmail(email);
		
		if(user == null) {
			throw new UsernameNotFoundException("Invalid Username / Password");
		}
		
		return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), mapRolesToAuthorities(user.getRoles()));
	}
	
	// authority role for user
	private List<? extends GrantedAuthority> mapRolesToAuthorities(List<Role> roles){
		return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
	}
}
