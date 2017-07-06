package com.edu.westga.cs6920.wiseowl.service;


import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.edu.westga.cs6920.wiseowl.model.*;
import com.edu.westga.cs6920.wiseowl.controller.*;

@Service("bookService")
@Transactional
public class BookService 
{
	@PersistenceContext
    private EntityManager em;
	
	final Logger logger = LoggerFactory.getLogger(BookService.class);
	
		
	/** Gets the domain Book object from the front-end layer, persists it, and returns it back with
	 * extra fields populated */
	public Book addCompBook(Book book)
	{		
		em.persist(book);// no need to find() User first to set the relationship.
		return book;
	}	
	
	/** Gets the Completed books read by the user based on the parameter sent by the front end GUI as to how many books 
	  * need to be returned. A value of -1 means all the books (with pagination logic) 
	  */
	 public List<Book> getCompBooks(String username,int numberOfBooks)
	 {
	  logger.info("Service:getCompBooks Invoked");
	  //Create an execute a named query defined in Book.java since it is related to the Book table
	  TypedQuery<Book> query = em.createNamedQuery("Book.getCompBooksQuery", Book.class);
	  query.setParameter("username", username);//set the parameter in the named query
	  if(numberOfBooks>0){query.setMaxResults(numberOfBooks);}
	  List<Book> results = query.getResultList();
	  return results;
	 }
	 
	 /** Gets the books reviewed by the user based on the parameter sent by the front end GUI as to how many books 
	  * need to be returned. A value of -1 means all the books (with pagination logic) 
	  */
	 public List<Book> getReviewedBooks(String username,int numberOfBooks)
	 {
	  logger.info("Service:getReviewedBooks Invoked");
	  //Create an execute a named query defined in Book.java since it is related to the Book table
	  TypedQuery<Book> query = em.createNamedQuery("Book.getReviewedBooksQuery", Book.class);
	  query.setParameter("username", username);//set the parameter in the named query
	  if(numberOfBooks>0){query.setMaxResults(numberOfBooks);}
	  List<Book> results = query.getResultList();
	  return results;
	 }
	 
	 /**
	  * Gets the Book object from the database using the Primary key
	  * @param book_ID the primary key which is passed from the UI
	  * @return the Book object in the database
	  */
	 public Book getBookById(int book_ID) {
	  Book book = em.find(Book.class, new Long(book_ID));// The EM find method finds by primary key the row in the database
	  return book;
	 }
	 
	 
	 /** Gets the model Book object from the front-end layer, updates it, and returns it back with
	  * extra fields populated 
	  */
		public Book updateBook(Book book)
		{		
			Book bookFromDB = em.find(Book.class, new Long(book.getBook_ID()));// The EM find method finds by primary key the row in the database
			bookFromDB.setBook_author_name(book.getBook_author_name());
			bookFromDB.setBook_notes(book.getBook_notes());
			bookFromDB.setBook_read_comments(book.getBook_read_comments());
			bookFromDB.setBook_read_date(book.getBook_read_date());
			bookFromDB.setBook_publish_date(book.getBook_publish_date());
			bookFromDB.setBook_read_format(book.getBook_read_format());
			bookFromDB.setBook_read_rating(book.getBook_read_rating());
			bookFromDB.setBook_read_source(book.getBook_read_source());
			bookFromDB.setBook_title(book.getBook_title());
			return bookFromDB;
		}
}
