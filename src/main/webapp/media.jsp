<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="jakarta.servlet.http.HttpSession" %>
<%@ page import="java.util.List" %>
<%@ page import="com.openbook.Dao.media" %>
<%@ page import="com.openbook.util.TimeAgoUtil" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Open Book</title>
    <link rel="stylesheet" type="text/css" href="css/style.css">
    <link rel="stylesheet" type="text/css" href="css/login.css">
    <link rel="shortcut icon" href="photo/logo.png" type="image/x-icon" />
</head>
<body>

	<header>
		<%
		//prevent baqck button after logout
		Cookie[] cks = request.getCookies();
		for(Cookie ck:cks) {
			if("admin".equals(ck.getName())) {
				if(!"true".equals(ck.getValue())){
					response.sendRedirect("login.jsp");
				}
			}
		}

		String name = (String) request.getParameter("username");
		%>
		<div class="logoname">
			<img src="photo/logo.png" alt="Company Logo">
			<h1>Open Book</h1>
		</div>
		<nav>
			<ul>
				<li><a href="javascript:window.history.back();">Home</a></li>

				<li><a href="about.html">About</a></li>
				<li>
					<form id="myForm" action="accountServlet" method="get">
						<input type="hidden" name="username" value="<%=name%>">
					</form> <a href="#"
					onclick="event.preventDefault(); document.getElementById('myForm').submit();">Account</a>
				</li>
				<li><a href="contact.html">Contact</a></li>
			</ul>
		</nav>
	</header>


	<h1>Latest Media</h1>
<% 
    List<media> users = (List<media>) request.getAttribute("users");
    if (users != null) {
    	
 %><div class="posts-container">
 <%
        for (media user : users) {
        	String createdAtStr = user.getCreatedAt();      	 
            String timeAgo = TimeAgoUtil.timeAgo(TimeAgoUtil.parseDateTime(createdAtStr));
            
%>     <div class="post-card">
                <div class="post-header">           
                <span class="post-username"> <%= user.getName() %></span>
                </div>
                <img src="data:image/jpeg;base64,<%= java.util.Base64.getEncoder().encodeToString(user.getPhoto()) %>" alt="User Photo" width="80%" />
                <div class="post-footer">
                    <span class="post-created-at"><%= timeAgo %></span>
                </div>
            </div>
<%
        }
 %>
 </div>
<%
    } else {
%>
    <p>No users found.</p>
<%
    }
%>

</body>
</html>