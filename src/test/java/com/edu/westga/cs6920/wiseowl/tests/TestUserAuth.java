package com.edu.westga.cs6920.wiseowl.tests;

import static org.junit.Assert.*;

import org.junit.Test;

import com.edu.westga.cs6920.wiseowl.model.UserAuth;

/**
 * This JUnit test class tests to make sure the constructor, getter, and setter methods
 * of the UserAuth class work properly.
 * 
 * @author David Bennett
 * @version 6/17/2017
 */
public class TestUserAuth {

	private UserAuth userauth;
	
	/**
	 * Test to make sure the constructed user auth has a valid user auth ID.
	 * @throws Exception 
	 */
	@Test
	public void testConstructedUserAuthHasUserAuthID() throws Exception {
		this.userauth = new UserAuth("jatin", "password1");
		assertNotNull(this.userauth.getUser_auth_id());
	}
	
	/**
	 * Test to make sure the user auth's user auth ID setter functions correctly.
	 * @throws Exception 
	 */
	@Test
	public void testSetUserAuthID() throws Exception {
		this.userauth = new UserAuth("jatin", "password1");
		userauth.setUser_auth_id(1);
		assertEquals(1, userauth.getUser_auth_id());
	}
	
	/**
	 * Test to make sure the constructed user auth has the correct username.
	 * @throws Exception 
	 */
	@Test
	public void testConstructedUserAuthHasCorrectUsername() throws Exception {
		this.userauth = new UserAuth("jatin", "password1");
		assertEquals("jatin", this.userauth.getUsername());
	}
	
	/**
	 * Test to make sure the user auth's username setter functions correctly.
	 * @throws Exception 
	 */
	@Test
	public void testSetUsername() throws Exception {
		this.userauth = new UserAuth();
		userauth.setUsername("username");
		assertEquals("username", this.userauth.getUsername());
	}

	/**
	 * Test to make sure the constructed user auth has the correct password.
	 * @throws Exception 
	 */
	@Test
	public void testConstructedUserHasCorrectLastName() throws Exception {
		this.userauth = new UserAuth("jatin", "password1");
		assertEquals("password1", this.userauth.getPassword());
	}
	
	/**
	 * Test to make sure the user auth's password setter functions correctly.
	 * @throws Exception 
	 */
	@Test
	public void testSetPassword() throws Exception {
		this.userauth = new UserAuth();
		userauth.setPassword("password");
		assertEquals("password", this.userauth.getPassword());
	}
	
	/**
	 * Test to make sure the constructed user auth does not have a valid created date. I need to ask whether new user auths are supposed to 
	 * have a create date attached to them.
	 * @throws Exception 
	 */
	@Test
	public void testConstructedUserHasNoCreateDateTime() throws Exception {
		this.userauth = new UserAuth("jatin", "password1");
		assertNull(this.userauth.getCreate_datetime());
	}
	
	/**
	 * Test to make sure the constructed user auth does not have an updated date. I need to ask whether new user auths are supposed to have 
	 * an update date attached to them.
	 * @throws Exception 
	 */
	@Test
	public void testConstructedUserHasNoUpdateDateTime() throws Exception {
		this.userauth = new UserAuth("jatin", "password1");
		assertNull(this.userauth.getUpdate_datetime());
	}
}
