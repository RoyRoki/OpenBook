package com.openbook.servlet;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.print.attribute.standard.MediaSize;

import com.openbook.Dao.*;
/**
 * Servlet implementation class MediaServlet
 */
public class MediaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private static userDao userdao = new userDaoImp();   
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MediaServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter("username");
		try {
             
            List<media> users = userdao.getPhotos();
          
            request.setAttribute("users", users);
            RequestDispatcher rd = request.getRequestDispatcher("media.jsp");
            rd.forward(request, response);
            
		} catch (Exception e) {
			HttpSession session = request.getSession();
            session.setAttribute("username", username);
            
            response.sendRedirect("mypostsServlet");
		}
	}


}
