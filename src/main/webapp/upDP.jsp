<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page import="jakarta.servlet.http.HttpSession" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" href="css/login.css">
<link rel="shortcut icon" href="photo/logo.png" type="image/x-icon" />
<title>Open Book(Update)</title>
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

		String username = (String) request.getParameter("username");
%>

 <div class="logoname">
        <img src="photo/logo.png" alt="Company Logo">
        <h1>Open Book</h1>
    </div>    
        <nav>
            <ul>
                <li><a href="javascript:window.history.go(-2);">Home</a></li>
                <li><a href="about.html">About</a></li>
                <li><a href="javascript:window.history.back();">Account</a></li>
                <li><a href="contact.html">Contact</a></li>
            </ul>
        </nav>
    </header>
<div class="main">
    <div class="card">
<%=username%>
       <form action="DPServlet" method="post" enctype="multipart/form-data">
        <input type="hidden" name="username" value="<%=username%>">
        <label>Profile Photo: </label> 
        <input type="file" name="photo" size="50" accept="image/*" required="required" />
            <button type="submit" class="submit">Update</button> 
        </form>
        
 <%-- Display error message if update fails --%>
        <% String error = request.getParameter("error");
            if (error != null && error.equals("1")) { %>
                <p style="color: red;">Update is Failed</p>
                 <p style="color: red;">(Wrong DataType!)</p>
        <% } 
            if (error != null && error.equals("2")) { %>
                <p style="color: red;">Update is Failed</p>
                 <p style="color: red;">(Wrong DataType!!)</p>
        <% } %>
        <div class="back"> <a href="javascript:window.history.back();"><img src="photo/back.png" alt="back"></a> </div>
      </div>
</div>

  <footer class="footer">
        <p>&copy; 2024 Open Book.All rights reserved.</p>
        <p>Contact us at: <a href="mailto:support@openbook.com">rokiroy2207@gmail.com</a></p>
        <p>Support at: <a href="mailto:support@openbook.com">Open Book Support</a></p>
        <p>Open Book : <a href="mailto:support@openbook.com">OpenBook</a></p>
        <p>24X7 service: <a href="mailto:support@openbook.com">support@openbook.com</a></p>
        <p>Email at: <a href="mailto:support@openbook.com">openbook@gmail.com</a></p>
    </footer>
</body>
</html>>