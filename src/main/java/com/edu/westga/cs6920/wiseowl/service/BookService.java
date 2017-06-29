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
	
	final Logger logger = LoggerFactory.getLogger(UserController.class);
	
		
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
}
