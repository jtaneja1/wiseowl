package com.edu.westga.cs6920.wiseowl.controller;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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
	
	
	private String bytesToHex(byte[] bytes) {
	    StringBuffer result = new StringBuffer();
	    for (byte b : bytes) result.append(Integer.toString((b & 0xff) + 0x100, 16).substring(1));
	    return result.toString();
	}
	
}