<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<title>Insert title here</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<script type="text/javascript"
	src="http://cdnjs.cloudflare.com/ajax/libs/jquery/2.0.3/jquery.min.js"></script>
<script type="text/javascript"
	src="http://netdna.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>
<link
	href="http://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.3.0/css/font-awesome.min.css"
	rel="stylesheet" type="text/css">
<link href="../common/css/login.css" rel="stylesheet" type="text/css">
</head>

<body>
	<script type="text/javascript">
		$(document)
				.ready(
						function() {
							$("#register")
									.on(
											"click",
											function() {
												location.href = "/kbofantasy/view.do?pathurl=/register/register.jsp";
											});
						});
		var xhr;
		function runAjax() {
			xhr = new XMLHttpRequest();
			xhr.onreadystatechange = login;
			//id = document.getElementById("id");
			xhr.open("POST", "/kbofantasy/emailcheck.do", true);
			xhr.setRequestHeader("Content-type",
					"application/x-www-form-urlencoded");
			xhr.send("email=" + myform.email.value);
		}

		function emailcheck() {
			console.log("상태값:" + xhr.readyState);
			if (xhr.readyState == 4 && xhr.status == 200) {
				//checkVal에 값을 출력 -Ajax통신 결과로 넘어오는 response데이터를 적절하게 출력
				//mydiv.innerHTML = xhr.responseText
				document.getElementById("checkVal").innerHTML = xhr.responseText;
			}
		}
	</script>

	<!-- view부분 시작 -->
	<div class="section">
		<div class="container">
			<div class="row">
				<div class="col-md-12">
					<div class="page-header text-info">
						<h1>
							로그인
						</h1>
					</div>
					<form class="form-horizontal" action="/kbofantasy/login.do"
						method="post">
						<div class="form-group">
							<div class="col-sm-offset-2 col-sm-2">
								<label for="inputEmail3" class="control-label text-right">Email</label>
							</div>
							<div class="col-sm-6">
								<input type="email" class="form-control" id="inputEmail3"
									placeholder="Email" name="email">
							</div>
						</div>
						<div class="form-group">
								<div class="col-sm-offset-2 col-sm-2">
								<label for="inputPassword3" class="control-label">Password</label>
							</div>
							<div class="col-sm-6">
								<input type="password" class="form-control" id="inputPassword3"
									placeholder="Password" name="password">
							</div>
						</div>
						<div class="form-group">
							<div class="col-sm-offset-4 col-sm-6 col-md-2">
								<button type="submit" class="btn btn-default action">Sign
									in</button>
								<button type="button" id="register"
									class="btn btn-default action">register</button>
							</div>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>
</body>
</html>

