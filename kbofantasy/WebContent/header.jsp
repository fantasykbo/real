<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%@page import="member.dto.*"%>
<!DOCTYPE html>
<html>

	<head>
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<script type="text/javascript"
			src="http://cdnjs.cloudflare.com/ajax/libs/jquery/2.0.3/jquery.min.js"></script>
		<script type="text/javascript"
			src="http://netdna.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>
		<link
			href="http://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.3.0/css/font-awesome.min.css"
			rel="stylesheet" type="text/css">
		<link
			href="http://pingendo.github.io/pingendo-bootstrap/themes/default/bootstrap.css"
			rel="stylesheet" type="text/css">
		<% KBOLoginDTO user= (KBOLoginDTO)session.getAttribute("login"); %>
		
		<script type="text/javascript">
			function eventListUrl() {
				var d = new Date();
				var year = d.getFullYear() + "";
				var month = (d.getMonth() + 1) + "";
				if (month.length == 1) {
					month = "0" + month;
				}
				location.href = "/kbofantasy/record/eventList.do?year=" + year
						+ "&month=" + month + "&pathurl=/record/eventList.jsp";
			}
		</script>
	</head>
	
	<body>
		<div class="navbar navbar-default navbar-inverse navbar-static-top">
			<div class="container">
				<div class="navbar-header">
					<button type="button" class="navbar-toggle" data-toggle="collapse"
						data-target="#navbar-ex-collapse">
						<span class="sr-only">Toggle navigation</span> <span
							class="icon-bar"></span> <span class="icon-bar"></span> <span
							class="icon-bar"></span>
					</button>
					<a class="navbar-brand" href="/kbofantasy">FantasyKBO</a>
				</div>
				<div class="collapse navbar-collapse" id="navbar-ex-collapse">
					<ul class="nav navbar-nav navbar-right">
						<%
						if(user!=null){%>						
						<li class="active"><a><%=user.getName()%>(<%=user.getEmail()%>)님 환영합니다!(<%=user.getPoint()%>p)</a></li>
						<%}%>
						<%
						if(user==null){
						%>
						<li><a href="/kbofantasy/view.do?pathurl=/login/login.jsp">Login</a></li>
						<li><a href="/kbofantasy/view.do?pathurl=/register/register.jsp">Register</a></li>
						<%}else{ %>
						<li><a href="/kbofantasy/view.do?pathurl=/mypage/mypage.jsp">my page</a></li>
						<li><a href="/kbofantasy/logout.do">logout</a></li>
						<%} %>
					</ul>
				</div>
			</div>
		</div>
		<div class="section">
			<div class="background-image"
				style="background-image: url('/kbofantasy/images/2012.png')"></div>
			<div class="container">
				<div class="row">
					<div class="col-md-12 text-center">
						<div class="col-md-12 text-center">
							<div class="btn-group btn-group-lg">
								<a class="btn btn-primary dropdown-toggle" data-toggle="dropdown"><i
									class="fa fa-3x fa-fw fa-gamepad"></i><br> Game <span
									class="fa fa-caret-down"></span></a>
								<ul class="dropdown-menu" role="menu">
								<% if(user!=null){%>
									<li><a href="/kbofantasy/myteam.do?member_cd=<%= user.getMember_cd() %>&game_dt=20160521&pathurl=/game/game.jsp">마이 라인업</a></li>
									<%} else { %>
									<li><a>내 팀 보기</a></li>
									<% } %>
									<li><a href="/kbofantasy/view.do?pathurl=/game/ranking.jsp">게임랭킹</a></li>
								</ul>
							</div>
							<div class="btn-group btn-group-lg">
								<a class="btn btn-primary dropdown-toggle" data-toggle="dropdown"><i
									class="fa fa-3x fa-table"></i><br> Record <span
									class="fa fa-caret-down"></span></a>
								<ul class="dropdown-menu" role="menu">
									<li><a href="javascript:eventListUrl()">일정 / 결과</a></li>
									<li><a href="/kbofantasy/record/teamTable.do?pathurl=/record/teamTable.jsp">팀 순위</a></li>
								</ul>
							</div>
							<div class="btn-group btn-group-lg">
								<a class="btn btn-primary dropdown-toggle menu-icon"
									data-toggle="dropdown"><i
									class="fa fa-3x fa-fw fa-newspaper-o"></i><br> Media <span
									class="fa fa-caret-down"></span></a>
								<ul class="dropdown-menu" role="menu">
									<li><a href="/kbofantasy/view.do?pathurl=/media/news.jsp">뉴스센터</a>
										<a href="/kbofantasy/view.do?pathurl=/media/video.jsp">영상센터</a>
									</li>
								</ul>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</body>

</html>