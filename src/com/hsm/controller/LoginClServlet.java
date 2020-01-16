package com.hsm.controller;

import com.hsm.domain.User;
import com.hsm.service.UserService;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
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
  
  public void init(){
	  System.out.println("================init===================");
	  String filePath = this.getServletContext().getRealPath("record.txt");
	  System.out.println("------" + filePath);
	  
	  try {
		FileReader fileReader = new FileReader(filePath);
		BufferedReader bufferdReader = new BufferedReader(fileReader);
		String sloginTimes = bufferdReader.readLine();
		System.out.println("============" + sloginTimes);
		int loginTimes = Integer.parseInt(sloginTimes);
		this.getServletContext().setAttribute("loginTimes", loginTimes);
		bufferdReader.close();
		fileReader.close();
	} catch (FileNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
  }
  
  public void destroy() {
	  System.out.println("================destroy===================");
	  String filePath = this.getServletContext().getRealPath("record.txt");
	  
	  try {
		FileWriter fileWriter = new FileWriter(filePath);
		BufferedWriter bufferdWriter = new BufferedWriter(fileWriter);
		int loginTimes = (int) this.getServletContext().getAttribute("loginTimes");
		System.out.println("================loginTimes===================" + loginTimes);
		bufferdWriter.write(loginTimes+"");
		bufferdWriter.flush();
		bufferdWriter.close();
		fileWriter.close();
	} catch (FileNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
  }
  
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
	      Object object = this.getServletContext().getAttribute("loginTimes");
	      System.out.println("objectobjectobject" + object);
	      int loginTimes = 0;
	      if(object != null) {
	    	  loginTimes = (int)object;  
	      }
	      this.getServletContext().setAttribute("loginTimes", loginTimes + 1);
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
