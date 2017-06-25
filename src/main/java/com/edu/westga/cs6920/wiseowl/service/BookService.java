package com.edu.westga.cs6920.wiseowl.service;


import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

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
	
}
