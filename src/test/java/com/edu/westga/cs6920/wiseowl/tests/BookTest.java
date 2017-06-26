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

/**
 * This JUnit test class tests to make sure the constructor, getter, and setter methods
 * of the BookFormat class work properly.
 * 
 * @author David Bennett
 * @version 6/25/2017
 */
public class BookTest {

	private Book book;
	private DateFormat dateformat;
	private BookSource booksource;
	private BookFormat bookformat;
	private User user;
	
	/**
	 * Test to make sure the 1-variable book constructor creates a book with the correct title.
	 * @throws Exception 
	 */
	@Test
	public void testOneVariableConstructor() throws Exception {
		this.book = new Book("Title");
		assertEquals("Title", this.book.getBook_title());
	}
	
	/**
	 * Test to make sure the 5-variable book constructor creates a book with the correct variables when all of the variables are provided.
	 * @throws Exception 
	 */
	@Test
	public void testFiveVariableConstructorAllFields() throws Exception {
		DateFormat format = new SimpleDateFormat("MM/dd/yyyy", Locale.ENGLISH);
		this.book = new Book("Title", "Author", "01/01/2001", "1234567890123", "Notes");
		assertEquals("Title", this.book.getBook_title());
		assertEquals("Author", this.book.getBook_author_name());
		assertEquals("01/01/2001", format.format(this.book.getBook_publish_date()));
		assertEquals("1234567890123", this.book.getBook_ISBN_13());
		assertEquals("Notes", this.book.getBook_notes());
	}
	
	/**
	 * Test to make sure the 5-variable book constructor creates a book with the correct variables when only the mandatory variables are 
	 * provided.
	 * @throws Exception 
	 */
	@Test
	public void testFiveVariableConstructorMandatoryOnly() throws Exception {
		this.book = new Book("Title", "Author", "", "", "");
		assertEquals("Title", this.book.getBook_title());
		assertEquals("Author", this.book.getBook_author_name());
		assertNull(this.book.getBook_publish_date());
		assertNull(this.book.getBook_ISBN_13());
		assertNull(this.book.getBook_notes());
	}
	
	/**
	 * Test to make sure the 10-variable book constructor creates a book with the correct variables when all of the variables are provided.
	 * @throws Exception 
	 */
	@Test
	public void testTenVariableConstructorAllFields() throws Exception {
		this.dateformat = new SimpleDateFormat("MM/dd/yyyy", Locale.ENGLISH);
		this.booksource = new BookSource();
		this.bookformat = new BookFormat();
		this.book = new Book("Title", "Author", "01/01/2001", "1234567890123", "Notes", "Comments", "01/01/2017", "5", this.booksource, this.bookformat);
		assertEquals("Title", this.book.getBook_title());
		assertEquals("Author", this.book.getBook_author_name());
		assertEquals("01/01/2001", dateformat.format(this.book.getBook_publish_date()));
		assertEquals("1234567890123", this.book.getBook_ISBN_13());
		assertEquals("Notes", this.book.getBook_notes());
		assertEquals("Comments", this.book.getBook_read_comments());
		assertEquals("01/01/2017", dateformat.format(this.book.getBook_read_date()));
		assertEquals("5", this.book.getBook_read_rating());
		assertEquals(this.booksource, this.book.getBook_read_source());
		assertEquals(this.bookformat, this.book.getBook_read_format());
	}
	
	/**
	 * Test to make sure the 10-variable book constructor creates a book with the correct variables when only the mandatory variables are 
	 * provided.
	 * @throws Exception 
	 */
	@Test
	public void testTenVariableConstructorMandatoryOnly() throws Exception {
		this.booksource = new BookSource();
		this.bookformat = new BookFormat();
		this.book = new Book("Title", "Author", "", "", "", "", "", "5", this.booksource, this.bookformat);
		assertEquals("Title", this.book.getBook_title());
		assertEquals("Author", this.book.getBook_author_name());
		assertNull(this.book.getBook_publish_date());
		assertNull(this.book.getBook_ISBN_13());
		assertNull(this.book.getBook_notes());
		assertNull(this.book.getBook_read_comments());
		assertNull(this.book.getBook_read_date());
		assertEquals("5", this.book.getBook_read_rating());
		assertEquals(this.booksource, this.book.getBook_read_source());
		assertEquals(this.bookformat, this.book.getBook_read_format());
	}
	
	/**
	 * Test to make sure the book title's setter method functions properly.
	 * @throws Exception 
	 */
	@Test
	public void testSetBookTitle() throws Exception {
		this.book = new Book("Title");
		this.book.setBook_title("Another Title");
		assertEquals("Another Title", this.book.getBook_title());
	}
	
	/**
	 * Test to make sure the book author name's setter method functions properly.
	 * @throws Exception 
	 */
	@Test
	public void testSetBookAuthorName() throws Exception {
		this.book = new Book("Title");
		this.book.setBook_author_name("Author");
		assertEquals("Author", this.book.getBook_author_name());
	}
	
	/**
	 * Test to make sure the book publish date's setter method functions properly.
	 * @throws Exception 
	 */
	@Test
	public void testSetBookPublishDate() throws Exception {
		this.dateformat = new SimpleDateFormat("MM/dd/yyyy", Locale.ENGLISH);
		this.book = new Book("Title");
		this.book.setBook_publish_date(this.dateformat.parse("01/01/2001"));
		assertEquals("01/01/2001", dateformat.format(this.book.getBook_publish_date()));
	}
	
	/**
	 * Test to make sure the book ISBN 13's setter method functions properly.
	 * @throws Exception 
	 */
	@Test
	public void testSetBookISBN13() throws Exception {
		this.book = new Book("Title");
		this.book.setBook_ISBN_13("1234567890123");
		assertEquals("1234567890123", this.book.getBook_ISBN_13());
	}
	
	/**
	 * Test to make sure the book notes's setter method functions properly.
	 * @throws Exception 
	 */
	@Test
	public void testSetBookNotes() throws Exception {
		this.book = new Book("Title");
		this.book.setBook_notes("Notes");
		assertEquals("Notes", this.book.getBook_notes());
	}
	
	/**
	 * Test to make sure the book create user's setter and getter methods function properly.
	 * @throws Exception 
	 */
	@Test
	public void testSetCreateUser() throws Exception {
		this.user = new User();
		this.book = new Book("Title");
		this.book.setCreate_user(this.user);
		assertEquals(this.user, this.book.getCreate_user());
	}
	
	/**
	 * Test to make sure the constructed book does not have a created date. I need to ask whether new books are supposed to have 
	 * a create date attached to them.
	 * @throws Exception 
	 */
	@Test
	public void testBookHasNoCreateDateTime() throws Exception {
		this.book = new Book("Title");
		assertNull(this.book.getCreate_datetime());
	}
	
	/**
	 * Test to make sure the constructed book does not have an update date. I need to ask whether new books are supposed to have 
	 * a create date attached to them.
	 * @throws Exception 
	 */
	@Test
	public void testBookHasNoUpdateDateTime() throws Exception {
		this.book = new Book("Title");
		assertNull(this.book.getUpdate_datetime());
	}
	
	/**
	 * Test to make sure the book comments's setter method functions properly.
	 * @throws Exception 
	 */
	@Test
	public void testSetReadComments() throws Exception {
		this.book = new Book("Title");
		this.book.setBook_read_comments("Comments");
		assertEquals("Comments", this.book.getBook_read_comments());
	}
	
	/**
	 * Test to make sure the book read date's setter method functions properly.
	 * @throws Exception 
	 */
	@Test
	public void testSetBookReadDate() throws Exception {
		this.dateformat = new SimpleDateFormat("MM/dd/yyyy", Locale.ENGLISH);
		this.book = new Book("Title");
		this.book.setBook_read_date(this.dateformat.parse("01/01/2017"));
		assertEquals("01/01/2017", dateformat.format(this.book.getBook_read_date()));
	}
	
	/**
	 * Test to make sure the book rating's setter method functions properly.
	 * @throws Exception 
	 */
	@Test
	public void testSetReadRating() throws Exception {
		this.book = new Book("Title");
		this.book.setBook_read_rating("5");
		assertEquals("5", this.book.getBook_read_rating());
	}
	
	/**
	 * Test to make sure the book read source's setter method functions properly.
	 * @throws Exception 
	 */
	@Test
	public void testSetReadSource() throws Exception {
		this.booksource = new BookSource();
		this.book = new Book("Title");
		this.book.setBook_read_source(this.booksource);
		assertEquals(this.booksource, this.book.getBook_read_source());
	}
	
	/**
	 * Test to make sure the book read format's setter method functions properly.
	 * @throws Exception 
	 */
	@Test
	public void testSetReadFormat() throws Exception {
		this.bookformat = new BookFormat();
		this.book = new Book("Title");
		this.book.setBook_read_format(this.bookformat);
		assertEquals(this.bookformat, this.book.getBook_read_format());
	}
}