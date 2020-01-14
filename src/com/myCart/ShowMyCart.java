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
	    request.getSession();
	    String url = response.encodeURL("/UserManager2/ShowBook");
	    response.getWriter().append("<a href='"+url+"'>返回继续购买</a><br/>");
	    HttpSession session = request.getSession();
	    HashMap<String, Book> mybooks = (HashMap<String, Book>) session.getAttribute("mybooks");
	    float total = 0;
	    if(mybooks != null) {
	    	response.getWriter().append("您的购物车有：<br/>");
	    	Iterator iterator = mybooks.keySet().iterator();
		    while(iterator.hasNext()) {
		    	String key = (String) iterator.next();
		    	Book book = mybooks.get(key);
		    	float t1 = book.getPrice() * book.getNum();
		    	response.getWriter().append(book.getName() + " " + book.getNum() +",总价格为:"+ t1 + "<br/>");
		    	total += t1;
		    }
		    response.getWriter().append("一共需要付款:" + total);
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
