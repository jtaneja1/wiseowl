package com.edu.westga.cs6920.wiseowl.tests;

import static org.junit.Assert.*;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import org.junit.Test;

import com.edu.westga.cs6920.wiseowl.model.User;
import com.edu.westga.cs6920.wiseowl.model.UserAuth;
import com.edu.westga.cs6920.wiseowl.model.UserRole;

/**
 * This JUnit test class tests to make sure the constructor, getter, and setter methods
 * of the User class work properly.
 * 
 * @author David Bennett
 * @version 6/17/2017
 */
public class TestUser {

	private User user;
	
	/**
	 * Test to make sure the constructed user has a valid user ID.
	 * @throws Exception 
	 */
	@Test
	public void constructedUserHasUserID() throws Exception {
		this.user = new User("firstname", "lastname", "nickname", "01/02/2003", "jatin", "password1");
		assertNotNull(this.user.getUser_ID());
	}
	
	/**
	 * Test to make sure the user's user ID setter functions correctly.
	 * @throws Exception 
	 */
	@Test
	public void testSetUserID() throws Exception {
		this.user = new User();
		user.setUser_ID(1);
		assertEquals(1, user.getUser_ID());
	}
	
	/**
	 * Test to make sure the constructed user has the correct first name.
	 * @throws Exception 
	 */
	@Test
	public void constructedUserHasCorrectFirstName() throws Exception {
		this.user = new User("firstname", "lastname", "nickname", "01/02/2003", "jatin", "password1");
		assertEquals("firstname", this.user.getFirstname());
	}
	
	/**
	 * Test to make sure the user's first name setter functions correctly.
	 * @throws Exception 
	 */
	@Test
	public void testSetFirstName() throws Exception {
		this.user = new User();
		user.setFirstname("firstname");
		assertEquals("firstname", this.user.getFirstname());
	}

	/**
	 * Test to make sure the constructed user has the correct last name.
	 * @throws Exception 
	 */
	@Test
	public void constructedUserHasCorrectLastName() throws Exception {
		this.user = new User("firstname", "lastname", "nickname", "01/02/2003", "jatin", "password1");
		assertEquals("lastname", this.user.getLastname());
	}
	
	/**
	 * Test to make sure the user's last name setter functions correctly.
	 * @throws Exception 
	 */
	@Test
	public void testSetLastName() throws Exception {
		this.user = new User();
		user.setLastname("lastname");
		assertEquals("lastname", this.user.getLastname());
	}
	
	/**
	 * Test to make sure the constructed user has the correct nickname.
	 * @throws Exception 
	 */
	@Test
	public void constructedUserHasCorrectNickname() throws Exception {
		this.user = new User("firstname", "lastname", "nickname", "01/02/2003", "jatin", "password1");
		assertEquals("nickname", this.user.getNickname());
	}
	
	/**
	 * Test to make sure the user's nickname setter functions correctly.
	 * @throws Exception 
	 */
	@Test
	public void testSetNickName() throws Exception {
		this.user = new User();
		user.setNickname("nickname");
		assertEquals("nickname", this.user.getNickname());
	}
	
	/**
	 * Test to make sure the constructed user has the correct date of birth.
	 * @throws Exception 
	 */
	@Test
	public void constructedUserHasCorrectDOB() throws Exception {
		this.user = new User("firstname", "lastname", "nickname", "01/02/2003", "jatin", "password1");
		DateFormat format = new SimpleDateFormat("MM/dd/yyyy", Locale.ENGLISH);
		String dateString = format.format(this.user.getDob());
		assertEquals("01/02/2003", dateString);
	}
	
	/**
	 * Test to make sure the user's DOB setter functions correctly.
	 * @throws Exception 
	 */
	@Test
	public void testSetDOB() throws Exception {
		this.user = new User();
		Calendar calendar = Calendar.getInstance();
		calendar.set(2000, 0, 1);
		user.setDob(calendar.getTime());
		
		DateFormat format = new SimpleDateFormat("MM/dd/yyyy", Locale.ENGLISH);
		String dateString = format.format(this.user.getDob());
		assertEquals("01/01/2000", dateString);
	}
	
	/**
	 * Test to make sure the constructed user does not have a created date. I need to ask whether new users are supposed to have 
	 * a create date attached to them.
	 * @throws Exception 
	 */
	@Test
	public void constructedUserHasNoCreateDateTime() throws Exception {
		this.user = new User("firstname", "lastname", "nickname", "01/02/2003", "jatin", "password1");
		assertNull(this.user.getCreate_datetime());
	}
	
	/**
	 * Test to make sure the constructed user does not have an updated date. I need to ask whether new users are supposed to have 
	 * an update date attached to them.
	 * @throws Exception 
	 */
	@Test
	public void constructedUserHasNoUpdateDateTime() throws Exception {
		this.user = new User("firstname", "lastname", "nickname", "01/02/2003", "jatin", "password1");
		assertNull(this.user.getUpdate_datetime());
	}
	
	/**
	 * Test to make sure the user's user role setter functions correctly.
	 * @throws Exception 
	 */
	@Test
	public void testSetUserRole() throws Exception {
		this.user = new User("firstname", "lastname", "nickname", "01/02/2003", "jatin", "password1");
		UserRole userrole = new UserRole();
		user.setUserRole(userrole);
		assertEquals(this.user.getUserRole(), userrole);
	}
	
	/**
	 * Test to make sure the constructed user has a UserAuth.
	 * @throws Exception 
	 */
	@Test
	public void constructedUserHasUserAuth() throws Exception {
		this.user = new User("firstname", "lastname", "nickname", "01/02/2003", "jatin", "password1");
		assertNotNull(this.user.getUserAuth());
	}
	
	/**
	 * Test to make sure the user's user role setter functions correctly.
	 * @throws Exception 
	 */
	@Test
	public void testSetUserAuth() throws Exception {
		this.user = new User("firstname", "lastname", "nickname", "01/02/2003", "jatin", "password1");
		UserAuth userauth = new UserAuth();
		user.setUserAuth(userauth);
		assertEquals(this.user.getUserAuth(), userauth);
	}
}
