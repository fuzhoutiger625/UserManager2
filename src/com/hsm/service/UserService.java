package com.hsm.service;
import com.hsm.domain.User;
import com.hsp.util.SqlHelper;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class UserService {
  public boolean checkUser(User user) {
    boolean b = false;
    String sql = "select * from users where id = ? and passwd=?";
    String[] parameters = { (new StringBuilder(String.valueOf(user.getId()))).toString(), user.getPasswd() };
    ResultSet rs = SqlHelper.executeQuery(sql, parameters);
    try {
      if (rs.next())
        b = true; 
    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      SqlHelper.close(rs, SqlHelper.getPs(), SqlHelper.getCt());
    } 
    return b;
  }
  
  public ArrayList getUserByPage(int pageNow, int pageSize) {
    ArrayList<User> al = new ArrayList<>();
    String sql = "select * from users limit " + ((pageNow - 1) * pageSize) + "," + pageSize;
    ResultSet rs = SqlHelper.executeQuery(sql, null);
    try {
      while (rs.next()) {
        User user = new User();
        user.setId(rs.getInt(1));
        user.setUsername(rs.getString(2));
        user.setEmail(rs.getString(3));
        user.setGrade(rs.getInt(4));
        user.setPasswd(rs.getString(5));
        al.add(user);
      } 
    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      SqlHelper.close(rs, SqlHelper.getPs(), SqlHelper.getCt());
    } 
    return al;
  }
  
  public int getPageCount(int pageSize) {
    int pageCount = 0;
    String sql = "select count(1) from users";
    ResultSet rs = SqlHelper.executeQuery(sql, null);
    try {
      if (rs.next())
        pageCount = (rs.getInt(1) - 1) / pageSize + 1; 
    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      SqlHelper.close(rs, SqlHelper.getPs(), SqlHelper.getCt());
    } 
    return pageCount;
  }
  
  
  public boolean delUser(String id) {
	  String sql = "delete from users where id=?";
	  String parameters[]= {id};
	  boolean b = true;
	  try {
		  SqlHelper.executeUpdate(sql, parameters);
	  }catch(RuntimeException e) {
		  b = false;
	  }
	  return b;
  }
}
