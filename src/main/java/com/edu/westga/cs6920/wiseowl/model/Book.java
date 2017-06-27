package com.edu.westga.cs6920.wiseowl.model;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@NamedQueries({
		@NamedQuery(name="Book.getCompBooksQuery",
				query="SELECT b " +
						"FROM Book b " +
						"WHERE b.book_read_date IS NOT NULL AND "
						+ "b.create_user.userAuth.username=:username ORDER BY b.book_read_date DESC")
})

public class Book implements Serializable
{

private static final long serialVersionUID = 1L;
	
@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
private long book_ID;

private String book_title;
private String book_author_name;

@Temporal(TemporalType.DATE)
@JsonFormat(shape=JsonFormat.Shape.STRING,timezone="CST", pattern = "yyyy/MM/dd")//this is for jackson to correctly convert JSON date to Java date when the user submits from html page
private Date book_publish_date;

private String book_ISBN_13;
private String book_notes;
private String book_read_comments;

@Temporal(TemporalType.DATE)//this is needed so that JPA can map it to the correct data type in the database
@JsonFormat(shape=JsonFormat.Shape.STRING,timezone="CST", pattern = "yyyy/MM/dd")//this is for jackson to correctly convert JSON date to Java date when the user submits from html page
private Date book_read_date;

private String book_read_rating;

@ManyToOne
@JoinColumn(name="BOOK_READ_SOURCE_ID")
private BookSource book_read_source;

@ManyToOne
@JoinColumn(name="BOOK_READ_FORMAT_ID")
private BookFormat book_read_format;


@OneToOne
@JoinColumn(name="CREATE_USER_ID")
private User create_user; //populated by the application based on the logged in user

@Temporal(TemporalType.TIMESTAMP)
private Date create_datetime;

@Temporal(TemporalType.TIMESTAMP)//this is needed so that JPA can map it to the correct data type in the database
private Date update_datetime; 



public Book(){};

public Book(String bookTitle) throws Exception
{
	this.book_title=bookTitle;//mandatory field
}


public Book(String bookTitle, String bookAuthor, String publishDate, String isbn13, String notes) throws Exception
{
	this.book_title=bookTitle;//mandatory field
	this.book_author_name=bookAuthor;//mandatory field
		
	DateFormat format = new SimpleDateFormat("MM/dd/yyyy", Locale.ENGLISH);
	if(publishDate!=null && publishDate.trim()!="")
	{
		Date date = format.parse(publishDate);
		this.book_publish_date=date;
	}
		
	if(isbn13!=null && isbn13.trim()!="")this.book_ISBN_13=isbn13;
	if(notes!=null && notes.trim()!="")this.book_notes=notes;
}

public Book(String bookTitle, String bookAuthor, String publishDate, String isbn13, String notes, String comments, String readDate, String rating, BookSource source, BookFormat readFormat) throws Exception
{

	this(bookTitle, bookAuthor, publishDate, isbn13, notes);
	
	if(comments!=null && comments.trim()!="")this.book_read_comments=comments;
	
	DateFormat format = new SimpleDateFormat("MM/dd/yyyy", Locale.ENGLISH);
	if(readDate!=null && readDate.trim()!="")
	{
		Date date = format.parse(readDate);
		this.book_read_date=date;
	}
	
	this.book_read_rating=rating;//mandatory
	this.book_read_source=source;//mandatory
	this.book_read_format=readFormat;//mandatory
}



/**
 * Returns the Book_ID, when JSON response is formed from Book.java to JS object. Not having this getter is not returning ID to the angular client browser
 * @return the book_ID
 */
public long getBook_ID() {
	return book_ID;
}


public String getBook_title() {
	return book_title;
}

public void setBook_title(String book_title) {
	this.book_title = book_title;
}

public String getBook_author_name() {
	return book_author_name;
}

public void setBook_author_name(String book_author_name) {
	this.book_author_name = book_author_name;
}


public Date getBook_publish_date() {
	return book_publish_date;
}

public void setBook_publish_date(Date book_publish_date) {
	this.book_publish_date = book_publish_date;
}


public String getBook_ISBN_13() {
	return book_ISBN_13;
}

public void setBook_ISBN_13(String book_ISBN_13) {
	this.book_ISBN_13 = book_ISBN_13;
}

public String getBook_notes() {
	return book_notes;
}

public void setBook_notes(String book_notes) {
	this.book_notes = book_notes;
}

public User getCreate_user() {
	return create_user;
}

public void setCreate_user(User create_user) {
	this.create_user = create_user;
}

public Date getCreate_datetime() {
	return create_datetime;
}


public Date getUpdate_datetime() {
	return update_datetime;
}

public String getBook_read_comments() {
	return book_read_comments;
}


public Date getBook_read_date() {
	return book_read_date;
}

public void setBook_read_date(Date book_read_date) {
	this.book_read_date = book_read_date;
}

public String getBook_read_rating() {
	return book_read_rating;
}

public void setBook_read_rating(String book_read_rating) {
	this.book_read_rating = book_read_rating;
}

public BookSource getBook_read_source() {
	return book_read_source;
}

public void setBook_read_source(BookSource book_read_source) {
	this.book_read_source = book_read_source;
}

public BookFormat getBook_read_format() {
	return book_read_format;
}

public void setBook_read_format(BookFormat book_read_format) {
	this.book_read_format = book_read_format;
}

public void setBook_read_comments(String book_read_comments) {
	this.book_read_comments = book_read_comments;
}

}
