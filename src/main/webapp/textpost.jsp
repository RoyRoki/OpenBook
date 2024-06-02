<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page import="jakarta.servlet.http.HttpSession" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Open Book (post)</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" href="css/login.css">
<link rel="shortcut icon" href="photo/logo.png" type="image/x-icon" />
</head>
<body>
    <header>

<%  //prevent baqck button after logout
Cookie[] cks = request.getCookies();
for(Cookie ck:cks) {
	if("admin".equals(ck.getName())) {
		if(!"true".equals(ck.getValue())){
			response.sendRedirect("login.jsp");
		}
	}
}
     
    // Retrieve the session object
    HttpSession session2 = request.getSession(false);
        String name = (String) session2.getAttribute("username");
%>
 <div class="logoname">
        <img src="photo/logo.png" alt="Company Logo">
         <h1 class="myname"><%= name %></h1>
    </div>    
        <nav>
            <ul>
                <li><a href="javascript:window.history.back();">Home</a></li>
                <li><a href="textpost.jsp?username=<%= name %>" >New Post</a></li>
                <li><a href="#">Account</a></li>
                <li><a href="javascript:window.history.back();">Logout</a></li>
            </ul>
        </nav>
    </header>
    
    <div class="main">
      <div class="card">
    <form action="textpostServlet" method="post">
            <label for="text">Your post:</label>
            <textarea  class="textarea" name="textpost" placeholder="Type something here..." required></textarea><br><br>
            <input type="hidden" name="username" value="<%= session.getAttribute("username") %>">
            <button type="submit" class="submit">Text post</button>
    </form>
    
        <%-- post failed--%>
    <%String text = request.getParameter("failed");
    if(text!= null && text.equals("2")) {%>
    <h3>Try again !!</h3>
    <%}%>
    <hr>
    <form action="PostPhotoServlet" method="post" enctype="multipart/form-data">
        <input type="hidden" name="username" value="<%=name%>">
        <label>New Photo: </label> 
        <input type="file" name="photo" size="50" accept="image/*" required="required" /><br>
        <button type="submit" class="submit">Photo post</button>
    </form>
     <div class="back"> <a href="javascript:window.history.back();"><img src="photo/back.png" alt="back"></a> </div>
      </div>
</div>
</body>
</html>