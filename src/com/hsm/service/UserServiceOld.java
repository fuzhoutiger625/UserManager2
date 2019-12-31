package com.hsm.service;
import com.hsm.domain.User;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserServiceOld {
  public boolean checkUser(User user) {
    Connection ct = null;
    ResultSet rs = null;
    PreparedStatement ps = null;
    boolean b = false;
    try {
      Class.forName("com.mysql.jdbc.Driver");
      ct = DriverManager.getConnection("jdbc:mysql://localhost:3306/usermanager?useSSL=false&serverTimezone=UTC", "root", "");
      ps = ct.prepareStatement("select * from users where id = ? and passwd=?");
      ps.setObject(1, Integer.valueOf(user.getId()));
      ps.setObject(2, user.getPasswd());
      rs = ps.executeQuery();
      if (rs.next()) {
        b = true;
      } else {
        b = false;
      } 
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      if (rs != null) {
        try {
          rs.close();
        } catch (SQLException e) {
          e.printStackTrace();
        } 
        rs = null;
      } 
      if (ps != null) {
        try {
          ps.close();
        } catch (SQLException e) {
          e.printStackTrace();
        } 
        ps = null;
      } 
      if (ct != null) {
        try {
          ct.close();
        } catch (SQLException e) {
          e.printStackTrace();
        } 
        ct = null;
      } 
    } 
    return b;
  }
}
