package com.openbook.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

import com.openbook.Dao.userDao;
import com.openbook.Dao.userDaoImp;

/**
 * Servlet implementation class upemailServlet
 */
public class upemailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	   private static userDao userDao = new userDaoImp();    
    /**
     * @see HttpServlet#HttpServlet()
     */
    public upemailServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String newemail = request.getParameter("newemail");
		String username = request.getParameter("username");
		String pass = request.getParameter("pass");
		
		
		int ok = userDao.upemail(username,pass,newemail);
		if(ok==0) {
			HttpSession session = request.getSession();
            session.setAttribute("username", username);
            
            response.sendRedirect("mypostsServlet");
		} else if(ok==1) {
			response.sendRedirect("updateEmail.jsp?error=1&username="+username);
		} else {
			response.sendRedirect("updateEmail.jsp?error=2&username="+username);
		}
	}

}
