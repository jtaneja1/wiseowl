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

import com.edu.westga.cs6920.wiseowl.controller.BookController;
import com.edu.westga.cs6920.wiseowl.model.Book;
import com.edu.westga.cs6920.wiseowl.model.User;
import com.edu.westga.cs6920.wiseowl.service.BookService;

/**
 * This JUnit test class tests to make sure the loginUser method
 * of the BookController class work properly.
 * 
 * @author David Bennett
 * @version 7/13/2017
 */
public class BookControllerTest {

	private Book book;
	private User user;
	private List<Book> booklist;
	private HttpServletRequest request;
	private HttpSession session;
	
	@Mock
	private BookService bookservice;
	
	@InjectMocks
	private BookController bookcontroller;
	
	@Before
	public void setup() throws Exception
	{     
		this.bookcontroller = new BookController();
	    this.request = mock(HttpServletRequest.class);
	    this.session = mock(HttpSession.class);  
	    this.book = new Book("Title");
	    this.user = new User("Test", "User", "Testy", "01/01/2001", "test1", "password");
	    this.booklist = new ArrayList<Book>();
		this.booklist.add(this.book);
	    
	    MockitoAnnotations.initMocks(this);
	    
	    when(this.request.getSession()).thenReturn(session);
	    when(this.session.getAttribute("loggedUser")).thenReturn(this.user);
	    when(this.bookservice.addCompBook(this.book)).thenReturn(this.book);
	    when(this.bookservice.getCompBooks(Mockito.anyString(), Mockito.anyInt())).thenReturn(this.booklist);
	    when(this.bookservice.getReviewedBooks(Mockito.anyString(), Mockito.anyInt())).thenReturn(this.booklist);
	    when(this.bookservice.getBookById(Mockito.anyInt())).thenReturn(this.book);
	    when(this.bookservice.updateBook(this.book)).thenReturn(this.book);
	    when(this.bookservice.addForlaterBook(this.book)).thenReturn(this.book);
	}
	
	/**
	 * Test to make sure the addCompBook method returns the book given to it.
	 * @throws Exception 
	 */
	@Test
	public void testAddCompBook() throws Exception {
		assertEquals(this.book, this.bookcontroller.addCompBook(this.book, this.session));
	}
	
	/**
	 * Test to make sure the getCompBook method can return a book added to the completed books list.
	 * @throws Exception 
	 */
	@Test
	public void testGetCompBooks() throws Exception {
		this.bookcontroller.addCompBook(this.book, this.session);
		assertEquals(this.booklist, this.bookcontroller.getCompBooks("1", this.session));
	}
	
	/**
	 * Test to make sure the getReviewedBook method can return a book with a review.
	 * @throws Exception 
	 */
	@Test
	public void testGetReviewedBooks() throws Exception {
		this.book.setBook_read_rating("5");
		this.bookcontroller.addCompBook(this.book, this.session);
		assertEquals(this.booklist, this.bookcontroller.getCompBooks("1", this.session));
	}
	
	/**
	 * Test to make sure the geteditbook method can return a book using its ID.
	 * @throws Exception 
	 */
	@Test
	public void testGetEditBook() throws Exception {
		this.bookcontroller.addCompBook(this.book, this.session);
		assertEquals(this.book, this.bookcontroller.geteditbook("1", this.session));
	}
	
	/**
	 * Test to make sure the updateBook method updates the model book object correctly.
	 * @throws Exception 
	 */
	@Test
	public void testUpdateBook() throws Exception {
		assertEquals(this.book, this.bookcontroller.updateCompBook(this.book, this.session));
	}
	
	/**
	 * Test to make sure the addForlaterBook method returns the book given to it.
	 * @throws Exception 
	 */
	@Test
	public void testAddForLaterBook() throws Exception {
		assertEquals(this.book, this.bookcontroller.addForlaterBook(this.book, this.session));
	}
}