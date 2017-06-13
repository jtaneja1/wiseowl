package com.edu.westga.cs6920.wiseowl.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="USER_AUTH")
public class UserAuth
{
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private long user_auth_id;
	
	private String username;
	private String password;	
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date create_datetime;	
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date update_datetime;
	
	public UserAuth(){}
	
	public UserAuth(String username, String password)
	{
		this.username=username;
		this.password=password;
	}
	
	public long getUser_auth_id() {
		return user_auth_id;
	}
	public void setUser_auth_id(long user_auth_id) {
		this.user_auth_id = user_auth_id;
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
	public Date getCreate_datetime() {
		return create_datetime;
	}
	public Date getUpdate_datetime() {
		return update_datetime;
	}
}
