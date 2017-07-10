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
 * @version 6/21/2017
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
	}
	
	/**
	 * Test to make sure the user controller logs in the user correctly.
	 * @throws Exception 
	 */

	@Test
	public void testLoginUser() throws Exception {
		assertEquals(this.user, this.usercontroller.loginUser(this.session, "test", "password1"));
	}
	
	/**
	 * Test to make sure the user controller logs in the user correctly.
	 * @throws Exception 
	 */

	@Test
	public void testGetUser() throws Exception {
		this.usercontroller.getUser(this.session);
		assertEquals(this.user, this.usercontroller.loginUser(this.session, "test", "password1"));
	}
}