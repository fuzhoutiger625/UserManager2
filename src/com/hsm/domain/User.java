package com.hsm.domain;

import com.hsm.domain.User;

public class User {
  private String username;
  
  private String passwd;
  
  private int id;
  public User() {
  }
  
  public User(int id, String username, String email, int grade, String passwd) {
	super();
	this.id = id;
	this.username = username;
	this.email = email;
	this.grade = grade;
	this.passwd = passwd;
}

private int grade;
  
  private String email;
  
  public String getPasswd() {
    return this.passwd;
  }
  
  public void setPasswd(String passwd) {
    this.passwd = passwd;
  }
  
  public int getId() {
    return this.id;
  }
  
  public void setId(int id) {
    this.id = id;
  }
  
  public int getGrade() {
    return this.grade;
  }
  
  public void setGrade(int grade) {
    this.grade = grade;
  }
  
  public String getEmail() {
    return this.email;
  }
  
  public void setEmail(String email) {
    this.email = email;
  }
  
  public String getUsername() {
    return this.username;
  }
  
  public void setUsername(String username) {
    this.username = username;
  }
}
