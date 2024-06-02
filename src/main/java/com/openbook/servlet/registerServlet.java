package com.openbook.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;


import com.openbook.Dao.*;
/**
 *  implementation class RegisterServlet
 */
public class registerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private userDao userD = new userDaoImp(); 
    /**
     * @see HttpServlet#HttpServlet()
     */
    public registerServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		    String username = request.getParameter("username");
	        String password = request.getParameter("password");
	        String email = request.getParameter("email");
	        
	        if(userD.isPresent(username)) {
	        	response.sendRedirect("register.jsp?error=3");
	        	return;
	        }
	        long id = userD.getId(username);
	        Cookie ck = new Cookie("name", Long.toString(id) );
	        Cookie ok = new Cookie("admin", "true");
	        response.addCookie(ck);
	        response.addCookie(ok);
	        
	        ck.setMaxAge(60*60*24);// one day
	        ok.setMaxAge(60*60*24*28);//one month

	        user user = new user();
	        user.setUsername(username);
	        user.setEmail(email);
	        user.setPassword(password);

			//UserDao userDao = new UserDaoImpl();
	        if (userD.addUser(user)) {
	        	HttpSession session = request.getSession();
	            session.setAttribute("username", username);
	            response.sendRedirect("home.jsp?reg=success");
	        } else {
	            response.sendRedirect("register.jsp?error=1");
	        }
	}

}

