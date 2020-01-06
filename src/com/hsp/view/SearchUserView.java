package com.hsp.view;

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

import com.hsm.domain.User;
import com.hsm.service.UserService;

/**
 * Servlet implementation class SearchUserView
 */
@WebServlet("/SearchUserView")
public class SearchUserView extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SearchUserView() {
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
	    String searchKey= request.getParameter("searchKey");
	      String mode = request.getParameter("mode");
	    if(searchKey == null ) {
	    	  searchKey = "";
	      }
	    if(mode == null) {
	    	  mode = "0";
	      }
	    System.out.println("searchKey is " + searchKey + ",mode is " + mode);
	    response.getWriter().append("<img src='imgs/tomcat.gif' /><a href=/UserManager2/LoginServlet>返回重新登录</a>");
	    response.getWriter().append("<form action='/UserManager2/SearchUserView' method='post'>");
	    response.getWriter().append("<input type='text' name='searchKey' value='"+searchKey+"'/><br>");
	    response.getWriter().append("<input type='submit' value='查找'/>");
	    response.getWriter().append("<input type='radio' name='mode' value='0' checked />模糊查找<input type='radio' name='mode' value='1'/>精确查找<br/>");
	    response.getWriter().append("</form>");
	    Connection ct = null;
	    ResultSet rs = null;
	    PreparedStatement ps = null;
	    int pageNow = 1;
	    int pageSize = 3;
	    int pageCount = 1;
	    int rowCount = 1;
	    try {
	      UserService userService = new UserService();
	      pageCount = userService.getPageCountBySearch(searchKey, mode,pageSize);
	      //System.out.println("=================" + pageCount);
	      String sPageNow = request.getParameter("pageNow");
	      if(sPageNow != null) {
	    	  pageNow = Integer.parseInt(sPageNow);
	      }
	      
	      
	      
	      
	      ArrayList<User> al = userService.searchUserByPage(searchKey, mode, pageNow, pageSize);
	      response.getWriter().append("<table border=1px bordercolor=green cessspacing=0 width=500>");
	      response.getWriter().append("<tr><th>id</th><th>name</th><th>email</th><th>grade</th><tr>");
	      for (User u : al) {
	        int id = u.getId();
	        String username = u.getUsername();
	        String email = u.getEmail();
	        String grade = u.getGrade() + "";
	        response.getWriter().append("<tr><td>" + id + 
	            "</td><td>" + username + 
	            "</td><td>" + email + 
	            "</td><td>" + grade + 
	            "</td></tr>");
	      } 
	      response.getWriter().append("</table>");
	      if (pageNow != 1)
	        response.getWriter().append("<a href='/UserManager2/SearchUserView?searchKey="+searchKey+"&mode="+mode+"&pageNow=" + (pageNow - 1) + "'>上一页</a>"); 
	      int pageStart = (pageNow - 1 > 1) ? (pageNow - 1) : 1;
	      int pageEnd = (pageNow + 1 > pageCount) ? pageCount : (pageNow + 1);
	      for (int i = pageStart; i <= pageCount; i++)
	        response.getWriter().append("<a href='/UserManager2/SearchUserView?searchKey="+searchKey+"&mode="+mode+"&pageNow=" + i + "'><" + i + "></a> "); 
	      if (pageNow != pageCount)
	        response.getWriter().append("<a href='/UserManager2/SearchUserView?searchKey="+searchKey+"&mode="+mode+"&pageNow=" + (pageNow + 1) + "'>下一页</a>"); 
	      response.getWriter().append("当前页：" + pageNow + "，总页数：" + pageCount + "</br>");
	      //response.getWriter().append("<input type='text' name='pageNow'/><input type='button' onClick='gotoPageNow()' value='跳转' />");
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

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
