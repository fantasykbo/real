<%@page import="member.dto.KBOLoginDTO"%>
<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
</head>
<body>
<% KBOLoginDTO user= (KBOLoginDTO)session.getAttribute("login"); %>
	<h4><%= user.getName()%>님 로그인 성공</h4> 

</body>
</html>