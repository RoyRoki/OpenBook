package com.openbook.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

import com.openbook.Dao.userDao;
import com.openbook.Dao.userDaoImp;

/**
 * Servlet implementation class loginServlet
 */
public class loginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static userDao userDao = new userDaoImp();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public loginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	
    	
        Cookie[] cookies = request.getCookies();
        String id = null;
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if ("name".equals(cookie.getName())) {
                    id = cookie.getValue();
                    
                    break;
                }
            }
        }
        
        if (id != null ) {
        	String name = userDao.getName(Long.parseLong(id));
        	if(userDao.isPresent(name)) {
        	HttpSession session = request.getSession();
            session.setAttribute("username", name);
            response.sendRedirect("mypostsServlet");
            }
        } else {
        	
        	HttpSession chech = request.getSession();     	
        	if(chech.getAttribute("notUser") == null) {
        		chech.setAttribute("notUser", true);
        	}
            response.sendRedirect("index.jsp");
        }
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 String username = request.getParameter("username");
	     String password = request.getParameter("password");
	     
	     
	     if (userDao.isValidUser(username, password)) {
	    	 
	    	    Cookie ck = new Cookie("name", Long.toString(userDao.getId(username)) );
	    	    Cookie ok = new Cookie("admin", "true");
	    	    
		        response.addCookie(ck);
		        response.addCookie(ok);
		        
		        ck.setMaxAge(60*60*24);// one day
		        ok.setMaxAge(60*60*24*28);//one month
		        
	            HttpSession session = request.getSession();
	            session.setAttribute("username", username);
	            
	            response.sendRedirect("mypostsServlet");
	           
	        } else {
	            response.sendRedirect("login.jsp?error=1");
	        	
	        }
	}

}
