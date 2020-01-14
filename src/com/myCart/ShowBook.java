package com.myCart;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

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
			    response.getWriter().append("<h1>��ӭ����</h1>");
			    
//			    response.getWriter().append("java��<a href='/UserManager2/BuyBookCl?id=1&name=java&price=1.1'>�������</a><br/>");
//			    response.getWriter().append("oracle��<a href='/UserManager2/BuyBookCl?id=2&name=oracle&price=2.3'>�������</a><br/>");
//			    response.getWriter().append("c��<a href='/UserManager2/BuyBookCl?id=3&name=c&price=4.6'>�������</a><br/>");
			    
		HashMap<String, Book> mydb = DB.getDB();
		Iterator iterator = mydb.keySet().iterator();
		request.getSession();
		while(iterator.hasNext()) {
			String key = (String) iterator.next();
			Book book = mydb.get(key);
			String url = response.encodeURL("/UserManager2/BuyBookCl?id="+book.getId());
			response.getWriter().append(book.getName() + "<a href='"+url+"'>�������</a><br/>");
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
