package com.vish.app.Entity;

import jakarta.persistence.*;

@Entity
@Table(name = "role")
public class Role {

	// role fields
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "role_id")
	private Long id;
	
	@Column(name = "role_name")
	private String name;
	
	
	// constructors
	
	public Role() {
		
	}
	
	public Role(String name) {
		this.name = name;
	}

	
	// getter setter

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	
	// override toString
	
	@Override
	public String toString() {
		return "Role [id=" + id + ", name=" + name + "]";
	}
	
}
