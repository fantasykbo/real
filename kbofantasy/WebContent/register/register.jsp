<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
	<head>
		<meta name="viewport" content="width=device-width, initial-scale=1"
			http-equiv="Content-Type" content="text/html" charset="EUC-KR">
		<script type="text/javascript"
			src="http://cdnjs.cloudflare.com/ajax/libs/jquery/2.0.3/jquery.min.js"></script>
		<script type="text/javascript"
			src="http://netdna.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>
		<link
			href="http://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.3.0/css/font-awesome.min.css"
			rel="stylesheet" type="text/css">
		<link href="../common/css/register.css" rel="stylesheet" type="text/css">
		<style type="text/css">
		h1 {
			color: black;
		}
		</style>
		<script type="text/javascript">
			var xhr;
			function runAjax() {
				xhr = new XMLHttpRequest();
				xhr.onreadystatechange = emailcheck;
				//id = document.getElementById("id");
				xhr.open("POST", "/kbofantasy/emailcheck.do", true);
				xhr.setRequestHeader("Content-type",
						"application/x-www-form-urlencoded");
				xhr.send("email=" + myform.email.value);
			}
		
			function emailcheck() {
				console.log("���°�:" + xhr.readyState);
				if (xhr.readyState == 4 && xhr.status == 200) {
					//checkVal�� ���� ��� -Ajax��� ����� �Ѿ���� response�����͸� �����ϰ� ���
					//mydiv.innerHTML = xhr.responseText
						document.getElementById("checkVal").innerHTML = xhr.responseText;
					
				}
			}
		</script>
	</head>
	<body>
		<div class="container">
			<div class="row">
				<div class="col-md-12">
					<div class="page-header text-info">
						<h1>
							ȸ������
						</h1>
					</div>
					<form class="form-horizontal" role="form"
						action="/kbofantasy/register.do" method="post" name="myform">
						<div class="form-group">
							<div class="col-sm-offset-2 col-sm-2">
								<label for="inputEmail3" class="control-label">Name</label>
							</div>
							<div class="col-sm-6">
								<input type="text" class="form-control" id="inputEmail3"
									placeholder="Name" name="name" required>
							</div>
						</div>
	
						<div class="form-group">
							<div class="col-sm-offset-2 col-sm-2">
								<label for="inputEmail3" class="control-label">Email</label>
							</div>
							<div class="col-sm-6">
								<input type="email" class="form-control" id="email"
									placeholder="Email" name="email" required onkeyup="runAjax()">
	
							</div>
							<span id="checkVal"> </span>
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
							<button type="submit" class="btn btn-default action">Register</button>
							</div>
						</div>
					</form>
				</div>
			</div>
		</div>
</body>
</html>

