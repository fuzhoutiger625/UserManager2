package com.hsp.view;
import com.hsm.domain.User;
import com.hsm.service.UserService;
import com.hsp.view.ManageUsers;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet({"/ManageUsers"})
public class ManageUsers extends HttpServlet {
  private static final long serialVersionUID = 1L;
  
  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    response.setContentType("text/html");
    response.setCharacterEncoding("UTF-8");
    response.getWriter().append("<script type='text/javascript' language='javascript'>");
    response.getWriter().append("function gotoPageNow() {var pageNow=document.getElementById('pageNow');alert(pageNow.value);window.open('/UserManager2/ManageUsers?pageNow=' + pageNow.value,'_self');}");
    response.getWriter().append("</script>");
    response.getWriter().append("<img src='imgs/tomcat.gif' /><a href=/UserManager2/LoginServlet>返回重新登录</a>");
    response.getWriter().append("<a href=/UserManager2/ManageUsers></a>");
    Connection ct = null;
    ResultSet rs = null;
    PreparedStatement ps = null;
    int pageNow = 1;
    int pageSize = 3;
    int pageCount = 1;
    int rowCount = 1;
    try {
      UserService userService = new UserService();
      pageCount = userService.getPageCount(pageSize);
      System.out.println("=================" + pageCount);
      String sPageNow = request.getParameter("pageNow");
      if(sPageNow != null) {
    	  pageNow = Integer.parseInt(sPageNow);
      }
      
      ArrayList<User> al = userService.getUserByPage(pageNow, pageSize);
      response.getWriter().append("<table border=1px bordercolor=green cessspacing=0 width=500>");
      response.getWriter().append("<tr><th>id</th><th>name</th><th>email</th><th>grade</th><th>删除用户</th><th>修改用户</th><tr>");
      for (User u : al) {
        int id = u.getId();
        String username = u.getUsername();
        String email = u.getEmail();
        String grade = u.getPasswd();
        response.getWriter().append("<tr><td>" + id + 
            "</td><td>" + username + 
            "</td><td>" + email + 
            "</td><td>" + grade + 
            "</td><td><a href='***' >删除用户</a></td><td><a href='***' >修改用户</a></td></tr>");
      } 
      response.getWriter().append("</table>");
      if (pageNow != 1)
        response.getWriter().append("<a href='/UserManager2/ManageUsers?pageNow=" + (pageNow - 1) + "'>上一页</a>"); 
      int pageStart = (pageNow - 1 > 1) ? (pageNow - 1) : 1;
      int pageEnd = (pageNow + 1 > pageCount) ? pageCount : (pageNow + 1);
      for (int i = pageStart; i <= pageCount; i++)
        response.getWriter().append("<a href='/UserManager2/ManageUsers?pageNow=" + i + "'><" + i + "></a> "); 
      if (pageNow != pageCount)
        response.getWriter().append("<a href='/UserManager2/ManageUsers?pageNow=" + (pageNow + 1) + "'>下一页</a>"); 
      response.getWriter().append("当前页：" + pageNow + "，总页数：" + pageCount + "</br>");
      response.getWriter().append("<input type='text' name='pageNow'/><input type='button' onClick='gotoPageNow()' value='跳转' />");
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
