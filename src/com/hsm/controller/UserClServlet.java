package com.hsm.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hsm.domain.User;
import com.hsm.service.UserService;

/**
 * Servlet implementation class DelClServlet
 */
@WebServlet("/UserClServlet")
public class UserClServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserClServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		
	    response.setCharacterEncoding("UTF-8");
	    UserService userService = new UserService();
	    String type=request.getParameter("type");
	    if("del".equals(type)) {
	    	String id = request.getParameter("id");
		    if(userService.delUser(id)) {
		    	request.setAttribute("info", "删除成功");
		    	request.getRequestDispatcher("/Ok").forward(request, response);
		    } else {
		    	request.setAttribute("info", "删除失败");
		    	request.getRequestDispatcher("/Err").forward(request, response);
		    }
	    } else if("gotoUpdateView".equals(type)) {
	    	String id = request.getParameter("id");
	    	User user = userService.getUserById(id);
	    	request.setAttribute("userInfo", user);
	    	request.getRequestDispatcher("UpdateUserView").forward(request, response);
	    } else if("update".equals(type)) {
	    	String id = request.getParameter("id");
	    	String username = request.getParameter("username");
	    	String email = request.getParameter("email");
	    	String grade = request.getParameter("grade");
	    	String passwd = request.getParameter("passwd");
	    	User user = new User();
	    	user.setId(Integer.parseInt(id));
	    	user.setUsername(username);
	    	user.setEmail(email);
	    	user.setGrade(Integer.parseInt(grade));
	    	user.setPasswd(passwd);
	    	if(userService.updateUser(user)) {
	    		request.setAttribute("info", "修改成功");
	    		request.getRequestDispatcher("/Ok").forward(request, response);
	    	} else {
	    		request.setAttribute("info", "修改失败");
	    		request.getRequestDispatcher("/Err").forward(request, response);
	    	}
	    	response.getWriter().append("修改用户");
	    } else if("gotoAddUser".equals(type)) {
	    	request.getRequestDispatcher("/AddUserView").forward(request, response);
	    } else if("add".equals(type)) {
	    	String id = request.getParameter("id");
	    	String username = request.getParameter("username");
	    	String email = request.getParameter("email");
	    	String grade = request.getParameter("grade");
	    	String passwd = request.getParameter("passwd");
	    	User user = new User();
	    	user.setId(Integer.parseInt(id));
	    	user.setUsername(username);
	    	user.setEmail(email);
	    	user.setGrade(Integer.parseInt(grade));
	    	user.setPasswd(passwd);
	    	if(userService.addUser(user)) {
	    		request.setAttribute("info", "添加成功");
	    		request.getRequestDispatcher("/Ok").forward(request, response);
	    	} else {
	    		request.setAttribute("info", "添加失败");
	    		request.getRequestDispatcher("/Err").forward(request, response);
	    	}
	    	response.getWriter().append("添加用户");
	    } else if ("gotoSearchUser".equals(type)) {
	    	request.getRequestDispatcher("/SearchUserView").forward(request, response);
	    }
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
