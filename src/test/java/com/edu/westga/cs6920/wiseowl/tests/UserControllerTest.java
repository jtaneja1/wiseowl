package com.edu.westga.cs6920.wiseowl.tests;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.edu.westga.cs6920.wiseowl.controller.UserController;
import com.edu.westga.cs6920.wiseowl.model.User;
import com.edu.westga.cs6920.wiseowl.model.UserAuth;
import com.edu.westga.cs6920.wiseowl.service.UserService;

/**
 * This JUnit test class tests to make sure the loginUser method
 * of the UserController class work properly.
 * 
 * @author David Bennett
 * @version 7/13/2017
 */
public class UserControllerTest {

	private HttpServletRequest request;
	private HttpSession session;
	private User user;
	private List<User> userlist;
	
	@Mock
	private UserService userservice;
	private UserAuth userauth;
	
	@InjectMocks
	private UserController usercontroller;
	
	@Before
	public void setup() throws Exception
	{     
		this.usercontroller = new UserController();
	    this.request = mock(HttpServletRequest.class);
	    this.session = mock(HttpSession.class);  
	    this.user = new User("Test", "User", "Testy", "01/01/2001", "test1", "password");
	    this.userlist = new ArrayList<User>();
		this.userlist.add(this.user);
	    
	    MockitoAnnotations.initMocks(this);
	    
	    when(this.request.getSession()).thenReturn(session);
	    when(this.session.getAttribute("loggedUser")).thenReturn(this.user);
	    when(this.userservice.loginUser(Mockito.notNull())).thenReturn(this.user);
	    when(this.userservice.registerUser(Mockito.notNull())).thenReturn(this.user);
	    when(this.userservice.updateProfile(this.user)).thenReturn(this.user);
	}
	
	/**
	 * Test to make sure the user controller logs in the user correctly.
	 * @throws Exception 
	 */

	@Test
	public void testLoginUser() throws Exception {
		assertEquals(this.user, this.usercontroller.loginUser(this.session, "test1", "password"));
	}
	
	/**
	 * Test to make sure the user controller can retrieve a user correctly.
	 * @throws Exception 
	 */

	@Test
	public void testGetUser() throws Exception {
		this.usercontroller.getUser(this.session);
		assertEquals(this.user, this.usercontroller.loginUser(this.session, "test1", "password"));
	}
	
	/**
	 * Test to make sure the user controller can register a user correctly.
	 * @throws Exception 
	 */

	@Test
	public void testRegisterUser() throws Exception {
		User registeredUser = this.usercontroller.registerUser(this.session, "Test", "User", "Testy", "01/01/2001", "test1", "password", "private");
		assertEquals(this.user, registeredUser);
	}
	
	/**
	 * Test to make sure the user controller can update a user's profile correctly.
	 * @throws Exception 
	 */

	@Test
	public void testUpdateProfileWithValidUser() throws Exception {
		assertEquals(this.user, this.usercontroller.updateProfile(this.user, this.session));
	}
	
	/**
	 * Test to make sure the updateProfile method returns null if the session has expired.
	 * @throws Exception 
	 */

	@Test
	public void testUpdateProfileWithNullUser() throws Exception {
		when(this.session.getAttribute("loggedUser")).thenReturn(null);
		assertNull(this.usercontroller.updateProfile(this.user, this.session));
	}
}