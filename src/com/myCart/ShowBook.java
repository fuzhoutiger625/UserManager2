package com.myCart;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ShowBook
 */
@WebServlet("/ShowBook")
public class ShowBook extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ShowBook() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
				response.setContentType("text/html");
			    response.setCharacterEncoding("UTF-8");
			    response.getWriter().append("<h1>»¶Ó­¹ºÂò</h1>");
			    
			    response.getWriter().append("javaÊé<a href='/UserManager2/BuyBookCl?id=1&name=java'>µã»÷¹ºÂò</a><br/>");
			    response.getWriter().append("oracleÊé<a href='/UserManager2/BuyBookCl?id=2&name=oracle'>µã»÷¹ºÂò</a><br/>");
			    response.getWriter().append("cÊé<a href='/UserManager2/BuyBookCl?id=3&name=c'>µã»÷¹ºÂò</a><br/>");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
