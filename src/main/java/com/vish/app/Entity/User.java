package com.vish.app.Entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;

@Entity
@Table(name = "user") 
public class User {
	
	// user fields
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_id")
	private int id;
	
	@Column(name = "user_username")
	private String username;
	
	@Column(name = "user_email")
	private String email;
	
	@Column(name = "user_password")
	private String password;
	
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "reservation_user_id")
	private List<Reservation> reservations;
	
	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinTable(name = "user_roles", joinColumns = @JoinColumn(referencedColumnName = "user_id"), inverseJoinColumns = @JoinColumn(referencedColumnName = "role_id"))
	private List<Role> roles;
	
	
	// constructor
	public User() {
		
	}
	
	public User(String username, String email, String password, List<Reservation> reservations, List<Role> roles) {
		this.username = username;
		this.email = email;
		this.password = password;
		this.reservations = reservations;
		this.roles = roles;
	}

	// getter / setter
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<Reservation> getReservations() {
		return reservations;
	}

	public void setReservations(List<Reservation> reservations) {
		this.reservations = reservations;
	}

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		List<Role> role = new ArrayList<>();
		role.add(new Role("ROLE_EMPLOYEE"));
		this.roles = role;
	}

	// override toString
	
	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", email=" + email + ", password=" + password
				+ ", reservations=" + reservations + ", roles=" + roles + "]";
	}
	
}
