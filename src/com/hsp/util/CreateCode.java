package com.hsp.util;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class CreateCode
 */
@WebServlet("/CreateCode")
public class CreateCode extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreateCode() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setDateHeader("Expires", -1);
		response.setHeader("Cache-control", "no-cache");
		response.setHeader("Pragme", "no-cache");
		
		response.setHeader("Content-Type", "image/jpeg");
		
		BufferedImage image = new BufferedImage(80,30,BufferedImage.TYPE_INT_RGB);
		
		Graphics g = image.getGraphics();
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, 80, 30);
		
		g.setColor(Color.BLACK);
		g.setFont(new Font(null, Font.BOLD,20));
		
		String num = makeNum();
		request.getSession().setAttribute("checkcode", num);
		g.drawString(num, 0, 20);
		ImageIO.write(image, "jpg", response.getOutputStream());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	public String makeNum() {
		Random r = new Random();
		String num = r.nextInt(9999) + "";
		StringBuffer sb = new StringBuffer();
		for(int i =0;i<4-num.length();i++) {
			sb.append("0");
		}
		num = sb.toString() + num;
		return num;
	}

}
