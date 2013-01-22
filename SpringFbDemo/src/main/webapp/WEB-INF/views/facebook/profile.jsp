<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	
<head >
   
	<title>Profile</title>
</head>

<br>
<br>

<body align=center>
	<table colspan=2 align=center border=1>
	     <th colspan=2>
	         Profile Details
	     </th>
	     <tr>
	         <td> <b> Pic </b></td>
	          <td align=center>
	          <c:url var="imgurl" value="http://graph.facebook.com/${profileInfo.id}/picture" />
	          <img src="${imgurl}" alt="anotherimage" />
	        
	         </td>
	     </tr>
	     <tr>
	        <td> <b> Url </b></td>
	        <td>${profileLink}</td>
	     </tr>
	      <tr>
	        <td> <b> id </b></td>
	        <td>${profileInfo.id}</td>
	     </tr>
	      <tr>
	        <td> <b> Name </b></td>
	        <td>${profileInfo.name}</td>
	     </tr>
	
	</table>
</body>
</html>