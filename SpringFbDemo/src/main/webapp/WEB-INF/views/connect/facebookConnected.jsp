<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">


	
<head th:fragment="header">
    <meta charset="utf-8" />
	<title>Connected</title>
</head>

<body>
	<div>
		<div>
			
			<div>
				<p>
					You are connected to facebook. Click below to disconnect
				</p>
				
				<form id="disconnect" action="/connect/facebook/"  method="post">
					<button type="submit" text="disconnect">Disconnect from Social</button>	
					<input type="hidden" name="_method" value="delete" />
				</form>
			</div>
		</div>
	</div>
</body>
</html>