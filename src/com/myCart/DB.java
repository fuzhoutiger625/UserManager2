package com.myCart;

import java.util.ArrayList;
import java.util.HashMap;

final public class DB {
	
	private static HashMap hm = null;
	
	static {
		hm = new HashMap<String,Book>();
		Book book1 = new Book();
		book1.setId("1");
		book1.setName("java");
		book1.setPrice(1.1f);
		
		Book book2 = new Book();
		book2.setId("2");
		book2.setName("oracle");
		book2.setPrice(2.1f);
		
		Book book3 = new Book();
		book3.setId("3");
		book3.setName("linux");
		book3.setPrice(5.5f);
		
		Book book4 = new Book();
		book4.setId("4");
		book4.setName("windows");
		book4.setPrice(4.5f);
		
		hm.put(book1.getId(),book1);
		hm.put(book2.getId(),book2);
		hm.put(book3.getId(),book3);
		hm.put(book4.getId(),book4);
	}
	
	public static HashMap getDB() {
		return hm;
	}
	
	public static Book getBookById(String id) {
		return (Book) hm.get(id);
	}

}
