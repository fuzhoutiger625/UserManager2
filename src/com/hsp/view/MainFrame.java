package com.hsp.view;
import com.hsp.view.MainFrame;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet({"/MainFrame"})
public class MainFrame extends HttpServlet {
  private static final long serialVersionUID = 1L;
  
  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    response.setContentType("text/html");
    response.setCharacterEncoding("UTF-8");
    response.getWriter().append("<img src='imgs/tomcat.gif' /> <a href=/UserManager2/LoginServlet>返回重新登录</a><br>");
    //response.getWriter().append("<h3><br>");
    response.getWriter().append("<a href=/UserManager2/ManageUsers>用户管理</a><br>");
    response.getWriter().append("<a href=??></a><br>");
    response.getWriter().append("<a href=??></a><br>");
    response.getWriter().append("<a href=??></a><br>");
  }
  
  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    doGet(request, response);
  }
}
