package com.edu.westga.cs6920.wiseowl.tests;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import javax.servlet.http.HttpSession;

import org.junit.Test;

import com.edu.westga.cs6920.wiseowl.controller.UserController;

/**
 * This JUnit test class tests to make sure the loginUser method
 * of the UserController class work properly.
 * 
 * @author David Bennett
 * @version 6/21/2017
 */
public class UserControllerTest {

	private HttpSession session;
	private UserController usercontroller;
	
	/**
	 * Test to make sure the user controller logs in the user correctly.
	 * @throws Exception 
	 */
	@Test
	public void testSetUserRoleID() throws Exception {
		this.session = mock(HttpSession.class);
		this.usercontroller = new UserController();
		this.usercontroller.loginUser(this.session, "jatin", "password1");
		assertNotNull(this.usercontroller.loginUser(this.session, "jatin", "password1"));
	}
}