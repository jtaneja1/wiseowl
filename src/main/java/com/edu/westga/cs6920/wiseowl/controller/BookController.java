package com.edu.westga.cs6920.wiseowl.controller;

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
import com.edu.westga.cs6920.wiseowl.service.*;

@Controller
@RequestMapping("/book")
public class BookController 
{
	@Autowired
	private BookService bookService;
	
	final Logger logger = LoggerFactory.getLogger(BookController.class);

	
	@RequestMapping(value="addCompBook", method = RequestMethod.POST, produces="application/json")
	@ResponseBody
	public Book addCompBook( @RequestBody Book book, HttpSession session) throws Exception
	{
		User user = (User) session.getAttribute("loggedUser");
		if(user==null){return null;} //If the session has expired, send null so that the user is redirected to the login page
		
		book.setCreate_user(user);
		book=bookService.addCompBook(book);		
		return book;		
	} 	
	
}
