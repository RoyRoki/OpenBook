package com.openbook.servlet;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.openbook.Dao.post;
import com.openbook.Dao.userDao;
import com.openbook.util.dataBaseConn;
import com.openbook.Dao.userDaoImp;
import com.openbook.quary.myQuary;

/**
 * Servlet implementation class allpostsServlet
 */
public class allpostsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
     private static userDao userDao = new userDaoImp();  
    /**
     * @see HttpServlet#HttpServlet()
     */
    public allpostsServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Cookie[] cks = request.getCookies();
		for(Cookie ck:cks) {
			if("admin".equals(ck.getName())) {
				if(!"true".equals(ck.getValue())){
					response.sendRedirect("login.jsp");
				}
			}
		}
		// TODO Auto-generated method stub
		String username = request.getParameter("username");
         List<post> posts =  fetchPostsData(username);
		
		request.setAttribute("posts", posts);
        RequestDispatcher dispatcher = request.getRequestDispatcher("public.jsp");
        
        dispatcher.forward(request, response);
		
	}
	
	
	
	
	private List<post> fetchPostsData(String userName) {
	    
	    List<post> posts = new ArrayList<>();

	    
	    try (Connection conn = dataBaseConn.getConn();
	         PreparedStatement pstmt = conn.prepareStatement(myQuary.allUsers)) {
	        
	        try (ResultSet rs = pstmt.executeQuery()) {
	            while (rs.next()) {
	                post post = new post();
	                post.setId(rs.getLong("id"));
	                
                    String name = userDao.getName(rs.getLong("user_id"));
	                post.setUsername(name);
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
