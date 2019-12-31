package com.hsm.domain;

import com.hsm.domain.User;

public class User {
  private String username;
  
  private String passwd;
  
  private int id;
  
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
