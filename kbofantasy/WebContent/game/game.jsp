<%@page import="game.dto.GamePlayerDTO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="member.dto.KBOLoginDTO"%>
<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
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
<link href="/kbofantasy/css/gamestyle.css" rel="stylesheet"
	type="text/css">
</head>
<body>
<%
	KBOLoginDTO user= (KBOLoginDTO)session.getAttribute("login");
	ArrayList<GamePlayerDTO> mypinfolist = (ArrayList<GamePlayerDTO>)request.getAttribute("mypinfolist"); 
	int member_cd= (int)request.getAttribute("member_cd");
	
		GamePlayerDTO spdto = mypinfolist.get(0);
		GamePlayerDTO rpdto = mypinfolist.get(1);
		GamePlayerDTO dhdto = mypinfolist.get(2);
		GamePlayerDTO cdto = mypinfolist.get(3);
		GamePlayerDTO fbdto = mypinfolist.get(4);
		GamePlayerDTO sbdto = mypinfolist.get(5);
		GamePlayerDTO tbdto = mypinfolist.get(6);
		GamePlayerDTO ssdto = mypinfolist.get(7);
		GamePlayerDTO lfdto = mypinfolist.get(8);
		GamePlayerDTO cfdto = mypinfolist.get(9);
		GamePlayerDTO rfdto = mypinfolist.get(10);
		
	
%>
<script type="text/javascript">
	$(document).ready(function() {
		
		// 오늘 날짜 표시
		var d = new Date();
		$("#today").text(" " + (d.getMonth() + 1) + ". " + d.getDate() + " ");
		
 		// 이전달, 다음달 링크 생성
 		var prev_game_dt = 0;
 		var next_game_dt = 0;
 		var month = 0;
		var prevMonth = month - 1;
 		var nextMonth = month + 1;
 		
 		if(prevMonth >= 4) {
 			$("#prevMonth").attr("href", "/kbofantasy/game.do?member_cd=<%=member_cd%>&game_dt=" + prev_game_dt + "pathurl=/game/myTeam.jsp");
 		} else {
 			$("#prevMonth").attr("href", "javascript:alert('이전 경기가 없습니다.')");
 		}
 		if(nextMonth <= 9) {
			$("#nextMonth").attr("href", "/kbofantasy/game.do?member_cd=<%=member_cd%>&game_dt=" + next_game_dt + "pathurl=/game/myTeam.jsp");
 		} else {
 			$("#nextMonth").attr("href", "javascript:alert('다음 경기가 없습니다.')");
 		}
		
		// 버튼 클릭 액션
		$("#rollback").on("click", function() {
			alert("복구");
		});

		$("#out").on("click", function() {
			alert("방출");
		});

		$("#save").on("click", function() {
			alert("저장");
		});

		$("#setcap").on("click", function() {
			alert("캡틴설정");
		});
		$(".player").on("click", function() {
			$(":hidden").show();
		});
		$("#close").on("click", function() {
			$("#selection").hide();
		});
	});
</script>

	<div class="section">
		<div class="container">
			<div class="row">
				<div class="col-md-12">
					<div class="page-header text-primary">
						<h1>마이 라인업</h1>
					</div>
					<div id="gamecontent">
					<ul class="lead pager">
						<li><a id="prevMonth" href="#">← Prev</a></li>
						<span id="today"></span>
						<li><a a id="nextMonth" href="#">Next →</a></li>
							<li id="btn"><span id="save" class="c_btn">라인업 저장</span></li>
						</ul>
						<div id="userinfo">
							<div>
								<h4><%=user.getName()%>님</h4>
								<br>
							</div>
							<div>일일점수: 0</div>
							<div>누적점수: <%= user.getPoint() %></div>
						</div>
						<div id="sp" class="player">
							<span class="img"> <img src="http://www.koreabaseball.com/FILE/person/middle/<%= spdto.getPlayer_Cd() %>.jpg" width="64" height="68"/>
							</span> <span class="txt"> <span class="pointsp"><%= "No." + spdto.getBack_No() + " " + spdto.getPlayer_Name() %></span> <br><%= spdto.getPosition_Dt() %>
								<br><%= spdto.getSalary() %> <br>Point
							</span>
						</div>
						<div id="rp" class="player">
							<span class="img"> <img src="http://www.koreabaseball.com/FILE/person/middle/<%= rpdto.getPlayer_Cd() %>.jpg" width="64" height="68"/>
							</span> <span class="txt"> <span class="pointsp"><%= "No." + rpdto.getBack_No() + " " + rpdto.getPlayer_Name() %></span> <br><%= rpdto.getPosition_Dt() %>
								<br><%= rpdto.getSalary() %> <br>Point
							</span>
						</div>
						<div id="c" class="player">
							<span class="img"> <img src="http://www.koreabaseball.com/FILE/person/middle/<%= cdto.getPlayer_Cd() %>.jpg" width="64" height="68"/>
							</span> <span class="txt"> <span class="pointsp"><%= "No." + cdto.getBack_No() + " " + cdto.getPlayer_Name() %></span> <br><%= cdto.getPosition_Dt() %>
								<br><%= cdto.getSalary() %> <br>Point
							</span>
						</div>
						<div id="b1" class="player">
							<span class="img"> <img src="http://www.koreabaseball.com/FILE/person/middle/<%= fbdto.getPlayer_Cd() %>.jpg" width="64" height="68"/>
							</span> <span class="txt"> <span class="pointsp"><%= "No." + fbdto.getBack_No() + " " + fbdto.getPlayer_Name() %></span> <br><%= fbdto.getPosition_Dt() %>
								<br><%= fbdto.getSalary() %> <br>Point
							</span>
						</div>
						<div id="b2" class="player">
							<span class="img"> <img src="http://www.koreabaseball.com/FILE/person/middle/<%= sbdto.getPlayer_Cd() %>.jpg" width="64" height="68"/>
							</span> <span class="txt"> <span class="pointsp"><%= "No." + sbdto.getBack_No() + " " + sbdto.getPlayer_Name() %></span> <br><%= sbdto.getPosition_Dt() %>
								<br><%= sbdto.getSalary() %> <br>Point
							</span>
						</div>
						<div id="b3" class="player">
							<span class="img"> <img src="http://www.koreabaseball.com/FILE/person/middle/<%= tbdto.getPlayer_Cd() %>.jpg" width="64" height="68"/>
							</span> <span class="txt"> <span class="pointsp"><%= "No." + tbdto.getBack_No() + " " + tbdto.getPlayer_Name() %></span> <br><%= tbdto.getPosition_Dt() %>
								<br><%= tbdto.getSalary() %> <br>Point
							</span>
						</div>
						<div id="ss" class="player">
							<span class="img"> <img src="http://www.koreabaseball.com/FILE/person/middle/<%= ssdto.getPlayer_Cd() %>.jpg" width="64" height="68"/>
							</span> <span class="txt"> <span class="pointsp"><%= "No." + ssdto.getBack_No() + " " + ssdto.getPlayer_Name() %></span> <br><%= ssdto.getPosition_Dt() %>
								<br><%= ssdto.getSalary() %> <br>Point
							</span>
						</div>
						<div id="lf" class="player">
							<span class="img"> <img src="http://www.koreabaseball.com/FILE/person/middle/<%= lfdto.getPlayer_Cd() %>.jpg" width="64" height="68"/>
							</span> <span class="txt"> <span class="pointsp"><%= "No." + lfdto.getBack_No() + " " + lfdto.getPlayer_Name() %></span> <br><%= lfdto.getPosition_Dt() %>
								<br><%= lfdto.getSalary() %> <br>Point
							</span>
						</div>
						<div id="cf" class="player">
							<span class="img"> <img src="http://www.koreabaseball.com/FILE/person/middle/<%= cfdto.getPlayer_Cd() %>.jpg" width="64" height="68"/>
							</span> <span class="txt"> <span class="pointsp"><%= "No." + cfdto.getBack_No() + " " + cfdto.getPlayer_Name() %></span> <br><%= cfdto.getPosition_Dt() %>
								<br><%= cfdto.getSalary() %> <br>Point
							</span>
						</div>
						<div id="rf" class="player">
							<span class="img"> <img src="http://www.koreabaseball.com/FILE/person/middle/<%= rfdto.getPlayer_Cd() %>.jpg" width="64" height="68"/>
							</span> <span class="txt"> <span class="pointsp"><%= "No." + rfdto.getBack_No() + " " + rfdto.getPlayer_Name() %></span> <br><%= rfdto.getPosition_Dt() %>
								<br><%= rfdto.getSalary() %> <br>Point
							</span>
						</div>
						<div id="dh" class="player">
							<span class="img"> <img src="http://www.koreabaseball.com/FILE/person/middle/<%= dhdto.getPlayer_Cd() %>.jpg" width="64" height="68"/>
							</span> <span class="txt"> <span class="pointsp"><%= "No." + dhdto.getBack_No() + " " + dhdto.getPlayer_Name() %></span> <br><%= dhdto.getPosition_Dt() %>
								<br><%= dhdto.getSalary() %> <br>Point
							</span>
						</div>

						<div id="selection" style="display: none;">
							<div id="pl_select">
								<div class="title">선수선택</div>
								<div class="btn-group btn-group-lg">
									<a class="btn btn-primary dropdown-toggle"
										data-toggle="dropdown"> Dropdown <span
										class="fa fa-caret-down"></span>
									</a>
									<ul class="dropdown-menu" role="menu">
										<li><a href="#">Action</a></li>
									</ul>

									<input type="text"><span><img
										src="http://casspoint.mbcplus.com/img/lineup/ico_search2.png"
										id="search_btn"></span>
								</div>
								<div class="btn-group">
									<a href="#" class="btn btn-default">통합</a> <a href="#"
										class="btn btn-default">5g평균</a> <a href="#"
										class="btn btn-default">최근</a> <a href="#"
										class="btn btn-default">가성비</a> <a href="#"
										class="btn btn-default">연봉</a>
								</div>
								<ul class="list-group">
									<li class="list-group-item">Cras justo odio</li>
									<li class="list-group-item">Dapibus ac facilisis in</li>
									<li class="list-group-item">Morbi leo risus</li>
									<li class="list-group-item">Porta ac consectetur ac</li>
									<li class="list-group-item">Vestibulum at eros</li>
									<li class="list-group-item">Vestibulum at eros</li>
									<li class="list-group-item">Vestibulum at eros</li>
								</ul>
							</div>
							<div id="pl_info">
								<div class="title">
									선수정보<span id="close">x</span>
								</div>
								<div id="profile">
									<img
										src="http://casspoint.mbcplus.com/data/player/pic/2015/63x79/11224.jpg" />
									선수정보
								</div>
								<table class="table">
									<thead>
										<tr>
											<th>포지션</th>
											<th>가성비</th>
											<th>선택울</th>
											<th>RANK</th>
										</tr>
									</thead>
									<tbody>
										<tr>
											<td>1</td>
											<td>Mark</td>
											<td>Otto</td>
											<td>@mdo</td>
										</tr>
									</tbody>
								</table>
								<a id="outbtn" class="btn btn-primary">방출하기</a>
								<p id="sum">2016년 포인트 총합</p>
								<table class="table">
									<thead>
										<tr>
											<th>시즌평균</th>
											<th>1G최고</th>
											<th>1G최저</th>
											<th>총평균</th>
											<th>원정평균</th>
										</tr>
									</thead>
									<tbody>
										<tr>
											<td>1</td>
											<td>Mark</td>
											<td>Otto</td>
											<td>@mdo</td>
											<td>@mdo</td>
										</tr>
									</tbody>
								</table>
								<table class="table">
									<thead>
										<tr>
											<th>승리경기평균</th>
											<th>패배경기평균</th>
											<th>최근5G평균</th>
											<th>최근10평균</th>
											<th>가성비</th>
										</tr>
									</thead>
									<tbody>
										<tr>
											<td>1</td>
											<td>Mark</td>
											<td>Otto</td>
											<td>@mdo</td>
										</tr>
									</tbody>
								</table>
							</div>
						</div>
					</div>

				</div>
			</div>
		</div>
	</div>


</body>
</html>