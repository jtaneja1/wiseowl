package com.edu.westga.cs6920.wiseowl.tests;

import static org.junit.Assert.*;

import org.junit.Test;

import com.edu.westga.cs6920.wiseowl.model.BookFormat;

/**
 * This JUnit test class tests to make sure the constructor, getter, and setter methods
 * of the BookFormat class work properly.
 * 
 * @author David Bennett
 * @version 6/25/2017
 */
public class BookFormatTest {

	private BookFormat bookformat;
	
	/**
	 * Test to make sure the constructed book format has the correct ID.
	 * @throws Exception 
	 */
	@Test
	public void testConstructedBookFormatHasBookFormatID() throws Exception {
		this.bookformat = new BookFormat("12345");
		assertEquals(12345, this.bookformat.getBookformat_ID());
	}
	
	/**
	 * Test to make sure the book format ID setter functions correctly.
	 * @throws Exception 
	 */
	@Test
	public void testSetBookFormatID() throws Exception {
		this.bookformat = new BookFormat("12345");
		this.bookformat.setBookformat_ID(67890);
		assertEquals(67890, this.bookformat.getBookformat_ID());
	}
	
	/**
	 * Test to make sure the book format code getter and setter methods function correctly.
	 * @throws Exception 
	 */
	@Test
	public void testSetBookFormatCode() throws Exception {
		this.bookformat = new BookFormat("12345");
		this.bookformat.setBookformat_code("CODE");
		assertEquals("CODE", this.bookformat.getBookformat_code());
	}
	
	/**
	 * Test to make sure the book format name getter and setter methods function correctly.
	 * @throws Exception 
	 */
	@Test
	public void testSetBookFormatName() throws Exception {
		this.bookformat = new BookFormat("12345");
		this.bookformat.setBookformat_name("Name");
		assertEquals("Name", this.bookformat.getBookformat_name());
	}
	
	/**
	 * Test to make sure the book format description getter and setter methods function correctly.
	 * @throws Exception 
	 */
	@Test
	public void testSetBookFormatDesc() throws Exception {
		this.bookformat = new BookFormat("12345");
		this.bookformat.setBookformat_desc("Description");
		assertEquals("Description", this.bookformat.getBookformat_desc());
	}
}
