package com.edu.westga.cs6920.wiseowl.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="USER_ROLE")
public class UserRole
{
	@Id
	private long user_role_ID;
	private String role_code;
	private String role_name;
	private String role_desc;
	
	public UserRole(){}
	
		
	public long getUser_role_ID() {
		return user_role_ID;
	}
	public void setUser_role_ID(long user_role_ID) {
		this.user_role_ID = user_role_ID;
	}
	public String getRole_code() {
		return role_code;
	}
	public void setRole_code(String role_code) {
		this.role_code = role_code;
	}
	public String getRole_name() {
		return role_name;
	}
	public void setRole_name(String role_name) {
		this.role_name = role_name;
	}
	public String getRole_desc() {
		return role_desc;
	}
	public void setRole_desc(String role_desc) {
		this.role_desc = role_desc;
	}
	
	
	
}
