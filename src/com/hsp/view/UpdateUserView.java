package com.hsp.view;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hsm.domain.User;

/**
 * Servlet implementation class UpdateUserView
 */
@WebServlet("/UpdateUserView")
public class UpdateUserView extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateUserView() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html");
	    response.setCharacterEncoding("UTF-8");
	    User user = (User)request.getAttribute("userInfo");
	    response.getWriter().append("<h1>修改用户</h1>");
	    response.getWriter().append("<form action='/UserManager2/UserClServlet?type=update' method='post'>");
	    response.getWriter().append("<table border=1px bordercolor=green cessspacing=0 width=250>");
	    response.getWriter().append("<tr><td>id</td><td><input type='text' name='id' readonly value='"+user.getId()+"'/></td></tr>");
	    response.getWriter().append("<tr><td>用户名</td><td><input type='text' name='username' value='"+user.getUsername()+"'/></td></tr>");
	    response.getWriter().append("<tr><td>email</td><td><input type='text' name='email' value='"+user.getEmail()+"'/></td></tr>");
	    response.getWriter().append("<tr><td>级别</td><td><input type='text' name='grade' value='"+user.getGrade()+"'/></td></tr>");
	    response.getWriter().append("<tr><td>密码</td><td><input type='text' name='passwd' value='"+user.getPasswd()+"'/></td></tr>");
	    response.getWriter().append("<tr><td><input type='submit' value='修改用户' /></td><td><input type='reset' value='重新填写' /></td></tr>");
	    response.getWriter().append("</table>");
	    response.getWriter().append("</form>");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
