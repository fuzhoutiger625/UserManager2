package com.hsm.controller;
import com.hsm.controller.LoginClServlet;
import com.hsm.domain.User;
import com.hsm.service.UserService;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet({"/LoginClServlet"})
public class LoginClServlet extends HttpServlet {
  private static final long serialVersionUID = 1L;
  
  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    response.setContentType("text/html");
    response.setCharacterEncoding("UTF-8");
    String id = request.getParameter("id");
    String passwd = request.getParameter("password");
    User user = new User();
    user.setId(Integer.parseInt(id));
    user.setPasswd(passwd);
    UserService us = new UserService();
    System.out.println("11111111");
    if (us.checkUser(user)) {
    	System.out.println("222222222");
      request.getRequestDispatcher("/MainFrame").forward(request, response);
    } else {
    	System.out.println("3333333");
      request.setAttribute("err", "”√ªß√˚√‹¬Î¥ÌŒÛ");
      request.getRequestDispatcher("/LoginServlet").forward(request, response);
    } 
  }
  
  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    doGet(request, response);
  }
}
