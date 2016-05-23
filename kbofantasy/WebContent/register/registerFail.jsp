<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
	<head>
	<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
	<title>Insert title here</title>
	</head>
	<body>
		<script type="text/javascript">
		alert("아이디와 비밀번호를 확인하세요");
		</script>
		<div>
			<jsp:include page="/header.jsp"></jsp:include>
		</div>
		<div>
			<jsp:include page="/register/register.jsp"></jsp:include>
		</div>
		<div>
			<jsp:include page="/footer.jsp"></jsp:include>
		</div>
	</body>
</html>