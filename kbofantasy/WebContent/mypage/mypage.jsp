<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<meta name="viewport" content="width=device-width, initial-scale=1">
<script type="text/javascript"
	src="http://cdnjs.cloudflare.com/ajax/libs/jquery/2.0.3/jquery.min.js"></script>
<script type="text/javascript"
	src="http://netdna.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>
<link
	href="http://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.3.0/css/font-awesome.min.css"
	rel="stylesheet" type="text/css">
<link href="../common/css/mypage.css" rel="stylesheet" type="text/css">
<title>마이 페이지</title>
<script type="text/javascript">
var windowSizeArray=["width=400,height=400",
                     "width=300,height=300,scrollbars=yes"];
$(document).ready(function(){
	$('.newWindow').click(function(event){
		var url=$(this).attr("href");
		var windowName="popUp"; //$(this).attr("name");
		var windowSize=windowSizeArray[$(this).attr("rel")];
		
		window.open(url,windowName,windowSize);
		
		event.preventDefault();
		
	});
});
                     

</script>
</head>
<body>

	<div class="section">
		<div class="container">
			<div class="row">
				<div class="col-md-12 text-center">
					<h1 contenteditable="true">MY-PAGE</h1>
				</div>
			</div>
		</div>
	</div>
	<div class="cover">
		<div class="cover-image"
			style="background-image: url('../images/mypage.PNG')"></div>
	</div>
	<div class="section">
		<div class="container">
			<div class="row">
				<div class="col-md-6">
					<a href="/kbofantasy/mypage/changepass.jsp" rel="0" class="newWindow"><img src="/kbofantasy/images/pass.PNG" ></a>
					<h2>비밀번호 변경</h2>
					<p>
						Lorem ipsum dolor sit amet, consectetur adipisici elit, <br>sed
						eiusmod tempor incidunt ut labore et dolore magna aliqua. <br>Ut
						enim ad minim veniam, quis nostrud
					</p>
				</div>
				<div class="col-md-6">
					<a href="/kbofantasy/mypage/memberleave.jsp" rel="1" class="newWindow"><img src="/kbofantasy/images/dropout.png"></a>
					<h2>회원 탈퇴</h2>
					<p>
						Lorem ipsum dolor sit amet, consectetur adipisici elit, <br>sed
						eiusmod tempor incidunt ut labore et dolore magna aliqua. <br>Ut
						enim ad minim veniam, quis nostrud
					</p>
				</div>
			</div>
		</div>
	</div>


</body>
</html>