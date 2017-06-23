package com.edu.westga.cs6920.wiseowl.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="booksource")
public class BookSource implements Serializable
{
	 /**
	  * 
	  */
	 private static final long serialVersionUID = 2L;
	 @Id
	 private long booksource_ID;
	 private String booksource_code;
	 private String booksource_name;
	 private String booksource_desc;
	 
	 public BookSource()
	 { }
	
	 public BookSource(String booksource_ID)
	 {
		 this.booksource_ID=Long.parseLong(booksource_ID);
	 }
	 
	 public long getBooksource_ID() {
		 return booksource_ID;
	 }
	 public void setBooksource_ID(long booksource_ID) {
		 this.booksource_ID = booksource_ID;
	 }
	 public String getBooksource_code() {
		 return booksource_code;
	 }
	 public void setBooksource_code(String booksource_code) {
		 this.booksource_code = booksource_code;
	 }
	 public String getBooksource_name() {
		 return booksource_name;
	 }
	 public void setBooksource_name(String booksource_name) {
		 this.booksource_name = booksource_name;
	 }
	 public String getBooksource_desc() {
		 return booksource_desc;
	 }
	 public void setBooksource_desc(String booksource_desc) {
		 this.booksource_desc = booksource_desc;
	 }
}