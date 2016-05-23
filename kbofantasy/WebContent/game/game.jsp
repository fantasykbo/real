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
	
	if(request.getAttribute("myscoutplayer")==null){
	}else{
		ArrayList<GamePlayerDTO> myscoutplayer = (ArrayList<GamePlayerDTO>)request.getAttribute("myscoutplayer"); 
	}
	
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
		
		$("#sp").on("click", function() {
			//에이작스동작
			alert("동작");
			sp();			
		});
		
		$("#rp").on("click", function() {
			alert("동작");
			rp();			
		});
		
		$("#c").on("click", function() {
			//에이작스동작
			alert("동작");
			c();			
		});
		
		$("#b1").on("click", function() {
			//에이작스동작
			alert("동작");
			b1();			
		});
		
		$("#b2").on("click", function() {
			//에이작스동작
			alert("동작");
			b2();			
		});
		
		$("#b3").on("click", function() {
			//에이작스동작
			alert("동작");
			b3();			
		});
		
		$("#ss").on("click", function() {
			//에이작스동작
			alert("동작");
			ss();			
		});
		
		$("#lf").on("click", function() {
			//에이작스동작
			alert("동작");
			lf();			
		});
		
		$("#cf").on("click", function() {
			//에이작스동작
			alert("동작");
			cf();
		});
		
		$("#rf").on("click", function() {
			//에이작스동작
			alert("동작");
			rf();			
		});
		
		$("#dh").on("click", function() {
			//에이작스동작
			alert("동작");
			dh();
		});			
	});
	
	function sp() {
		xhr = new XMLHttpRequest();
		position_dt = "P";
		member_cd = '<%=member_cd%>';
		mypinfolist = '<%=mypinfolist%>';
		xhr.onreadystatechange = emailcheck;
		xhr.open("POST", "/kbofantasy/scoutplayer.do", true);
		xhr.setRequestHeader("Content-type",
				"application/x-www-form-urlencoded");
		
		xhr.send("position_dt="+position_dt,"member_cd="+member_cd,"mypinfolist="+mypinfolist);
	}
	
	function rp() {
		xhr = new XMLHttpRequest();
		position_dt = "P";
		member_cd = '<%=member_cd%>';
		mypinfolist = '<%=mypinfolist%>';
		xhr.onreadystatechange = emailcheck;
		xhr.open("POST", "/kbofantasy/scoutplayer.do", true);
		xhr.setRequestHeader("Content-type",
				"application/x-www-form-urlencoded");
		
		xhr.send("position_dt="+position_dt,"member_cd="+member_cd,"mypinfolist="+mypinfolist);
	}
	
	function c() {
		xhr = new XMLHttpRequest();
		position_dt = "C";
		member_cd = '<%=member_cd%>';
		mypinfolist = '<%=mypinfolist%>';
		xhr.onreadystatechange = emailcheck;
		xhr.open("POST", "/kbofantasy/scoutplayer.do", true);
		xhr.setRequestHeader("Content-type",
				"application/x-www-form-urlencoded");
		
		xhr.send("position_dt="+position_dt,"member_cd="+member_cd,"mypinfolist="+mypinfolist);
	}
	
	function b1() {
		xhr = new XMLHttpRequest();
		position_dt = "1B";
		member_cd = '<%=member_cd%>';
		mypinfolist = '<%=mypinfolist%>';
		xhr.onreadystatechange = emailcheck;
		xhr.open("POST", "/kbofantasy/scoutplayer.do", true);
		xhr.setRequestHeader("Content-type",
				"application/x-www-form-urlencoded");
		
		xhr.send("position_dt="+position_dt,"member_cd="+member_cd,"mypinfolist="+mypinfolist);
	}
	
	function b2() {
		xhr = new XMLHttpRequest();
		position_dt = "2B";
		member_cd = '<%=member_cd%>';
		mypinfolist = '<%=mypinfolist%>';
		xhr.onreadystatechange = emailcheck;
		xhr.open("POST", "/kbofantasy/scoutplayer.do", true);
		xhr.setRequestHeader("Content-type",
				"application/x-www-form-urlencoded");
		
		xhr.send("position_dt="+position_dt,"member_cd="+member_cd,"mypinfolist="+mypinfolist);
	}
	
	function b3() {
		xhr = new XMLHttpRequest();
		position_dt = "3B";
		member_cd = '<%=member_cd%>';
		mypinfolist = '<%=mypinfolist%>';
		xhr.onreadystatechange = emailcheck;
		xhr.open("POST", "/kbofantasy/scoutplayer.do", true);
		xhr.setRequestHeader("Content-type",
				"application/x-www-form-urlencoded");
		
		xhr.send("position_dt="+position_dt,"member_cd="+member_cd,"mypinfolist="+mypinfolist);
	}
	
	function ss() {
		xhr = new XMLHttpRequest();
		position_dt = "SS";
		member_cd = '<%=member_cd%>';
		mypinfolist = '<%=mypinfolist%>';
		xhr.onreadystatechange = emailcheck;
		xhr.open("POST", "/kbofantasy/scoutplayer.do?", true);
		xhr.setRequestHeader("Content-type",
				"application/x-www-form-urlencoded");
		
		xhr.send("position_dt="+position_dt,"member_cd="+member_cd,"mypinfolist="+mypinfolist);
	}
	
	function lf() {
		xhr = new XMLHttpRequest();
		position_dt = "LF";
		member_cd = '<%=member_cd%>';
		mypinfolist = '<%=mypinfolist%>';
		xhr.onreadystatechange = emailcheck;
		xhr.open("POST", "/kbofantasy/scoutplayer.do?", true);
		xhr.setRequestHeader("Content-type",
				"application/x-www-form-urlencoded");
		
		xhr.send("position_dt="+position_dt,"member_cd="+member_cd,"mypinfolist="+mypinfolist);
	}
	
	function cf() {
		xhr = new XMLHttpRequest();
		position_dt = "CF";
		member_cd = '<%=member_cd%>';
		mypinfolist = '<%=mypinfolist%>';
		xhr.onreadystatechange = emailcheck;
		xhr.open("POST", "/kbofantasy/scoutplayer.do?", true);
		xhr.setRequestHeader("Content-type",
				"application/x-www-form-urlencoded");
		
		xhr.send("position_dt="+position_dt,"member_cd="+member_cd,"mypinfolist="+mypinfolist);
	}
	
	function rf() {
		xhr = new XMLHttpRequest();
		position_dt = "RF";
		member_cd = '<%=member_cd%>';
		mypinfolist = '<%=mypinfolist%>';
		xhr.onreadystatechange = emailcheck;
		xhr.open("POST", "/kbofantasy/scoutplayer.do?", true);
		xhr.setRequestHeader("Content-type",
				"application/x-www-form-urlencoded");
		
		xhr.send("position_dt="+position_dt,"member_cd="+member_cd,"mypinfolist="+mypinfolist);
	}
	
	function dh() {
		xhr = new XMLHttpRequest();
		position_dt = "DH";
		member_cd = '<%=member_cd%>';
		mypinfolist = '<%=mypinfolist%>';
		xhr.onreadystatechange = emailcheck;
		xhr.open("POST", "/kbofantasy/scoutplayer.do?", true);
		xhr.setRequestHeader("Content-type",
				"application/x-www-form-urlencoded");
		
		xhr.send("position_dt="+position_dt,"member_cd="+member_cd,"mypinfolist="+mypinfolist);
	}
	
	function emailcheck() {
		console.log("상태값:" + xhr.readyState);
		if (xhr.readyState == 4 && xhr.status == 200) {
			/*  document.getElementById("#player_list").innerHTML = xhr.responseText; */
			myscoutplayer = xhr.responseText;
			text ="";
			<%-- text += "<tr><td><img src='http://www.koreabaseball.com/FILE/person/middle/<%= myscoutplayer.get(i).getPlayer_Cd() %>.jpg' width='64' height='68'/></td>" --%>
			text += "<tr><td>"+myscoutplayer.get(0).getPlayer_Name()+"</td>"
			text += "<td>" + myscoutplayer.get(0).getPosition_Dt() + "</td>"
			text += "<td>" + myscoutplayer.get(0).getSalary() + "</td>"
			text += "<td>" + myscoutplayer.get(0).getTeam_Cd() + "</td>"
			text += "<td>" + myscoutplayer.get(0).getHand() + "</td></tr>"
			$("#player_list").append(text);
		}

	}
	
	function selectList(myscoutplayer){
		

		/*  
			System.out.println(myscoutplayer.get(i).getPlayer_Cd());
			System.out.println(myscoutplayer.get(i).getPlayer_Name());
			System.out.println(myscoutplayer.get(i).getPosition_Dt());
			System.out.println(myscoutplayer.get(i).getSalary());
			System.out.println(myscoutplayer.get(i).getTeam_Cd());
			System.out.println(myscoutplayer.get(i).getHand()); */
		/* for(i=0;i<myscoutplayer.length;i++){ */
			text ="";
			<%-- text += "<tr><td><img src='http://www.koreabaseball.com/FILE/person/middle/<%= myscoutplayer.get(i).getPlayer_Cd() %>.jpg' width='64' height='68'/></td>" --%>
			text += "<tr><td>"+myscoutplayer.get(0).getPlayer_Name()+"</td>"
			text += "<td>" + myscoutplayer.get(0).getPosition_Dt() + "</td>"
			text += "<td>" + myscoutplayer.get(0).getSalary() + "</td>"
			text += "<td>" + myscoutplayer.get(0).getTeam_Cd() + "</td>"
			text += "<td>" + myscoutplayer.get(0).getHand() + "</td></tr>"
			$("#player_list").append(text);
		}
	/* function selectList(text){
		ArrayList<GamePlayerDTO> myscoutplayer = text;
		for(i=0; i<myscoutplayer.length; i++){
			ap_text ="";
			ap_text += "<tr><td>" + myscoutplayer. + "</td>"
			ap_text += "<td>" + away_pitcher.inn + "</td>"
			ap_text += "<td>" + away_pitcher.ballCount + "</td>"
			ap_text += "<td>" + away_pitcher.hit + "</td>"
			ap_text += "<td>" + away_pitcher.hr + "</td>"
			ap_text += "<td>" + away_pitcher.bb + "</td>"
			ap_text += "<td>" + away_pitcher.kk + "</td>"
			ap_text += "<td>" + away_pitcher.run + "</td>"
			ap_text += "<td>" + away_pitcher.er + "</td>"
			ap_text += "<td>" + away_pitcher.todayEra + "</td>"
			ap_text += "<td>" + away_pitcher.seasonEra + "</td>"
			ap_text += "<td>" + away_pitcher.vsEra + "</td></tr>"
			$("#ap_body").append(ap_text);
		}
	} */
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
								<div class="player_list" id="player_list">
									<!-- <a class="btn btn-primary dropdown-toggle"
										data-toggle="dropdown"> Dropdown <span
										class="fa fa-caret-down"></span>
									</a>
									<ul class="dropdown-menu" role="menu">
										<li><a href="#">Action</a></li>
									</ul>

									<input type="text"><span><img
										src="http://casspoint.mbcplus.com/img/lineup/ico_search2.png"
										id="search_btn"></span> -->
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