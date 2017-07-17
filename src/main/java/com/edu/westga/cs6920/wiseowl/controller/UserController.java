package com.edu.westga.cs6920.wiseowl.controller;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.edu.westga.cs6920.wiseowl.model.*;

@Controller
@RequestMapping("/user")
public class UserController 
{
	@Autowired
	private com.edu.westga.cs6920.wiseowl.service.UserService userService;
	
	final Logger logger = LoggerFactory.getLogger(UserController.class);	
	
		
	@RequestMapping(value="loginUser", method = RequestMethod.POST, produces="application/json")
	@ResponseBody
	public User loginUser(HttpSession session, String username, String password) throws Exception
	{		
		logger.info("loginUser Invoked by user {}",username);
		if(username==null || username.trim()=="" || password==null || password.trim() == "") {return null;}
		
		MessageDigest md = MessageDigest.getInstance("SHA-256");
		byte[] password_hash = md.digest(password.getBytes(StandardCharsets.UTF_8));
		String encoded_password = bytesToHex(password_hash);
		
		UserAuth userAuth = new UserAuth(username, encoded_password);
		User user = userService.loginUser(userAuth);		
		if(user!=null)session.setAttribute("loggedUser", user);
		return user;		
	}
	
	
	@RequestMapping(value="getUser", method = RequestMethod.GET, produces="application/json")
	@ResponseBody
	public User getUser(HttpSession session) throws Exception
	{
		User user = (User) session.getAttribute("loggedUser");
		return user;		
	}
	
	
	private String bytesToHex(byte[] bytes) {
	    StringBuffer result = new StringBuffer();
	    for (byte b : bytes) result.append(Integer.toString((b & 0xff) + 0x100, 16).substring(1));
	    return result.toString();
	}
	
	
	@RequestMapping(value="registerUser", method = RequestMethod.POST, produces="application/json")
	@ResponseBody
	public User registerUser(HttpSession session, String firstname, String lastname, String nickname, String dob, String username, String password, String password2) throws Exception
	{
		logger.info("registerUser Invoked by user {}",username);
		MessageDigest md = MessageDigest.getInstance("SHA-256");
		byte[] password_hash = md.digest(password.trim().getBytes(StandardCharsets.UTF_8));
		String encoded_password = bytesToHex(password_hash);

		User user = new User(firstname, lastname, nickname, dob, username, encoded_password);
		user=userService.registerUser(user);
		return user;		
	}
	
	
	@RequestMapping(value="updateprofile", method = RequestMethod.POST, produces="application/json")
	@ResponseBody
	public User updateProfile( @RequestBody User user_, HttpSession session) throws Exception
	{
		logger.info("updateProfile Invoked for User with firstname:" + user_.getFirstname());
		User user = (User) session.getAttribute("loggedUser");
		if(user==null){return null;} //If the session has expired, send null so that the user is redirected to the login page

		//book.setUpdate_user(user);
		user=userService.updateProfile(user_);
		if(user!=null)session.setAttribute("loggedUser", user);
		return user;
	}
	
	
	@RequestMapping(value="logout", method = RequestMethod.GET, produces="application/json")
	@ResponseBody
	public void logoutUser(HttpSession session) throws Exception
	{
		logger.info("logoutUser Invoked");
		session.invalidate();
	}
	
}
