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
 * Servlet implementation class upPassServlet
 */
public class upPassServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static userDao userDao = new userDaoImp();     
    /**
     * @see HttpServlet#HttpServlet()
     */
    public upPassServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String newpass = request.getParameter("newpass");
		String username = request.getParameter("username");
		String oldpass = request.getParameter("oldpass");
		
		if(userDao.upPassWord(username,oldpass,newpass)) {
			HttpSession session = request.getSession();
            session.setAttribute("username", username);
            
            response.sendRedirect("mypostsServlet");
		} else {
			response.sendRedirect("updatepass.jsp?error=1&username="+username);
		}
	}

}
