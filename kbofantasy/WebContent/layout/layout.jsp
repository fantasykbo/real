<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
	</head>
	<body>
		<div>
			<jsp:include page="/header.jsp"></jsp:include>
		</div>
		<div>
			<jsp:include page="${pathurl}"></jsp:include>
		</div>
		<div>
			<jsp:include page="/footer.jsp"></jsp:include>
		</div>
	</body>
</html>