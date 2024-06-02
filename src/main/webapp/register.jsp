<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" href="css/login.css">
<link rel="shortcut icon" href="photo/logo.png" type="image/x-icon" />
<title>Open Book(Registration)</title>
</head>
<body>

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

        <form action="registerServlet" method="post">
            <label for="username">UserName:</label>
            <input type="text" id="username" name="username" required><br>
            <label for="email">Email:</label>
            <input type="text" id="username" name="email" required><br>   
            <label for="password">Password:</label>
            <input type="password" id="password" name="password" required><br>
            <button type="submit" class="submit">Register</button>
        </form>
			<%-- Display error message if user have an account Already  --%>
			<%
			String error = request.getParameter("error");
			if (error != null && error.equals("3")) {
			%>
			<p style="color: green;">You Have An Account</p>
			<p style="color: green;">Please Login</p>
			<%
			}
			if (error != null && error.equals("1")) {
			%>
			<p style="color: red;">Registration Failed</p>
			<p style="color: red;">Please Try Again!</p>
			<%
			}
			%>
			<div class="back"> <a href="index.html"><img src="photo/back.png" alt="back"></a> </div>
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