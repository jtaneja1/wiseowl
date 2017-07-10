package com.edu.westga.cs6920.wiseowl.tests;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.edu.westga.cs6920.wiseowl.model.Book;
import com.edu.westga.cs6920.wiseowl.model.User;
import com.edu.westga.cs6920.wiseowl.service.BookService;

/**
 * This JUnit test class tests to make sure the methods of the BookService class work properly.
 * 
 * @author David Bennett
 * @version 6/28/2017
 */
public class BookServiceTest {

	private Book book;
	private List<Book> booklist;
	private HttpServletRequest request;
	private HttpSession session;
	private User user;
	private TypedQuery<Book> query;
	
	@Mock
	private EntityManager em;
	
	@InjectMocks
	private BookService bookservice = new BookService();
	
	@Before
	public void setup() throws Exception
	{     
	    this.request = mock(HttpServletRequest.class);
	    this.session = mock(HttpSession.class);  
	    this.book = new Book("Title");
	    this.booklist = new ArrayList<Book>();
		this.booklist.add(this.book);
		this.query = mock(TypedQuery.class);
	    
	    MockitoAnnotations.initMocks(this);
	    
	    when(this.request.getSession()).thenReturn(session);
	    when(this.session.getAttribute("loggedUser")).thenReturn(this.user);
	    when(this.em.createNamedQuery("Book.getCompBooksQuery", Book.class)).thenReturn(this.query);
	    when(this.em.createNamedQuery("Book.getReviewedBooksQuery", Book.class)).thenReturn(this.query);
	    when(this.em.find(Book.class, new Long(this.book.getBook_ID()))).thenReturn(this.book);
	    when(query.setParameter("username", "test1")).thenReturn(query);
	    when(query.setMaxResults(Mockito.anyInt())).thenReturn(query);
	    when(query.getResultList()).thenReturn(this.booklist);
	}
	
	/**
	 * Test to make sure the addCompBook method properly returns the book given to it.
	 * @throws Exception 
	 */
	@Test
	public void testAddCompBook() throws Exception {
		assertEquals(this.book, this.bookservice.addCompBook(this.book));
	}
	
	/**
	 * Test to make sure the getCompBook method can return a book added to the completed books list.
	 * @throws Exception 
	 */
	@Test
	public void testGetCompBooks() throws Exception {
		this.bookservice.addCompBook(this.book);
		assertEquals(this.booklist, this.bookservice.getCompBooks("test1", 1));
	}
	
	/**
	 * Test to make sure the getReviewedBook method can return a book with a review.
	 * @throws Exception 
	 */
	@Test
	public void testGetReviewedBooks() throws Exception {
		this.book.setBook_read_rating("5");
		this.bookservice.addCompBook(this.book);
		assertEquals(this.booklist, this.bookservice.getReviewedBooks("test1", 1));
	}
	
	/**
	 * Test to make sure the getBookById method can return a book with a review.
	 * @throws Exception 
	 */
	@Test
	public void testGetBookById() throws Exception {
		this.bookservice.addCompBook(this.book);
		assertEquals(this.book, this.bookservice.getBookById((int) this.book.getBook_ID()));
	}
	
	/**
	 * Test to make sure the updateBook method updates the model book object correctly.
	 * @throws Exception 
	 */
	@Test
	public void testUpdateBook() throws Exception {
		assertEquals(this.book, this.bookservice.updateBook(this.book));
	}
}