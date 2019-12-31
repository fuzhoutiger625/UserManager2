package com.hsp.util;
import com.hsp.util.SqlHelper;
import java.io.IOException;
import java.io.InputStream;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class SqlHelper {
  private static Connection ct = null;
  
  private static PreparedStatement ps = null;
  
  private static ResultSet rs = null;
  
  private static String url = "";
  
  private static String username = "";
  
  private static String driver = "";
  
  private static String passwd = "";
  
  private static CallableStatement cs = null;
  
  public static CallableStatement getCs() {
    return cs;
  }
  
  private static Properties pp = null;
  
  private static InputStream fis = null;
  
  static {
    try {
      pp = new Properties();
      fis = SqlHelper.class.getClassLoader().getResourceAsStream("dbinfo.properties");
      pp.load(fis);
      url = pp.getProperty("url");
      username = pp.getProperty("username");
      driver = pp.getProperty("driver");
      passwd = pp.getProperty("passwd");
      Class.forName(driver);
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      try {
        fis.close();
      } catch (IOException e) {
        e.printStackTrace();
      } 
      fis = null;
    } 
  }
  
  public static Connection getConnection() {
    try {
      ct = DriverManager.getConnection(url, username, passwd);
    } catch (Exception e) {
      e.printStackTrace();
    } 
    return ct;
  }
  
  public static CallableStatement callPro1(String sql, String[] parameters) {
    try {
      ct = getConnection();
      cs = ct.prepareCall(sql);
      if (parameters != null)
        for (int i = 0; i < parameters.length; i++)
          cs.setObject(i + 1, parameters[i]);  
      cs.execute();
    } catch (Exception e) {
      e.printStackTrace();
      throw new RuntimeException(e.getMessage());
    } finally {
      close(rs, cs, ct);
    } 
    return cs;
  }
  
  public static CallableStatement callPro2(String sql, String[] inparameters, Integer[] outparameters) {
    try {
      ct = getConnection();
      cs = ct.prepareCall(sql);
      if (inparameters != null)
        for (int i = 0; i < inparameters.length; i++)
          cs.setObject(i + 1, inparameters[i]);  
      if (outparameters != null)
        for (int i = 0; i < outparameters.length; i++)
          cs.registerOutParameter(inparameters.length + 1 + i, outparameters[i].intValue());  
      cs.execute();
    } catch (Exception e) {
      e.printStackTrace();
      throw new RuntimeException(e.getMessage());
    } 
    return cs;
  }
  
  public static ResultSet executeQuery(String sql, String[] parameters) {
    try {
      ct = getConnection();
      ps = ct.prepareStatement(sql);
      if (parameters != null)
        for (int i = 0; i < parameters.length; i++)
          ps.setString(i + 1, parameters[i]);  
      rs = ps.executeQuery();
    } catch (Exception e) {
      e.printStackTrace();
      throw new RuntimeException(e.getMessage());
    } 
    return rs;
  }
  
  public static Connection getCt() {
    return ct;
  }
  
  public static PreparedStatement getPs() {
    return ps;
  }
  
  public static ResultSet getRs() {
    return rs;
  }
  
  public static void executeUpdate2(String[] sql, String[][] parameters) {
    try {
      ct = getConnection();
      ct.setAutoCommit(false);
      for (int i = 0; i < sql.length; i++) {
        if (parameters[i] != null) {
          ps = ct.prepareStatement(sql[i]);
          for (int j = 0; j < (parameters[i]).length; j++)
            ps.setString(j + 1, parameters[i][j]); 
          ps.executeUpdate();
        } 
      } 
      ct.commit();
    } catch (Exception e) {
      e.printStackTrace();
      try {
        ct.rollback();
      } catch (SQLException e1) {
        e1.printStackTrace();
      } 
      throw new RuntimeException(e.getMessage());
    } finally {
      close(rs, ps, ct);
    } 
  }
  
  public static void executeUpdate(String sql, String[] parameters) {
    try {
      ct = getConnection();
      ps = ct.prepareStatement(sql);
      if (parameters != null)
        for (int i = 0; i < parameters.length; i++)
          ps.setString(i + 1, parameters[i]);  
      ps.executeUpdate();
    } catch (Exception e) {
      e.printStackTrace();
      throw new RuntimeException(e.getMessage());
    } finally {
      close(rs, ps, ct);
    } 
  }
  
  public static void close(ResultSet rs, Statement ps, Connection ct) {
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
}
