package com.openbook.servlet;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

import com.openbook.Dao.userDao;
import com.openbook.Dao.userDaoImp;

/**
 * Servlet implementation class GetMyPhoto
 */
public class GetMyPhoto extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static userDao userdao = new userDaoImp();   
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetMyPhoto() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter("username");
		Long ph_id = userdao.getPh_Id(username);
		byte[] photo = userdao.getPhotoById(ph_id);
		if(photo!=null) {
			request.setAttribute("dp", photo);
            RequestDispatcher rd = request.getRequestDispatcher("account.jsp");
            rd.forward(request, response);
		} else {
			HttpSession session = request.getSession();
            session.setAttribute("username", username);
            
            response.sendRedirect("mypostsServlet");
		}
	}



}
