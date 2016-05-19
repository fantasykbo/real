<%@page import="org.json.simple.JSONArray"%>
<%@page import="org.json.simple.parser.JSONParser"%>
<%@page import="org.json.simple.JSONObject"%>
<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<!-- <meta charset="utf-8"> -->
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
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<script src="//code.jquery.com/jquery-1.11.3.min.js"></script>
<title>Insert title here</title>
<%
	String kbo = (String) request.getAttribute("kbo");
	int Inn = (int) request.getAttribute("Inn");
	String eventId = (String) request.getAttribute("eventId");
	String month = (String) request.getAttribute("month");
%>
</head>
<body>
<script type="text/javascript">
	$(document).ready(function(){
				kbo = '<%=kbo%>';
				Inn = '<%=Inn%>';
                obj = JSON.parse(kbo);
                if(Inn==0){
                	Inn = obj.currentInning;
                }
                
                
                curInn = obj.currentInning;
                rel = obj.relayTexts;
 				curBat = rel.currentBatter.liveText;
 				curTxtSize = rel.currentBatterTexts.length;
 				
 				// 팀정보 출력
 				$("#awaylogo").attr("src","http://imgsports.naver.net/images/emblem/new/kbo/default/"+obj.gameInfo.aCode+".png");
 				$("#homelogo").attr("src","http://imgsports.naver.net/images/emblem/new/kbo/default/"+obj.gameInfo.hCode+".png");
 				$("#a_score0").text(obj.gameInfo.aName);
 				$("#h_score0").text(obj.gameInfo.hName);
 				$("#ab_record").text(obj.gameInfo.aName+"타자기록");
 				$("#ap_record").text(obj.gameInfo.aName+"투수기록");
 				$("#hb_record").text(obj.gameInfo.hName+"타자기록");
 				$("#hp_record").text(obj.gameInfo.hName+"투수기록");
 				
 				// 경기 날짜,시간 출력
 				var gDayArr = new Array('일', '월', '화', '수', '목', '금', '토');

 		 	    var gDate = obj.gameInfo.gdate.toString();
 		 		var gYear = gDate.substr(0,4);
 		 		var gMonth = gDate.substr(4,2);
 		 		var gDay = gDate.substr(6,2);
 		 		var d = new Date(gYear, gMonth - 1, gDay);
 		 		var gWeekday = gDayArr[d.getDay()];
 				$("#schedule").text(gYear+"."+gMonth+"."+gDay+" ("+gWeekday+") 시작시간: "
 						+obj.gameInfo.gtime+" 경기장: "+obj.gameInfo.stadium+"경기장");
 				// liveText를 제외한 모든 정보 출력
 				refreshInfo();
                // liveText 출력
                totalText();
                

                //Inn link걸기
                //종료된 게임일 경우 바로 서블릿 호출
                //진행중인 게임일 경우 Ajax호출
                if(obj.gameInfo.statusCode==4){ 
                	if(Inn<10){
    		 			for(i=1;i<=curInn;i++){
    		 				$("#page"+i).attr("href","javascript:refreshText("+$("#page"+i).text()+")");    		 				
    		 			}
    	 			}else{
    	 				$("#pageext").attr("href","javascript:refreshText("+curInn+")");
    	 			}
                }else{      	
                	if(Inn<10){	 				
    		 			for(i=1;i<=curInn;i++){				   		 				
    		 				$("#page"+i).attr("href","javascript:Ajax('<%=month%>','<%=eventId%>',"+$('#page'+i).text()+")");
    		 				ajaxInn = $('#page'+i).text();
    		 			}
    	 			}else{
    	 				inn = $('#page'+i).text();
    	 				$("#pageext").attr("href","javascript:Ajax('<%=month%>','<%=eventId%>',"+curInn+")");
    	 			}
                }
 				
                
	 			
	 			
	 				 			

    })
	function Ajax(month, eventId, Inn) {
		$(".page").on("click", function(){		
			selectInn = Inn;
			$.get("/kbofantasy/livetext.do",{"month":month,"eventId":eventId,"Inn":Inn,"path":"ajax"},live);
		});
	};
	function live(txt){	//서버와 통신이 성공하면 처리결과를 success에 지정한 함수를 호출하며 넘겨준다.
		kbo = txt;
		/* Inn = Inn; */
		Inn = selectInn;
        obj = JSON.parse(kbo);
        curInn = obj.currentInning;
        rel = obj.relayTexts;
		curBat = rel.currentBatter.liveText;
		curTxtSize = rel.currentBatterTexts.length;
		
		refreshText(Inn);
	}
    function totalText(){
		if(Inn==curInn){
	 		//경기가 끝났을 경우 final정보 출력
	 		if(obj.relayTexts.final.length!=0){
	 			for(i=0;i<rel.final.length;i++){
	 		 		finalinfo = $("<li>"+rel.final[i].liveText+"</li>");
	 		 	 	$("#live").children().append(finalinfo);
	 		 	}
	 		 	$("#live").children().append("<li>============================</li>"); 		 	 			
	 		}
	 		//경기가 아직 진행중일때
	 		
			curBatText();
			gametext(Inn);
	 	}else{
	 		gametext(Inn);
	 	}
	 }
    
	//이전 게임정보 출력
    function gametext(Inn){
		if(Inn>9){
			for(i=10; i<=curInn; i++){
				/*  Object.keys(obj.relayTexts[Inn]).length object 사이즈 구하기 */
				reltxt = Object.keys(obj.relayTexts[Inn]).length;				
				for(j=1; j<=reltxt; j++){	               	
		            livetext = $("<li>"+obj.relayTexts[i][j-1].liveText+"</li>");
		            $("#live").children().append(livetext);
		            }	
			}
		}else{
			reltxt = Object.keys(obj.relayTexts[Inn]).length;
			for(j=0; j<reltxt; j++){	               	
	            livetext = $("<li>"+obj.relayTexts[Inn][j].liveText+"</li>");
	            $("#live").children().append(livetext);
	            }			
		}
		$("#live").children().append("<li>============================</li>");
	}
	
	function curBatText(){
			//현재 타자이름 출력
			livecurBat = $("<li>"+"현재타자 : "+curBat+"</li>");
			$("#live").children().append(livecurBat);		
			$("#live").children().append("<li>----------------------------</li>");
			
			//현재 타자 배팅정보 출력
			for(i=0; i< curTxtSize; i++){
				curTxt = $("<li>"+obj.relayTexts.currentBatterTexts[i].liveText+"</li>");
				$("#live").children().append(curTxt);

			}
			$("#live").children().append("<li>----------------------------</li>");
	}
	
	function refreshText(inn){
		Inn = inn;
		$("#live1").empty();
		totalText();
	}
	
	function refreshInfo(){
		// 팀 스코어 출력
		$("#a_teamscore").text(obj.scoreBoard.rheb.away.r);
		$("#h_teamscore").text(obj.scoreBoard.rheb.home.r);
		
		// 공격팀 출력
		if(obj.relayTexts.final.length==0){
			$("#status").text(rel.currentOffensiveTeam.liveText);
		}else{
			$("#status").text("경기 종료");
		}
		// 현재 선수정보 출력
		$("#awayimg").attr("src","http://www.koreabaseball.com/FILE/person/middle/"+obj.currentPlayers.away.playerInfo.pCode+".jpg");
		$("#homeimg").attr("src","http://www.koreabaseball.com/FILE/person/middle/"+obj.currentPlayers.home.playerInfo.pCode+".jpg");
		$("#awayPlayer").append("<h4 class='text-center' id=a_name></h4>");
		$("#a_name").text(obj.currentPlayers.away.playerInfo.name +
						"  /  "+ obj.currentPlayers.away.playerInfo.hitType);
		
		$("#homePlayer").append("<h4 class='text-center' id=h_name></h4>");
		$("#h_name").text(obj.currentPlayers.home.playerInfo.name +
						"  /  "+ obj.currentPlayers.home.playerInfo.hitType); 
		
		// 스코어 보드 출력
		for(i=0; i<curInn; i++){
			$("#a_score"+(i+1)).text(obj.scoreBoard.inn.away[i]);
			$("#h_score"+(i+1)).text(obj.scoreBoard.inn.home[i]);
		}
		
		$("#a_scoreR").text(obj.scoreBoard.rheb.away.r);
		$("#a_scoreH").text(obj.scoreBoard.rheb.away.h);
		$("#a_scoreE").text(obj.scoreBoard.rheb.away.e);
		$("#a_scoreB").text(obj.scoreBoard.rheb.away.b);
		
		$("#h_scoreR").text(obj.scoreBoard.rheb.home.r);
		$("#h_scoreH").text(obj.scoreBoard.rheb.home.h);
		$("#h_scoreE").text(obj.scoreBoard.rheb.home.e);
		$("#h_scoreB").text(obj.scoreBoard.rheb.home.b);
		
		// 팀정보 출력
		$("#a_teamname").text(obj.gameInfo.aFullName);
		$("#h_teamname").text(obj.gameInfo.hFullName);
		$("#a_rank").text(obj.awayStandings.rank+"위");
		$("#h_rank").text(obj.homeStandings.rank+"위");
		$("#a_record").text(obj.awayStandings.w+"승 "+obj.awayStandings.d+"무 "+obj.awayStandings.l+"패");
		$("#h_record").text(obj.homeStandings.w+"승 "+obj.homeStandings.d+"무 "+obj.homeStandings.l+"패");
		
		// away팀 타자정보 출력
		$("#ab_body").empty();
		for(i=0; i<obj.awayTeamLineUp.batter.length; i++){
			ab_text ="";
			away_batter = obj.awayTeamLineUp.batter[i];
			if(obj.awayTeamLineUp.batter[i].seqno==1){
				ab_text += "<tr><td>" + away_batter.batOrder + "</td>";			
			}else{
				ab_text += "<tr><td>교 " + away_batter.batOrder + "</td>";
			}
			ab_text += "<td>" + away_batter.name + "</td>";	
			ab_text += "<td>" + away_batter.posName + "</td>";
			ab_text += "<td>" + away_batter.ab + "</td>";
			ab_text += "<td>" + away_batter.run + "</td>";
			ab_text += "<td>" + away_batter.hit + "</td>";
			ab_text += "<td>" + away_batter.rbi + "</td>";
			ab_text += "<td>" + away_batter.bb + "</td>";
			ab_text += "<td>" + away_batter.so + "</td>";
			ab_text += "<td>" + away_batter.hbp + "</td>";
			ab_text += "<td>" + away_batter.todayHra + "</td>";
			ab_text += "<td>" + away_batter.seasonHra + "</td>";
			ab_text += "<td>" + away_batter.vsHra + "</td></tr>";
			$("#ab_body").append(ab_text);
			
		}
		
		// away팀 투수정보 출력
		$("#ap_body").empty();
		for(i=0; i<obj.awayTeamLineUp.pitcher.length; i++){
			ap_text ="";
			away_pitcher = obj.awayTeamLineUp.pitcher[i];
			ap_text += "<tr><td>" + away_pitcher.name + "</td>"
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
		
		// home팀 타자정보 출력
		$("#hb_body").empty();
		for(i=0; i<obj.homeTeamLineUp.batter.length; i++){
			hb_text ="";
			home_batter = obj.homeTeamLineUp.batter[i];
			if(obj.homeTeamLineUp.batter[i].seqno==1){
				hb_text += "<tr><td>" + home_batter.batOrder + "</td>";			
			}else{
				hb_text += "<tr><td>교 " + home_batter.batOrder + "</td>";
			}
			hb_text += "<td>" + home_batter.name + "</td>";	
			hb_text += "<td>" + home_batter.posName + "</td>";
			hb_text += "<td>" + home_batter.ab + "</td>";
			hb_text += "<td>" + home_batter.run + "</td>";
			hb_text += "<td>" + home_batter.hit + "</td>";
			hb_text += "<td>" + home_batter.rbi + "</td>";
			hb_text += "<td>" + home_batter.bb + "</td>";
			hb_text += "<td>" + home_batter.so + "</td>";
			hb_text += "<td>" + home_batter.hbp + "</td>";
			hb_text += "<td>" + home_batter.todayHra + "</td>";
			hb_text += "<td>" + home_batter.seasonHra + "</td>";
			hb_text += "<td>" + home_batter.vsHra + "</td></tr>";
			$("#hb_body").append(hb_text);
			
		}
		
		// home팀 투수정보 출력
		$("#hp_body").empty();
		for(i=0; i<obj.homeTeamLineUp.pitcher.length; i++){
			hb_text ="";
			home_pitcher = obj.homeTeamLineUp.pitcher[i];
			hb_text += "<tr><td>" + home_pitcher.name + "</td>"
			hb_text += "<td>" + home_pitcher.inn + "</td>"
			hb_text += "<td>" + home_pitcher.ballCount + "</td>"
			hb_text += "<td>" + home_pitcher.hit + "</td>"
			hb_text += "<td>" + home_pitcher.hr + "</td>"
			hb_text += "<td>" + home_pitcher.bb + "</td>"
			hb_text += "<td>" + home_pitcher.kk + "</td>"
			hb_text += "<td>" + home_pitcher.run + "</td>"
			hb_text += "<td>" + home_pitcher.er + "</td>"
			hb_text += "<td>" + home_pitcher.todayEra + "</td>"
			hb_text += "<td>" + home_pitcher.seasonEra + "</td>"
			hb_text += "<td>" + home_pitcher.vsEra + "</td></tr>"
			$("#hp_body").append(hb_text);
		}
		
	}
</script>
	<div class="section">
		<div class="container">
			<div class="row">
				<div class="col-md-12">
					<div class="page-header text-primary">
						<h1>문자중계</h1>
					</div>
				</div>
			</div>
			<div class="row">
				<div class="col-md-2 text-center">
					<img class="center-block img-responsive" id="awaylogo">
				</div>
				<div class="col-md-1">
					<h1 class="text-center" id="a_teamscore"></h1>
				</div>
				<div class="col-md-6">
					<table class="table table-condensed">
						<thead>
							<tr>
								<th></th>
								<th>1</th>
								<th>2</th>
								<th>3</th>
								<th>4</th>
								<th>5</th>
								<th>6</th>
								<th>7</th>
								<th>8</th>
								<th>9</th>
								<th>10</th>
								<th>11</th>
								<th>12</th>
								<th>R</th>
								<th>H</th>
								<th>E</th>
								<th>B</th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<td id="a_score0"></td>
								<td id="a_score1">0</td>
								<td id="a_score2">-</td>
								<td id="a_score3">-</td>
								<td id="a_score4">-</td>
								<td id="a_score5">-</td>
								<td id="a_score6">-</td>
								<td id="a_score7">-</td>
								<td id="a_score8">-</td>
								<td id="a_score9">-</td>
								<td id="a_score10">-</td>
								<td id="a_score11">-</td>
								<td id="a_score12">-</td>
								<td id="a_scoreR">0</td>
								<td id="a_scoreH">0</td>
								<td id="a-scoreE">0</td>
								<td id="a_scoreB">0</td>								
							</tr>
							<tr>
								<td id="h_score0"></td>
								<td id="h_score1">0</td>
								<td id="h_score2">-</td>
								<td id="h_score3">-</td>
								<td id="h_score4">-</td>
								<td id="h_score5">-</td>
								<td id="h_score6">-</td>
								<td id="h_score7">-</td>
								<td id="h_score8">-</td>
								<td id="h_score9">-</td>
								<td id="h_score10">-</td>
								<td id="h_score11">-</td>
								<td id="h_score12">-</td>
								<td id="h_scoreR">0</td>
								<td id="h_scoreH">0</td>
								<td id="h_scoreE">0</td>
								<td id="h_scoreB">0</td>
							</tr>
						</tbody>
					</table>
					<h4 class="text-center" id="schedule"></h4>
				</div>
				<div class="col-md-1">
					<h1 class="text-center" id="h_teamscore"></h1>
				</div>
				<div class="col-md-2 text-center">
					<img class="center-block img-responsive" id="homelogo">
				</div>
			</div>
			<div class="row" draggable="true">
				<div class="col-md-3">
					<h1 class="text-left">
						<h1 id="a_teamname"></h1><br>
						<span class="label label-default" id="a_rank" style="font-size:40"></span>
						<span id="a_record"></span>
					</h1>
				</div>
				<div class="col-md-6">
					<h1 class="text-center" id="status"></h1>
				</div>
				<div class="col-md-3 text-right">
					<h1 class="text-right">
						<h1 id="h_teamname"></h1><br>
						<span class="label label-default" id="h_rank" style="font-size:40"></span>
						<span id="h_record"></span>
					</h1>
				</div>
			</div>
			<div class="row">
				<div class="col-md-4 text-center">
					<div class="col-md-12">
						<ul class="pagination">
							<li><a id="page1" class="page">1</a></li>
							<li><a id="page2" class="page">2</a></li>
							<li><a id="page3" class="page">3</a></li>
							<li><a id="page4" class="page">4</a></li>
							<li><a id="page5" class="page">5</a></li>
							<li><a id="page6" class="page">6</a></li>
							<li><a id="page7" class="page">7</a></li>
							<li><a id="page8" class="page">8</a></li>
							<li><a id="page9" class="page">9</a></li>
							<li><a id="pageext" class="page">연장</a></li>
						</ul>
					</div>
					<div class="col-md-12" id="live">
						<ol class="lead list-unstyled" id="live1">
						</ol>
					</div>
				</div>
				<div class="col-md-8">
					<div class="col-md-12">
						<h3 class="text-muted">실시간 투타정보</h3>
						<div class="col-md-6">
							<div class="thumbnail">
								<img class="img-responsive" id="awayimg">
								<div class="caption" id="awayPlayer"></div>
							</div>
						</div>
						<div class="col-md-6">
							<div class="thumbnail">
								<img class="img-responsive" id="homeimg">
								<div class="caption" id="homePlayer"></div>
							</div>
						</div>
							<div class="col-md-12">
								<h3 class="text-muted" id="ab_record"></h3>
								<table
									class="table table-bordered table-condensed table-striped">
									<thead>
										<tr>
											<th>타순</th>
											<th>선수명</th>
											<th>포지션</th>
											<th>타수</th>
											<th>득점</th>
											<th>안타</th>
											<th>타점</th>
											<th>볼넷</th>
											<th>삼진</th>
											<th>사구</th>
											<th>오늘타율</th>
											<th>시즌타율</th>
											<th>상대타율</th>
										</tr>
									</thead>
									<tbody id="ab_body">
									</tbody>
								</table>
								<h3 class="text-muted" id="ap_record"></h3>
								<table
									class="table table-bordered table-condensed table-striped">
									<thead>
										<tr>
											<th>선수명</th>
											<th>이닝</th>
											<th>투구수</th>
											<th>피안타</th>
											<th>피홈런</th>
											<th>볼넷</th>
											<th>삼진</th>
											<th>실점</th>
											<th>자책</th>
											<th>오늘평균자책</th>
											<th>시즌평균자책</th>
											<th>상대평균자책</th>
										</tr>
									</thead>
									<tbody id="ap_body">
									</tbody>
								</table>
							</div>
							<div class="col-md-12">
								<h3 class="text-muted" id="hb_record">삼성 라이온즈 타자기</h3>
								<table
									class="table table-bordered table-condensed table-striped">
									<thead>
										<tr>
											<th>타순</th>
											<th>선수명</th>
											<th>포지션</th>
											<th>타수</th>
											<th>득점</th>
											<th>안타</th>
											<th>타점</th>
											<th>볼넷</th>
											<th>삼진</th>
											<th>사구</th>
											<th>오늘타율</th>
											<th>시즌타율</th>
											<th>상대타율</th>
										</tr>
									</thead>
									<tbody id="hb_body">
									</tbody>
								</table>
								<h3 class="text-muted" id="hp_record"></h3>
								<table
									class="table table-bordered table-condensed table-striped">
									<thead>
										<tr>
											<th>선수명</th>
											<th>이닝</th>
											<th>투구수</th>
											<th>승</th>
											<th>피홈런</th>
											<th>볼넷</th>
											<th>삼진</th>
											<th>실점</th>
											<th>자책</th>
											<th>오늘평균자책</th>
											<th>시즌평균자책</th>
											<th>상대평균자책</th>
										</tr>
									</thead>
									<tbody id="hp_body">
									</tbody>
								</table>
							</div>
						</div>
					</div>
				</div>
			</div>
</body>
</html>