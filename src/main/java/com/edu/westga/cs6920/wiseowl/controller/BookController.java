package com.edu.westga.cs6920.wiseowl.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
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
	
	/**
	  * Gets invoked when all the books/count of books read by the user have to be returned
	  * @param count the count of the books to return which have been completed by the user
	  * @return the List of Book objects as a JSON object
	  */
	 @RequestMapping(value="getcompbooks/{count}", method = RequestMethod.GET, produces="application/json")
	 @ResponseBody
	 public List<Book> getCompBooks(@PathVariable String count, HttpSession session) throws Exception
	 {
	  logger.info("getCompBooks Invoked");
	  User user = (User) session.getAttribute("loggedUser");
	  if(user==null){return null;} //If the session has expired, send null so that the user is redirected to the login page
	  
	  List<Book> bookList=bookService.getCompBooks(user.getUserAuth().getUsername(),Integer.parseInt(count));
	  return bookList;  
	 }
	 
	 /**
	  * Gets invoked when all the books/count of books reviewed by the user have to be returned
	  * @param count the count of the books to return which have been completed by the user
	  * @return the List of Book objects as a JSON object
	  */
	 @RequestMapping(value="getreviewedbooks/{count}", method = RequestMethod.GET, produces="application/json")
	 @ResponseBody
	 public List<Book> getReviewedBooks(@PathVariable String count, HttpSession session) throws Exception
	 {
	  logger.info("getReviewedBooks Invoked");
	  User user = (User) session.getAttribute("loggedUser");
	  if(user==null){return null;} //If the session has expired, send null so that the user is redirected to the login page
	  
	  List<Book> bookList=bookService.getReviewedBooks(user.getUserAuth().getUsername(),Integer.parseInt(count));
	  return bookList;  
	 }
}
