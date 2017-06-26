package com.edu.westga.cs6920.wiseowl.model;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


@Entity
@Table(name="SITEUSER")
public class User
{

		
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private long user_ID;
	
	private String firstname;
	private String lastname;
	private String nickname;
	
	@Temporal(TemporalType.DATE)
	private Date dob;
	
	@OneToOne
	@JoinColumn(name="USER_ROLE_ID")
	private UserRole userRole;
	
	@OneToOne(cascade={CascadeType.PERSIST, CascadeType.REMOVE})
	@JoinColumn(name="USER_AUTH_ID")
	private UserAuth userAuth;
	
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date create_datetime;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date update_datetime;
	
	public User(){}
	
	public User(String firstname, String lastname, String nickname, String dob, String username, String password) throws Exception
	{
		this.firstname=firstname;
		this.lastname=lastname;
		this.nickname=nickname;
		
		DateFormat format = new SimpleDateFormat("MM/dd/yyyy", Locale.ENGLISH);
		Date date = format.parse(dob);
		this.dob=date;
		
		UserAuth userAuth=new UserAuth(username,password);
		this.userAuth=userAuth;		
	}
	
	
	public long getUser_ID() {
		return user_ID;
	}

	public void setUser_ID(long user_ID) {
		this.user_ID = user_ID;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	
	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	public Date getCreate_datetime() {
		return create_datetime;
	}


	public Date getUpdate_datetime() {
		return update_datetime;
	}

	public UserRole getUserRole() {
		return userRole;
	}

	public void setUserRole(UserRole userRole) {
		this.userRole = userRole;
	}

	public UserAuth getUserAuth() {
		return userAuth;
	}

	public void setUserAuth(UserAuth userAuth) {
		this.userAuth = userAuth;
	}
	
}
