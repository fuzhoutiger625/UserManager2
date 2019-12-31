package com.hsm.controller;
import com.hsm.controller.DownloadServlet;
import java.io.FileInputStream;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet({"/DownloadServlet"})
public class DownloadServlet extends HttpServlet {
  private static final long serialVersionUID = 1L;
  
  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    System.out.println("11111111111111111");
    response.setHeader("Content-Disposition", "attachment;filename=web.xml");
    String path = getServletContext().getRealPath("/WEB-INF/web.xml");
    FileInputStream fis = new FileInputStream(path);
    byte[] buff = new byte[1024];
    int len = 0;
    ServletOutputStream servletOutputStream = response.getOutputStream();
    while ((len = fis.read(buff)) > 0)
      servletOutputStream.write(buff, 0, len); 
  }
  
  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    doGet(request, response);
  }
}
