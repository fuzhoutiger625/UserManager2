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
		  request.setAttribute("err", "�������û�������");
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
    response.getWriter().append("<img src='imgs/tomcat.gif' />��ӭ�û� "+id+" ��¼��" + welcome + "<a href=/UserManager2/LoginServlet>�������µ�¼</a><br>");
    //response.getWriter().append("<h3><br>");
    response.getWriter().append("<a href=/UserManager2/ManageUsers>�û�����</a><br>");
    response.getWriter().append("<a href=/UserManager2/UserClServlet?type=gotoAddUser>����û�</a><br>");
    response.getWriter().append("<a href=/UserManager2/UserClServlet?type=gotoSearchUser>�����û�</a><br>");
    response.getWriter().append("<a href=/UserManager2/LoginClServlet?type=logout>�˳�ϵͳ</a><br>");
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
		    		wel = "���ϴε�¼��ʱ����:" + cookie.getValue();
		    		b = true;
		    	}
		    }
	    }
	    if(!b) {
	    	wel = "��������һ�ε�¼";
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
