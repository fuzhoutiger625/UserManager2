package com.myCart;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class BuyBookCl
 */
@WebServlet("/BuyBookCl")
public class BuyBookCl extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BuyBookCl() {
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
	    String bookname = request.getParameter("name");
	    String id = request.getParameter("id");
	    Book book = new Book();
	    book.setId(id);
	    book.setName(bookname);
	    HashMap<String, Book> mybooks;
	    HttpSession session = request.getSession();
	    mybooks = (HashMap) session.getAttribute("mybooks");
	    if(null == mybooks) {
	    	mybooks = new HashMap<String, Book>();
	    	book.setNum(1);
	    	mybooks.put(id,book);

		    session.setAttribute("mybooks", mybooks);
	    } else {
	    	if(mybooks.containsKey(id)) {
	    		Book b = mybooks.get(id);
	    		b.setNum(b.getNum() + 1);
	    		mybooks.put(id,b);
	    	}else {
	    		book.setNum(1);
	    		mybooks.put(id,book);

	    	}
	    	session.setAttribute("mybooks", mybooks);
	    }
	    
	    
	    
	    request.getRequestDispatcher("/ShowMyCart").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
