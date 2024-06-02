package com.openbook.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;

import com.openbook.Dao.*;
/**
 * Servlet implementation class upnameServlet
 */
public class upnameServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private static userDao userDao = new userDaoImp();   
    /**
     * @see HttpServlet#HttpServlet()
     */
    public upnameServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String newname = request.getParameter("newname");
		String oldname = request.getParameter("oldname");
		String pass = request.getParameter("pass");
		
		int ok = userDao.upname(oldname,pass,newname);
		if(ok==0) {
			HttpSession session = request.getSession();
            session.setAttribute("username", newname);
            
            response.sendRedirect("mypostsServlet");
		} else if(ok==1) {
			response.sendRedirect("updatename.jsp?error=1&username="+oldname);
		} else {
			response.sendRedirect("updatename.jsp?error=2&username="+oldname);
		}
	}

}
