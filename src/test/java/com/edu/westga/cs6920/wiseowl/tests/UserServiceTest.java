package com.edu.westga.cs6920.wiseowl.tests;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;
import javax.persistence.EntityManager;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.edu.westga.cs6920.wiseowl.model.User;
import com.edu.westga.cs6920.wiseowl.model.UserAuth;
import com.edu.westga.cs6920.wiseowl.model.UserRole;
import com.edu.westga.cs6920.wiseowl.service.UserService;

/**
 * This JUnit test class tests to make sure the loginUser method
 * of the UserService class work properly.
 * 
 * @author David Bennett
 * @version 7/13/2017
 */
public class UserServiceTest {

	private UserAuth userauth;
	private User user;
	private List<User> userlist;
	private List<User> emptyuserlist;
	private Query query;
	private UserRole userrole;
	
	@Mock
	private EntityManager em;
	
	@InjectMocks
	private UserService userservice;
	
	@Before
	public void setup() throws Exception
	{      
		this.user = new User("Test", "User", "Testy", "01/01/2001", "test1", "password");
		this.userlist = new ArrayList<User>();
		this.userlist.add(this.user);
		this.emptyuserlist = new ArrayList<User>();
		this.userauth = new UserAuth("test1", "password");
		this.userservice = new UserService();
		this.query = mock(Query.class);
		
		MockitoAnnotations.initMocks(this);
	    
		when(this.em.createQuery(Mockito.anyString())).thenReturn(this.query);
		when(this.query.setParameter(Mockito.anyInt(), Mockito.anyString())).thenReturn(this.query);
		when(this.em.find(UserRole.class, new Long(2))).thenReturn(this.userrole);
		when(this.em.find(User.class, new Long(this.user.getUser_ID()))).thenReturn(this.user);
	}
	
	/**
	 * Test to make sure the user service logs in the user correctly.
	 * @throws Exception 
	 */

	@Test
	public void testSetUserRoleID() throws Exception {
		when(this.query.getResultList()).thenReturn(this.userlist);
		
		assertNotNull(this.userservice.loginUser(this.userauth));
	}
	
	/**
	 * Test to make sure the user service can register a user correctly.
	 * @throws Exception 
	 */

	@Test
	public void testRegisterUser() throws Exception {
		when(this.query.getResultList()).thenReturn(this.emptyuserlist);
		
		assertEquals(this.user, this.userservice.registerUser(this.user));
	}
	
	/**
	 * Test to make sure the user service can update a user's profile correctly.
	 * @throws Exception 
	 */

	@Test
	public void testUpdateUser() throws Exception {
		assertEquals(this.user, this.userservice.updateProfile(this.user));
	}
}