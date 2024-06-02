package com.openbook.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;

import java.io.IOException;
import java.io.InputStream;

import com.openbook.Dao.*;

@MultipartConfig(maxFileSize = 16177215)
public class PostPhotoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private static userDao userdao = new userDaoImp();   
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PostPhotoServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name = request.getParameter("username");
		Long user_id = userdao.getId(name);
		InputStream inputStream = null;
		
		Part filePart = request.getPart("photo");
		
        if(filePart != null) {
        	String contentType = filePart.getContentType();
        	
        	if (contentType != null && contentType.startsWith("image/")) {
        		String fileName = filePart.getSubmittedFileName();
                String fileExtension = fileName.substring(fileName.lastIndexOf(".") + 1).toLowerCase();
                
                if (fileExtension.equals("jpg") || fileExtension.equals("jpeg") || fileExtension.equals("png") || fileExtension.equals("gif")) {
                    inputStream = filePart.getInputStream();
                } else {
                response.sendRedirect("textpost.jsp?failed=2");
                return;
            }
		}
      }
        Long ph_id = userdao.addPhoto(user_id, inputStream);
        if(ph_id != null) {
        	HttpSession session = request.getSession();
            session.setAttribute("username", name);
            response.sendRedirect("mypostsServlet");
        }
	}

}
