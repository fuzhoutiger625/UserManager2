package com.hsp.view;
import com.hsp.view.ManageUsersOld;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet({"/ManageUsersOld"})
public class ManageUsersOld extends HttpServlet {
  private static final long serialVersionUID = 1L;
  
  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    response.setContentType("text/html");
    response.setCharacterEncoding("UTF-8");
    response.getWriter().append("<script type='text/javascript' language='javascript'>");
    response.getWriter().append("function gotoPageNow() {var pageNow=document.getElementById('pageNow');window.open('/UserManager2/ManageUsers?pageNow=' + pageNow.value,'_self');}");
    response.getWriter().append("</script>");
    response.getWriter().append("<img src='imgs/tomcat.gif' />href=/UserManager2/LoginServlet>");
    response.getWriter().append("<a href=/UserManager2/ManageUsers>");
    Connection ct = null;
    ResultSet rs = null;
    PreparedStatement ps = null;
    int pageNow = 3;
    int pageSize = 3;
    int pageCount = 1;
    int rowCount = 1;
    try {
      Class.forName("com.mysql.jdbc.Driver");
      ct = DriverManager.getConnection("jdbc:mysql://localhost:3306/usermanager?useSSL=false&serverTimezone=UTC", "root", "");
      ps = ct.prepareStatement("select count(1) from users");
      rs = ps.executeQuery();
      rs.next();
      rowCount = rs.getInt(1);
      pageCount = (rowCount - 1) / pageSize + 1;
      String spageNow = request.getParameter("pageNow");
      if (spageNow != null)
        pageNow = Integer.parseInt(spageNow); 
      ps = ct.prepareStatement("select * from users limit " + ((pageNow - 1) * pageSize) + "," + pageSize);
      rs = ps.executeQuery();
      response.getWriter().append("<table border=1px bordercolor=green cessspacing=0 width=20>");
      response.getWriter().append("<tr><th>id</th><th>");
      while (rs.next()) {
        int id = rs.getInt(1);
        String username = rs.getString(2);
        String email = rs.getString(3);
        String grade = rs.getString(4);
        response.getWriter().append("<tr><th>" + id + 
            "</th><th>" + username + 
            "</th><th>" + email + 
            "</th><th>" + grade + 
            "</th></tr>");
      } 
      response.getWriter().append("</table>");
      if (pageNow != 1)
        response.getWriter().append("<a href='/UserManager2/ManageUsers?pageNow=" + (pageNow - 1) + "'><"); 
      int pageStart = (pageNow - 1 > 1) ? (pageNow - 1) : 1;
      int pageEnd = (pageNow + 1 > pageCount) ? pageCount : (pageNow + 1);
      for (int i = pageStart; i <= pageCount; i++)
        response.getWriter().append("<a href='/UserManager2/ManageUsers?pageNow=" + i + "'><" + i + "></a> "); 
      if (pageNow != pageCount)
        response.getWriter().append("<a href='/UserManager2/ManageUsers?pageNow=" + (pageNow + 1) + "'><"); 
      response.getWriter().append("    + pageNow + " + pageCount + "</br>");
      response.getWriter().append("type='text' name='pageNow'/><input type='button' onClick='gotoPageNow()' value='/>");
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
  }
  
  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    doGet(request, response);
  }
}
