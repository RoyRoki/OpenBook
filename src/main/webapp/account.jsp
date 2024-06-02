<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="jakarta.servlet.http.HttpSession" %>
<%@ page import="java.util.List" %>
<%@ page import="com.openbook.Dao.post" %>
<%@ page import="com.openbook.util.TimeAgoUtil" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Open Book (Home)</title>
<link rel="shortcut icon" href="photo/logo.png" type="image/x-icon" />
<link rel="stylesheet" type="text/css" href="css/style.css">
<link rel="stylesheet" type="text/css" href="css/login.css">
<link rel="stylesheet" type="text/css" href="css/popupbox.css">
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
     
        String name = (String) request.getParameter("username");
        byte[] photo =(byte[]) request.getAttribute("photo");
%>
 <%if(photo!=null) { %>
    	<div class="logoname">
            <img src="data:image/jpeg;base64,<%= java.util.Base64.getEncoder().encodeToString(photo) %>" alt="Profile Photo" >           
            <h1><%=name %></h1>
            
        </div>
        <%} else { %>
        <div class="logoname">
            <img src="photo/logo.png" alt="Profile Photo">           
            <h1><%=name %></h1>
            
        </div>
        <%} %>
        <nav>
            <ul>
                <li><a href="javascript:window.history.back();">Home</a></li>
                <li><a href="about.html">About</a></li>
                <li><a href="">Account</a></li>
                <li><a href="contact.html">Contact</a></li>
            </ul>
        </nav>
    </header>
<div class="main">
    <div class="card">
    <%//get the accountDetails from the atribute
    String[] str = (String[])request.getAttribute("accountDetails");
    
    
    if(str!=null) {
    	%>
    	<h3>User Id: <%=str[0] %></h3>
    	<h3>User Name: <%=str[1] %></h3>
    	<h3>Email Id: <%=str[2] %></h3>
    	<h3>DP Id:<%=str[3] %></h3>
    	<%
    } else {
    	%>
    	 <h3>No Details In Your Account</h3>
    	 <h3>Please contact with OpenBook !</h3>
    	<% } %>
    	<br>
    	<%if(photo!=null) { %>
    	<div class="logoname">
            <img src="data:image/jpeg;base64,<%= java.util.Base64.getEncoder().encodeToString(photo) %>" alt="Profile Photo" >           
            <a href="upDP.jsp?username=<%=name%>">Edit</a>
            
        </div>
        <%} else { %>
        <div class="logoname">
            <img src="photo/logo.png" alt="Profile Photo">           
            <a href="upDP.jsp?username=<%=name%>">Edit</a>
            
        </div>
        <%} %>
    	<hr>
    	<br>
<div class="button-container">
     <div class="button">
        <a href="updatename.jsp?username=<%=name%>">Update&nbspName</a>
        
    </div>
    <div class="button">
        
        <a href="updateEmail.jsp?username=<%=name%>">Update&nbspEmail</a>
    </div>
        <div class="button">
        
        <a href="updatepass.jsp?username=<%=name%>">Update&nbspPassWord</a>
     </div>
    </div>
     <%-- Display  message if updated --%>
        <% String mess = request.getParameter("updated");
            if (mess != null) { %>
                <p style="color: green;"><%=mess %> is Updated</p>
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
</html>