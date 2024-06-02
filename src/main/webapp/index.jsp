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
<title>Open Book</title>
</head>
<body>

<%
   HttpSession chech = request.getSession(false);
   if(chech != null && chech.getAttribute("notUser")!= null) {
	   chech.invalidate();    
   } else {
	   response.sendRedirect("loginServlet");
   }

%>


 <header>
 <div class="logoname">
        <img src="photo/logo.png" alt="Company Logo">
        <h1>Open Book</h1>
    </div>    
        <nav>
            <ul>
                <li><a href="index.html">Home</a></li>
                <li><a href="about.html">About</a></li>
                <li><a href="login.jsp">Account</a></li>
                <li><a href="contact.html">Contact</a></li>
            </ul>
        </nav>
    </header>
<div class="main">
    <div class="card">
    <div class="description">
        <h3>Welcome to Open Book</h3>
        <p>your premier social storytelling platform. Whether you're an avid reader, a passionate writer, or someone who loves sharing life's moments, Open Book is the place for you. Connect with a vibrant community, discover inspiring stories, and share your unique journey with the world.</p>
        <p>If you already have an account, log in to continue your adventure. For new users, sign up today and start creating your story with Open Book. Thank you for joining us!</p>
    </div>
    <div class="button">
        <a href="login.jsp">Login</a>
        
    </div>
    <div class="button">
        
        <a href="register.jsp">Sign Up</a>
    </div>
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
</html>