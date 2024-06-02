package com.openbook.servlet;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.openbook.Dao.userDao;
import com.openbook.Dao.post;
import com.openbook.Dao.userDaoImp;
import com.openbook.quary.myQuary;
import com.openbook.util.dataBaseConn;

/**
 * Servlet implementation class mypostsServlet
 */



public class mypostsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static userDao userDao = new userDaoImp();   
    /**
     * @see HttpServlet#HttpServlet()
     */
    public mypostsServlet() {
        super();
        // TODO Auto-generated constructor stub
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Cookie[] cks = request.getCookies();
		for(Cookie ck:cks) {
			if("admin".equals(ck.getName())) {
				if(!"true".equals(ck.getValue())){
					response.sendRedirect("login.jsp");
				}
			}
		}
		
		HttpSession session = request.getSession(false);
		if (session != null && session.getAttribute("username") != null) {
			String userName = (String) session.getAttribute("username");
		List<post> posts =  fetchPostsData(userName);
        byte[] photo=null;
		
		Long ph_id = userDao.getPh_Id(userName);
		if (ph_id != -1) {
			photo = userDao.getPhotoById(ph_id);
		}
		
		request.setAttribute("posts", posts);
		request.setAttribute("photo", photo);
		
        RequestDispatcher dispatcher = request.getRequestDispatcher("home.jsp");
        
        dispatcher.forward(request, response);
		} else { response.sendRedirect("login.jsp");}
	}
	
	
	
	
	private List<post> fetchPostsData(String userName) {
	    long userId = userDao.getId(userName);
	    List<post> posts = new ArrayList<>();
	    
	    try (Connection conn = dataBaseConn.getConn();
	         PreparedStatement pstmt = conn.prepareStatement(myQuary.retrieveposts)) {
	        
	        pstmt.setLong(1, userId);
	        try (ResultSet rs = pstmt.executeQuery()) {
	            while (rs.next()) {
	                post post = new post();
	                post.setId(rs.getLong("id"));
	                

	                post.setUsername(rs.getString("username"));
	                post.setContent(rs.getString("content"));
	               
	                post.setCreatedAt(rs.getString("created_at"));
	                
	                // Add the post to the list
	                posts.add(post);
	            }
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    return posts;
	}


}
