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
    <script>
        function refreshPage() {
           window.location.reload(true); // Reload the page from the server
       }
        // Check if the page is loaded from the cache or not
        window.onload = function () {
            if (performance.navigation.type === 2) {
                // If page is loaded from the cache (via back button), refresh the page
                refreshPage();
            }
        };
        
        function openPopup() {
            var overlay = document.querySelector('.popup-overlay');
            var popup = document.querySelector('.popup-content');
            overlay.style.display = 'flex';
            setTimeout(function() {
                popup.classList.add('show');
                popup.style.animation = 'slideInFromTopLeft 0.3s forwards';
            }, 10);
        }

        function closePopup() {
        	refreshPage();
            var overlay = document.querySelector('.popup-overlay');
            var popup = document.querySelector('.popup-content');
            popup.classList.remove('show');
            popup.style.animation = '';
            setTimeout(function() {
                overlay.style.display = 'none';
            }, 300);
        }
        <!-- JavaScript to handle the "Read More" button click event -->
            function toggleReadMore(postId) {
                const content = document.getElementById('content-' + postId);
                const readMoreBtn = content.nextElementSibling.querySelector('.read-more');
                if (content.classList.contains('expanded')) {
                    content.classList.remove('expanded');
                    readMoreBtn.textContent = 'Read More';
                } else {
                    content.classList.add('expanded');
                    readMoreBtn.textContent = 'Read Less';
                }
            }
    </script>
</head>
<body>
	<header>

		<%
		//logout==admin=false
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
				<li><a href="">Home</a></li>
				<li><a href="textpost.jsp?username=<%=name%>">New Post</a></li>
				<li>
					<form id="myForm" action="accountServlet" method="get">
						<input type="hidden" name="username" value="<%=name%>">
					</form> <a href="#"
					onclick="event.preventDefault(); document.getElementById('myForm').submit();">Account</a>
				</li>
				<li><a href="#" onclick="openPopup(event)">Logout</a></li>
			</ul>
		</nav>
	</header>
	<div class="popup-overlay" >
		<div class="popup-content">
			<h2>Logout Confirmation</h2>
			<p>Are you sure you want to logout?</p>
			<button class="close-btn" onclick="closePopup()">Cancel</button>
			<a href="LogoutServlet" class="close-btn">Logout</a>
		</div>
	</div>
	<%
    // Check if registration was successful
    String registrationMessage = request.getParameter("reg"); 
    if (registrationMessage != null && registrationMessage.equals("success")) { 
%>
    <h1>Welcome</h1>
<%
    }
%>

<%
    // Retrieve the session object
    HttpSession session5 = request.getSession(false);

    // Check if the session is not null and the username attribute is set
    if (session5 != null && session5.getAttribute("username") != null) {
        // Get the username from the session
        String username = (String) session5.getAttribute("username");
%>

    <br>
    <form action="allpostsServlet" class="glassy-form">
        <input type="hidden" name="username" value="<%=username%>">
        <button type="submit" class="live"></button>
        <h1>Text Posts</h1>
    </form>
 
    <form action="MediaServlet" class="glassy-form">
    <input type="hidden" name="username" value="<%=username%>">
        <button type="submit" class="live2"></button>
        <h1>Media</h1>
    </form>
<%}
    // Check if a post was successfully added
    String postedMessage = request.getParameter("posted");
    if (postedMessage != null && postedMessage.equals("1")) { 
%><p>New post Add</p>
<%} %>
    <hr><h1>Your Posts </h1><hr>
    <br>
   <%
    // Retrieve the posts attribute from the request
    List<post> posts = (List<post>) request.getAttribute("posts");
    
    if (posts != null && !posts.isEmpty()) {
%>
    <div class="posts-container">
        <%
            // Iterate over the list of posts and display each one in a card
            for (post post : posts) {
            	 String createdAtStr = post.getCreatedAt();
            	 
                 String timeAgo = TimeAgoUtil.timeAgo(TimeAgoUtil.parseDateTime(createdAtStr));
        %>
            <div class="post-card">
                <div class="post-header">
                    <!--span class="post-id">ID:post.getId()%></span-->
                    <span class="post-username"><%= post.getUsername() %></span>
                </div>
                <div class="post-content" id="content-<%= post.getId() %>">
                    <%= post.getContent() %>
                </div>
                <div class="post-footer">
                    <span class="post-created-at"><%= timeAgo %></span>
                    <!-- Add a "Read More" button -->
                    <span class="read-more" onclick="toggleReadMore('<%= post.getId() %>')">Read More</span>
                </div>
            </div>
        <%
            } 
        %>
    </div>
<%
    } else { 
%>
    <a href="textpost.jsp?username=<%= name %>" ><img src="https://cdn-icons-gif.flaticon.com/16061/16061147.gif" alt="Company Logo" class="live"></a>
<%}%>
<br>
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
