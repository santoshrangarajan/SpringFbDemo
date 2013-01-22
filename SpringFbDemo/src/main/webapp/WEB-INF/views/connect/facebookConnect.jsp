<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">


<head>
	<title>Connect to Fb</title>
</head>

<body>
	<div class="skin">
		<div class="content">
			<div class="connect">
				<h3>Connect to Social Site</h3>
				<div>
					<form action="/connect/facebook/"  method="POST">
						<input type="hidden" name="scope" value="publish_stream,offline_access" />
						<p>Connect to Facebook</p>
						<button type="submit" text="Connect with Facebook">Connect</button>
					</form>
				</div>
			</div>
		</div>
	</div>	
</body>
</html>