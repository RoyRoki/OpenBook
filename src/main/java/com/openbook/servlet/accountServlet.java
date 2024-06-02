package com.openbook.servlet;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import com.openbook.Dao.userDao;
import com.openbook.Dao.userDaoImp;

/**
 * Servlet implementation class accountServlet
 */
public class accountServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static userDao userDao = new userDaoImp();    
    /**
     * @see HttpServlet#HttpServlet()
     */
    public accountServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String username = request.getParameter("username");
		String[] str = userDao.getuser(username);
		byte[] photo=null;
		
		Long ph_id = userDao.getPh_Id(username);
		if (ph_id != -1) {
			photo = userDao.getPhotoById(ph_id);
		}

		if(str!=null) {
			request.setAttribute("accountDetails", str);
			request.setAttribute("photo", photo);
	        RequestDispatcher dispatcher = request.getRequestDispatcher("account.jsp");
	        
	        dispatcher.forward(request, response);
		}
	}

}
