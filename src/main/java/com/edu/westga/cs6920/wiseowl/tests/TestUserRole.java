package com.edu.westga.cs6920.wiseowl.tests;

import static org.junit.Assert.*;

import org.junit.Test;

import com.edu.westga.cs6920.wiseowl.model.UserAuth;
import com.edu.westga.cs6920.wiseowl.model.UserRole;

/**
 * This JUnit test class tests to make sure the constructor, getter, and setter methods
 * of the UserRole class work properly.
 * 
 * @author David Bennett
 * @version 6/17/2017
 */
public class TestUserRole {

	private UserRole userrole;
	
	/**
	 * Test to make sure the user role's user role ID setter functions correctly.
	 * @throws Exception 
	 */
	@Test
	public void testSetUserRoleID() throws Exception {
		this.userrole = new UserRole();
		userrole.setUser_role_ID(1);
		assertEquals(1, userrole.getUser_role_ID());
	}
	
	/**
	 * Test to make sure the user role's role code setter functions correctly.
	 * @throws Exception 
	 */
	@Test
	public void testSetUserRoleCode() throws Exception {
		this.userrole = new UserRole();
		userrole.setRole_code("CODE");
		assertEquals("CODE", this.userrole.getRole_code());
	}

	/**
	 * Test to make sure the user role's role name setter functions correctly.
	 * @throws Exception 
	 */
	@Test
	public void testSetUserRoleName() throws Exception {
		this.userrole = new UserRole();
		userrole.setRole_name("rolename");
		assertEquals("rolename", this.userrole.getRole_name());
	}
	
	/**
	 * Test to make sure the user role's role code setter functions correctly.
	 * @throws Exception 
	 */
	@Test
	public void testSetUserRoleDesc() throws Exception {
		this.userrole = new UserRole();
		userrole.setRole_desc("Description");
		assertEquals("Description", this.userrole.getRole_desc());
	}
}
