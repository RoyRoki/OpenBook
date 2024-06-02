<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="jakarta.servlet.http.HttpSession" %>
<%@ page import="java.util.List" %>
<%@ page import="com.openbook.Dao.post" %>
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


	<h1>Latest Posts</h1>
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
    <p>No posts found.</p>
<%}%>
<!-- JavaScript to handle the "Read More" button click event -->
<script>
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

</body>
</html>