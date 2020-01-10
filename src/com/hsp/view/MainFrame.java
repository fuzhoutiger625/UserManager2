package com.hsp.view;
import com.hsm.domain.User;
import com.hsp.view.MainFrame;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet({"/MainFrame"})
public class MainFrame extends HttpServlet {
  private static final long serialVersionUID = 1L;
  
  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    
	  HttpSession session = request.getSession();
	  User user = (User)session.getAttribute("login");
	  if(null == user ) {
		  request.setAttribute("err", "请输入用户名密码");
		  request.getRequestDispatcher("/LoginServlet").forward(request, response);
		  return ;
	  }
	  response.setContentType("text/html");
    response.setCharacterEncoding("UTF-8");
    String val = request.getParameter("iskeepinfo");
    String id = request.getParameter("id");
    String password = request.getParameter("password");
    if("keep".equals(val)) {
    	Cookie cookie = new Cookie("id", id);
    	cookie.setMaxAge(7*24*60*60);
    	response.addCookie(cookie);
    }else {
    	Cookie[] cookies = request.getCookies();
    	for(Cookie cookie:cookies) {
    		if("id".equals(cookie.getName())) {
    			cookie.setMaxAge(0);
    			response.addCookie(cookie);
    		}
    	}
    }
    String welcome = GetWelcome(request, response);
    response.getWriter().append("<img src='imgs/tomcat.gif' />欢迎用户 "+id+" 登录，" + welcome + "<a href=/UserManager2/LoginServlet>返回重新登录</a><br>");
    //response.getWriter().append("<h3><br>");
    response.getWriter().append("<a href=/UserManager2/ManageUsers>用户管理</a><br>");
    response.getWriter().append("<a href=/UserManager2/UserClServlet?type=gotoAddUser>添加用户</a><br>");
    response.getWriter().append("<a href=/UserManager2/UserClServlet?type=gotoSearchUser>查找用户</a><br>");
    response.getWriter().append("<a href=/UserManager2/LoginClServlet?type=logout>退出系统</a><br>");
  }
  
  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    doGet(request, response);
  }
  
  String GetWelcome(HttpServletRequest request, HttpServletResponse response) {
	  boolean b = false;
	  String wel = "";
	    Cookie [] cookies = request.getCookies();
	    if(cookies != null) {
		    for(int i=0;i<cookies.length;i++) {
		    	Cookie cookie = cookies[i];
		    	if("lasttime".equals(cookie.getName())){
		    		wel = "您上次登录的时间是:" + cookie.getValue();
		    		b = true;
		    	}
		    }
	    }
	    if(!b) {
	    	wel = "这是您第一次登录";
	    }
	    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd===HH:mm:ss");
	    String nowTime = simpleDateFormat.format(new Date());
	    Cookie cookie = new Cookie("lasttime", nowTime);
	    cookie.setMaxAge(7 * 24 * 60 * 60);
	    //C:\Users\Administrator\AppData\Local\Microsoft\Windows\Temporary Internet Files
	    response.addCookie(cookie);
	    return wel;
  }
}
