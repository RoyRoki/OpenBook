package com.openbook.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import com.openbook.Dao.userDao;
import com.openbook.Dao.userDaoImp;

/**
 * Servlet implementation class textpostServlet
 */
public class textpostServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static userDao userDao = new userDaoImp();   
    /**
     * @see HttpServlet#HttpServlet()
     */
    public textpostServlet() {
        super();
        // TODO Auto-generated constructor stub
    }


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String textpost = request.getParameter("textpost");
		String username = request.getParameter("username");
		
		long userId = userDao.getId(username);
		//String str = Long.toString(userId);
		boolean posted = userDao.insertTextPost(userId, textpost);
	       if(posted) {
	           response.sendRedirect("mypostsServlet");    	   
	       } else {
	    	   response.sendRedirect("textpost.jsp?failed=2");
	       }
	}

}
