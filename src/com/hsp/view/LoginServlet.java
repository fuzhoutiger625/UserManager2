package com.hsp.view;
import com.hsp.view.LoginServlet;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet({"/LoginServlet"})
public class LoginServlet extends HttpServlet {
  private static final long serialVersionUID = 1L;
  
  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    response.setContentType("text/html");
    response.setCharacterEncoding("UTF-8");
    String id = getId(request, response);

    response.getWriter().append("<img src='imgs/tomcat.gif' /><hr/>");
    response.getWriter().append("<h1>�û���¼</h1>");
    response.getWriter().append("<form action='/UserManager2/LoginClServlet' mechod='post'>");
    response.getWriter().append("<input type='hidden'  name='type' value='login'/><br>");
    response.getWriter().append("�û�����<input type='text' name='id' value='"+id+"'/><br>");
    response.getWriter().append("���룺<input type='password' name='password'/><br>");
    response.getWriter().append("��֤�룺<input type='text' name='checkcode'/><img src='/UserManager2/CreateCode' /><br>");
    response.getWriter().append("<input type='checkbox' value='keep' name='iskeepinfo'/>�ڴ˵����ϱ����û���<br>");
    response.getWriter().append("<input type='submit' value='��¼'/><br>");
    response.getWriter().append("</form>");
    String errInfo = (String)request.getAttribute("err");
    if (errInfo != null)
      response.getWriter().append("<font color='red' > " + errInfo + "</font>"); 
  }
  
  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    doGet(request, response);
  }
  
  String getId(HttpServletRequest request, HttpServletResponse response) {
	  String id = "";
	  boolean b = false;
	    Cookie [] cookies = request.getCookies();
	    if(cookies != null) {
		    for(int i=0;i<cookies.length;i++) {
		    	Cookie cookie = cookies[i];
		    	if("id".equals(cookie.getName())){
		    		id = cookie.getValue();
		    		b = true;
		    		
		    	}
		    }
	    }
	    return id;
  }
}
