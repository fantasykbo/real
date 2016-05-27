<%@page import="com.google.gson.annotations.JsonAdapter"%>
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

		
		// 버튼 클릭 액션
		$("#save").on("click", function() {
			alert("저장");
		});
		
		$(".player").on("click", function() {
			$("#selection").show();
		});
		
		$("#close").on("click", function() {
			$("#selection").hide();
		});
		
		$("#select_p").on("click", function() {
			$("#selection").hide();
		});
		
		$("#sp").on("click", function() {
			//에이작스동작
			position("P");			
		});
		
		$("#rp").on("click", function() {
			//에이작스동작
			position("P");			
		});
		
		$("#c").on("click", function() {
			//에이작스동작
			position("C");			
		});
		
		$("#b1").on("click", function() {
			//에이작스동작
			position("1B");			
		});
		
		$("#b2").on("click", function() {
			//에이작스동작
			position("2B");			
		});
		
		$("#b3").on("click", function() {
			//에이작스동작
			position("3B");			
		});
		
		$("#ss").on("click", function() {
			//에이작스동작
			position("SS");			
		});
		
		$("#lf").on("click", function() {
			//에이작스동작
			position("LF");			
		});
		
		$("#cf").on("click", function() {
			//에이작스동작
			position("CF");
		});
		
		$("#rf").on("click", function() {
			//에이작스동작
			position("RF");			
		});
		
		$("#dh").on("click", function() {
			//에이작스동작
			position("DH");
		});			
	});
	
	function setPlayer(){
		alert('준비 중 입니다.');
	}
	
	function position(position_dt){
		
		xhr = new XMLHttpRequest();
		xhr.onreadystatechange = updatePlayer;
		xhr.open("POST", "/kbofantasy/scoutplayer.do", true);
		xhr.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
		xhr.send("position_dt="+position_dt);
		$("#player_list").empty();
	}

	function updatePlayer() {
		console.log("상태값:" + xhr.readyState);
		if (xhr.readyState == 4 && xhr.status == 200) {
			json = JSON.parse(xhr.responseText);
			console.log(json);
			txt = xhr.responseText;
			json = JSON.parse(txt);
			console.log(txt);
			text ="";
			
			for(i=0; i<json.length; i++){			
			text += "<tr><td><img src=http://www.koreabaseball.com/FILE/person/middle/"+json[i].player_Cd+".jpg width=50 height=60></td>"
			text += "<td><a href=javascript:setPlayer()>"+json[i].player_Name+"</a></td>"
			text += "<td>" + json[i].position_Dt + "</td>"
			text += "<td>" + json[i].team_Cd + "</td>"
			text += "<td>" + json[i].salary + "</td>"
			text += "<td>" + json[i].hand + "</td></tr>"			
			}
			$("#player_list").append(text); 
		}
	}
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
						<li><a id="prevMonth" href="javascript:alert('준비 중 입니다.')">← Prev</a></li>
						<span id="today"></span>
						<li><a a id="nextMonth" href="javascript:alert('준비 중 입니다.')">Next →</a></li>
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
						<form id="myform">
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
								<div class="title">
									선수선택<span id="close">x</span>
								</div>
								<div style="overflow:scroll; height:90%;" class="col-md-12">
								<h3 class="text-muted" id=""></h3>
								<table
									class="table table-bordered table-condensed table-striped">
									<thead>
										<tr>
											<th>img</th>
											<th>선수명</th>
											<th>포지션</th>
											<th>팀명</th>
											<th>봉급</th>
											<th>hand</th>
										</tr>
									</thead>
									<tbody id="player_list">
									</tbody>
								</table>
								</div>
							</div>
						</div>
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>


</body>
</html>