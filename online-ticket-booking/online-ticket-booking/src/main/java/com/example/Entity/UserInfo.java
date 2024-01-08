package com.example.Entity;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;

@Entity
public class UserInfo {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private String username;
	private String password;
	private String email;
	@ManyToMany(fetch = FetchType.EAGER)
	private Set <UserRole> roles=new HashSet<>();
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
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Set<UserRole> getRoles() {
		return roles;
	}
	public void setRoles(Set<UserRole> roles) {
		this.roles = roles;
	}
	public UserInfo() {
		super();
		// TODO Auto-generated constructor stub
	}
	public UserInfo(int id, String username, String password, String email, Set<UserRole> roles) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.email = email;
		this.roles = roles;
	}
	@Override
	public String toString() {
		return "UserInfo [id=" + id + ", username=" + username + ", password=" + password + ", email=" + email
				+ ", roles=" + roles + "]";
	}
	

}
