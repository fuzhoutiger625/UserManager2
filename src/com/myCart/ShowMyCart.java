package com.myCart;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class ShowMyCart
 */
@WebServlet("/ShowMyCart")
public class ShowMyCart extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ShowMyCart() {
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
	    response.getWriter().append("<a href='/UserManager2/ShowBook' >返回继续购买</a><br/>");
	    HttpSession session = request.getSession();
	    HashMap<String, Book> mybooks = (HashMap<String, Book>) session.getAttribute("mybooks");
	    if(mybooks != null) {
	    	response.getWriter().append("您的购物车有：<br/>");
	    	Iterator iterator = mybooks.keySet().iterator();
		    while(iterator.hasNext()) {
		    	String key = (String) iterator.next();
		    	Book book = mybooks.get(key);
		    	response.getWriter().append(book.getName() + " " + book.getNum() + "<br/>");
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
