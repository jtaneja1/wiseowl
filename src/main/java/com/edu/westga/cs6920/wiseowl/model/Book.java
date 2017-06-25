package com.edu.westga.cs6920.wiseowl.model;

import java.io.Serializable;
import java.sql.Timestamp;
import java.time.LocalDate;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="booksource")
public class Book implements Serializable
{
	 /**
	  * 
	  */
	 private static final long serialVersionUID = 3L;
	 @Id
	 private long book_ID;
	 private String book_title;
	 private String book_author_name;
	 private LocalDate book_publish_date;
	 private String book_isbn_13;
	 private String book_notes;
	 private String book_read_comments;
	 private long book_owned_format_ID;
	 private LocalDate book_read_date;
	 private String book_read_rating;
	 private long book_read_source_ID;
	 private long book_read_format_ID;
	 private long create_user_ID;
	 private Timestamp create_datetime;
	 private Timestamp update_datetime;
	 
	 public Book()
	 { }
	
	 public Book(String book_ID)
	 {
		 this.book_ID=Long.parseLong(book_ID);
	 }
	 
	 public long getBook_ID() {
		 return book_ID;
	 }
	 public void setBook_ID(long book_ID) {
		 this.book_ID = book_ID;
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
	 public LocalDate getBook_publish_date() {
		 return book_publish_date;
	 }
	 public void setBook_publish_date(LocalDate book_publish_date) {
		 this.book_publish_date = book_publish_date;
	 }
	 public String getBook_isbn_13() {
		 return book_isbn_13;
	 }
	 public void setBook_isbn_13(String book_isbn_13) {
		 this.book_isbn_13 = book_isbn_13;
	 }
	 public String getBook_notes() {
		 return book_notes;
	 }
	 public void setBook_notes(String book_notes) {
		 this.book_notes = book_notes;
	 }
	 public String getBook_read_comments() {
		 return book_read_comments;
	 }
	 public void setBook_read_comments(String book_read_comments) {
		 this.book_read_comments = book_read_comments;
	 }
	 public long getBook_owned_format_ID() {
		 return book_owned_format_ID;
	 }
	 public void setBook_owned_format_ID(long book_owned_format_ID) {
		 this.book_owned_format_ID = book_owned_format_ID;
	 }
	 public LocalDate getBook_read_date() {
		 return book_read_date;
	 }
	 public void setBook_read_date(LocalDate book_read_date) {
		 this.book_read_date = book_read_date;
	 }
	 public String getBook_read_rating() {
		 return book_read_rating;
	 }
	 public void setBook_read_rating(String book_read_rating) {
		 this.book_read_rating = book_read_rating;
	 }
	 public long getBook_read_source_ID() {
		 return book_read_source_ID;
	 }
	 public void setBook_read_source_ID(long book_read_source_ID) {
		 this.book_read_source_ID = book_read_source_ID;
	 }
	 public long getBook_read_format_ID() {
		 return book_read_format_ID;
	 }
	 public void setBook_read_format_ID(long book_read_format_ID) {
		 this.book_read_format_ID = book_read_format_ID;
	 }
	 public long getCreate_user_ID() {
		 return create_user_ID;
	 }
	 public void setCreate_user_ID(long create_user_ID) {
		 this.create_user_ID = create_user_ID;
	 }
	 public Timestamp getCreate_datetime() {
		 return create_datetime;
	 }
	 public void setCreate_datetime(Timestamp create_datetime) {
		 this.create_datetime = create_datetime;
	 }
	 public Timestamp getUpdate_datetime() {
		 return update_datetime;
	 }
	 public void setUpdate_datetime(Timestamp update_datetime) {
		 this.update_datetime = update_datetime;
	 }
}