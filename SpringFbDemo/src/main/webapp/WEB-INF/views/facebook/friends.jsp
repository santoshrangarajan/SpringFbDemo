<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">


<head th:fragment="header">  
	<title>Friends</title>
</head>

<body>
	<table style="border: 1px solid; width: 50%; text-align:center">
	<thead style="background:#d3dce3">
		<tr>
			<th>Friend Id's</th>	
		</tr>
	</thead>
	<tbody style="background:#ccc">
	<c:forEach items="${friendsList}" var="friend">
			<tr>
				<td><c:out value="${friend}" /></td>
			</tr>
	</c:forEach>
		
	</tbody>
</table>
</body>
</html>