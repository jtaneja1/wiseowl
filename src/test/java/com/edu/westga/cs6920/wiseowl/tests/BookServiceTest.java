package com.edu.westga.cs6920.wiseowl.tests;

import static org.junit.Assert.*;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Locale;

import org.junit.Test;

import com.edu.westga.cs6920.wiseowl.model.Book;
import com.edu.westga.cs6920.wiseowl.model.BookFormat;
import com.edu.westga.cs6920.wiseowl.model.BookSource;
import com.edu.westga.cs6920.wiseowl.model.User;
import com.edu.westga.cs6920.wiseowl.service.BookService;

/**
 * This JUnit test class tests to make sure the methods of the BookService class work properly.
 * 
 * @author David Bennett
 * @version 6/28/2017
 */
public class BookServiceTest {

	private BookService bookservice;
	private Book book;
	
	/**
	 * Test to make sure the addCompBook method properly returns the book given to it.
	 * @throws Exception 
	 */
	/*
	@Test
	public void testAddCompBook() throws Exception {
		this.bookservice = new BookService();
		this.book = new Book("Title");
		assertEquals(this.book, this.bookservice.addCompBook(this.book));
	}
	*/
	
	/**
	 * Test to make sure the getCompBook method can return a book added to the completed books list.
	 * @throws Exception 
	 */
	/*
	@Test
	public void testGetCompBooks() throws Exception {
		this.bookservice = new BookService();
		this.book = new Book("Title");
		this.bookservice.addCompBook(this.book);
		assertFalse(this.bookservice.getCompBooks("jatin", 1).isEmpty());
	}
	*/
}