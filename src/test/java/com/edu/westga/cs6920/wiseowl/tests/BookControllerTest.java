package com.edu.westga.cs6920.wiseowl.tests;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import javax.servlet.http.HttpSession;

import org.junit.Test;

import com.edu.westga.cs6920.wiseowl.controller.BookController;
import com.edu.westga.cs6920.wiseowl.controller.UserController;
import com.edu.westga.cs6920.wiseowl.model.Book;

/**
 * This JUnit test class tests to make sure the loginUser method
 * of the BookController class work properly.
 * 
 * @author David Bennett
 * @version 6/28/2017
 */
public class BookControllerTest {

	private HttpSession session;
	private BookController bookcontroller;
	private Book book;
	
	/**
	 * Test to make sure the book controller logs returns the book given to it.
	 * @throws Exception 
	 */
	/*
	@Test
	public void testAddCompBook() throws Exception {
		this.bookcontroller = new BookController();
		this.book = new Book("Title");
		assertEquals(this.book, this.bookcontroller.addCompBook(this.book, this.session));
	}
	*/
	
	/**
	 * Test to make sure the getCompBook method can return a book added to the completed books list.
	 * @throws Exception 
	 */
	/*
	@Test
	public void testGetCompBooks() throws Exception {
		this.bookcontroller = new BookController();
		this.book = new Book("Title");
		this.bookcontroller.addCompBook(this.book);
		assertFalse(this.bookcontroller.getCompBooks(1, this.session).isEmpty());
	}
	*/
}