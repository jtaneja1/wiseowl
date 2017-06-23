package com.edu.westga.cs6920.wiseowl.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="BOOKFORMAT")
public class BookFormat implements Serializable
{
 /**
  * 
  */
 private static final long serialVersionUID = 1L;
 @Id
 private long bookformat_ID;
 private String bookformat_code;
 private String bookformat_name;
 private String bookformat_desc;
 
 public BookFormat()
 { }

 public BookFormat(String bookformat_ID)
 {
  this.bookformat_ID=Long.parseLong(bookformat_ID);
 }
 
 public long getBookformat_ID() {
  return bookformat_ID;
 }
 public void setBookformat_ID(long bookformat_ID) {
  this.bookformat_ID = bookformat_ID;
 }
 public String getBookformat_code() {
  return bookformat_code;
 }
 public void setBookformat_code(String bookformat_code) {
  this.bookformat_code = bookformat_code;
 }
 public String getBookformat_name() {
  return bookformat_name;
 }
 public void setBookformat_name(String bookformat_name) {
  this.bookformat_name = bookformat_name;
 }
 public String getBookformat_desc() {
  return bookformat_desc;
 }
 public void setBookformat_desc(String bookformat_desc) {
  this.bookformat_desc = bookformat_desc;
 }
 
}