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
  
  public ArrayList searchUserByPage(String searchKey,String mode, int pageNow, int pageSize) {
	    ArrayList<User> al = new ArrayList<>();
	    String sql;
	    String[] parameters = new String[1];
	    if("0".equals(mode)) {
	    	sql = "select * from users where username like ? limit " + ((pageNow - 1) * pageSize) + "," + pageSize;
	    	parameters[0]= new String("%"+searchKey+"%");
	    }else {
	    	sql = "select * from users where username = ? limit " + ((pageNow - 1) * pageSize) + "," + pageSize;
	    	parameters[0]= searchKey;
	    }
	    ResultSet rs = SqlHelper.executeQuery(sql, parameters);
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
  
  public int getPageCountBySearch(String searchKey,String mode,int pageSize) {
	    int pageCount = 0;
	    String sql;
	    String[] parameters = new String[1];
	    if("0".equals(mode)) {
	    	sql = "select count(1) from users where username like ?";
	    	parameters[0]= new String("%"+searchKey+"%");
	    }else {
	    	sql = "select count(1) from users where username = ?";
	    	parameters[0]= searchKey;
	    }
	    ResultSet rs = SqlHelper.executeQuery(sql, parameters);
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
  
  public User getUserById(String id) {
	  String sql = "select * from users where id=?";
	  String parameters[]= {id};
	  User user = new User();
	  ResultSet rs = SqlHelper.executeQuery(sql, parameters);;
	  try {
		  if(rs.next()) {
			  user.setId(rs.getInt(1));
			  user.setUsername(rs.getString(2));
			  user.setEmail(rs.getString(3));
			  user.setGrade(rs.getInt(4));
			  user.setPasswd(rs.getString(5));
			  
		  }
	  }catch(SQLException e) {
		  e.printStackTrace();
	  }finally {
		  SqlHelper.close(rs, SqlHelper.getPs(), SqlHelper.getCt());
	  }
	  return user;
  }
  
  public boolean updateUser(User user) {
	  boolean b = true;
	  
	  String sql = "update users set username=?,email=?,grade=?,passwd=? where id=?";
	  String parameters[] = {user.getUsername(), user.getEmail(), user.getGrade()+"", user.getPasswd(), user.getId()+""};
	  try {
		  SqlHelper.executeUpdate(sql, parameters);
	  } catch(Exception e) {
		  b = false;
		  e.printStackTrace();
	  }
	  return b;
  }
  
  public boolean addUser(User user) {
	  boolean b = true;
	  
	  String sql = "insert into users values(?,?,?,?,?)";
	  String parameters[] = {user.getId()+"", user.getUsername(), user.getEmail(), user.getGrade()+"", user.getPasswd()};
	  try {
		  SqlHelper.executeUpdate(sql, parameters);
	  } catch(Exception e) {
		  b = false;
		  e.printStackTrace();
	  }
	  return b;
  }
}
