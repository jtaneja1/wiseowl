package com.edu.westga.cs6920.wiseowl.tests;

import static org.junit.Assert.*;
import org.junit.Test;

import com.edu.westga.cs6920.wiseowl.model.UserAuth;
import com.edu.westga.cs6920.wiseowl.service.UserService;

/**
 * This JUnit test class tests to make sure the loginUser method
 * of the UserService class work properly.
 * 
 * @author David Bennett
 * @version 6/22/2017
 */
public class UserServiceTest {

	private UserAuth userauth;
	private UserService userservice;
	
	/**
	 * Test to make sure the user service logs in the user correctly.
	 * @throws Exception 
	 */
	@Test
	public void testSetUserRoleID() throws Exception {
		this.userauth = new UserAuth("jatin", "password1");
		this.userservice = new UserService();
		this.userservice.loginUser(this.userauth);
		assertNotNull(this.userservice.loginUser(this.userauth));
	}
}