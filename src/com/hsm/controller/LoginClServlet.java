package com.hsm.controller;

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
import javax.servlet.http.HttpSession;

@WebServlet({"/LoginClServlet"})
public class LoginClServlet extends HttpServlet {
  private static final long serialVersionUID = 1L;
  
  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    response.setContentType("text/html");
    response.setCharacterEncoding("UTF-8");
    HttpSession session = request.getSession();
    String inputCheckcode = request.getParameter("checkcode");
    String checkcode = (String)session.getAttribute("checkcode");
    if( !checkcode.equals(inputCheckcode)) {
    	request.setAttribute("err", "验证码错误");
	      request.getRequestDispatcher("/LoginServlet").forward(request, response);
	      return ;
    }

    String type = request.getParameter("type");
    if ("login".equals(type)) {
	    String id = request.getParameter("id");
	    String passwd = request.getParameter("password");
	    if(null == id || null == passwd || "".equals(id) || "".equals(passwd)) {
	    	request.setAttribute("err", "请输入用户名或者密码");
		    request.getRequestDispatcher("/LoginServlet").forward(request, response);
		    return ;
	    }
	    User user = new User();
	    user.setId(Integer.parseInt(id));
	    user.setPasswd(passwd);
	    UserService us = new UserService();
	    if (us.checkUser(user)) {
	      session.setAttribute("login", user);
	      request.getRequestDispatcher("/MainFrame").forward(request, response);
	      
	    } else {
	      request.setAttribute("err", "用户名密码错误");
	      request.getRequestDispatcher("/LoginServlet").forward(request, response);
	    } 
    } else if("logout".equals(type)) {
	    session.invalidate();
	    request.getRequestDispatcher("/LoginServlet").forward(request, response);
    }
  }
  
  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    doGet(request, response);
  }
}
