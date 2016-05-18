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

<script type="text/javascript">
	$(document).ready(function(){
				kbo = '<%=kbo%>';
				Inn = '<%=Inn%>';
                obj = JSON.parse(kbo);
                if(Inn==0){
                	Inn = obj.currentInning;
                }
                
                /*  Object.keys(obj.relayTexts[1][0]).length object 사이즈 구하기 */
                curInn = obj.currentInning;
                rel = obj.relayTexts;
 				curBat = rel.currentBatter.liveText;
 				curTxtSize = rel.currentBatterTexts.length;
 				alert(Inn);
 				
 				$("#awaylogo").attr("src","http://imgsports.naver.net/images/emblem/new/kbo/default/"+obj.gameInfo.aCode+".png");
 				$("#homelogo").attr("src","http://imgsports.naver.net/images/emblem/new/kbo/default/"+obj.gameInfo.hCode+".png");
 				$("#awayimg").attr("src","http://www.koreabaseball.com/FILE/person/middle/"+obj.currentPlayers.away.playerInfo.pCode+".jpg");
 				$("#homeimg").attr("src","http://www.koreabaseball.com/FILE/person/middle/"+obj.currentPlayers.home.playerInfo.pCode+".jpg");
 				
				$("#awayPlayer").append("<h4 class='text-center' id=a_name></h4>");
				$("#a_name").text(obj.currentPlayers.away.playerInfo.name +
								"  /  "+ obj.currentPlayers.away.playerInfo.hitType);
				
				$("#homePlayer").append("<h4 class='text-center' id=h_name></h4>");
				$("#h_name").text(obj.currentPlayers.home.playerInfo.name +
								"  /  "+ obj.currentPlayers.home.playerInfo.hitType);
				$("#tbody").append()
				alert(obj.scoreBoard.inn.home.length);
				if(obj.scoreBoard.inn.home.)
				ascoresize = obj.scoreBoard.inn.home.length;
				hscoresize = obj.scoreBoard.inn.home.length;
				for(i=0; i<ascoresize; i++){
					$(score)
				}
                /* <h3 class="text-center">Thumbnail label</h3>
                <p class="text-center">obj.currentPlayers.away.playerInfo.name</p> */
                /* +obj.currentPlayers.away.playerInfo.name +
				"/t"+obj.currentPlayers.away.playerInfo.hitType */
 				//Inn link걸기
 				<%-- href="javascript:runAjax('<%= deptInfo.getDeptno() %>')" --%>
                
                Totaltext();
                //종료된 게임일 경우 바로 서블릿 호출
                //진행중인 게임일 경우 Ajax호출
                if(obj.gameInfo.statusCode==4){ 
                	if(Inn<10){
    		 			for(i=1;i<=curInn;i++){
    		 				$("#page"+i).attr("href","javascript:refresh("+$("#page"+i).text()+")");    		 				
    		 			}
    	 			}else{
    	 				$("#pageext").attr("href","javascript:refresh("+curInn+")");
    	 			}
                }else{      	
                	if(Inn<10){	 				
    		 			for(i=1;i<=curInn;i++){
    		 				inn = $('#page'+i).text();
    		 				$("#page"+i).attr("href","javascript:Ajax('<%=month%>','<%=eventId%>',"+inn+")");		 				
    		 			}
    	 			}else{
    	 				inn = $('#page'+i).text();
    	 				$("#pageext").attr("href","javascript:Ajax('<%=month%>','<%=eventId%>',"+curInn+")");
    	 			}
                }
 				
                
	 			
	 			
	 				 			

    })
	function Ajax(month, eventId, Inn) {
		$(".page").on("click", function(){
			$.get("/kbofantasy/livetext.do",{"month":month,"eventId":eventId,"Inn":Inn,"path":"ajax"},live);
		});
	};
	function live(txt){	//서버와 통신이 성공하면 처리결과를 success에 지정한 함수를 호출하며 넘겨준다.
		kbo = txt;
		Inn = inn;
        obj = JSON.parse(kbo);
        curInn = obj.currentInning;
        rel = obj.relayTexts;
		curBat = rel.currentBatter.liveText;
		curTxtSize = rel.currentBatterTexts.length;
/* 		if(Inn==curInn){
			
		}else{ */
			refresh();
		/* } */
		
		/* $("#live").children().html("get"+txt);	//document.getElementByid().innerhtml = xhr.responseText와 동일 */
	}
    function Totaltext(){
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
	
	function refresh(inn){
		Inn = inn;
		$("#live1").empty();
		Totaltext();
	}
	
</script>
</head>
<body>
	<div class="section">
		<div class="container">
			<div class="row">
				<div class="col-md-12">
					<div class="page-header text-primary">
						<h1>경기결과aaaaaaaaaaaaaaa</h1>
					</div>
				</div>
			</div>
			<div class="row">
				<div class="col-md-2">
					<ol>
						<li>One</li>
						<li>Two</li>
						<li>Three</li>
					</ol>
				</div>
				<div class="col-md-2">
					<ol>
						<li>One</li>
						<li>Two</li>
						<li>Three</li>
					</ol>
				</div>
				<div class="col-md-2">
					<ol>
						<li>One</li>
						<li>Two</li>
						<li>Three</li>
					</ol>
				</div>
				<div class="col-md-2">
					<ol>
						<li>One</li>
						<li>Two</li>
						<li>Three</li>
					</ol>
				</div>
				<div class="col-md-2">
					<ol>
						<li>One</li>
						<li>Two</li>
						<li>Three</li>
					</ol>
				</div>
				<div class="col-md-2">
					<ol>
						<li>One</li>
						<li>Two</li>
						<li>Three</li>
					</ol>
				</div>
			</div>
			<div class="row">
				<div class="col-md-2 text-center">
					<img class="center-block img-responsive" id="awaylogo">
				</div>
				<div class="col-md-1">
					<h1 class="text-center">99</h1>
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
						<tbody id="score">
							<tr>
								<td>LG</td>
								<td>99</td>
								<td>99</td>
								<td>99</td>
								<td>99</td>
								<td>99</td>
								<td>99</td>
								<td>99</td>
								<td>99</td>
								<td>99</td>
								<td>99</td>
								<td>99</td>
								<td>99</td>
								<td>99</td>
								<td>99</td>
								<td>99</td>
								<td>99</td>
							</tr>
							<tr>
								<td>삼성</td>
								<td>99</td>
								<td>99</td>
								<td>99</td>
								<td>99</td>
								<td>99</td>
								<td>99</td>
								<td>99</td>
								<td>99</td>
								<td>99</td>
								<td>99</td>
								<td>99</td>
								<td>99</td>
								<td>99</td>
								<td>99</td>
								<td>99</td>
								<td>99</td>
							</tr>
						</tbody>
					</table>
					<h3 class="text-center">2016.05.14 (토) 15:00, 잠실</h3>
				</div>
				<div class="col-md-1">
					<h1 class="text-center">99</h1>
				</div>
				<div class="col-md-2 text-center">
					<img class="center-block img-responsive" id="homelogo">
				</div>
			</div>
			<div class="row" draggable="true">
				<div class="col-md-3">
					<h1 class="text-left">
						LG 트윈스 <br> <small> <span class="label label-default">1위</span>99승
							99무 99패
						</small>
					</h1>
				</div>
				<div class="col-md-6">
					<h1 class="text-center">4회말 LG 공격</h1>
				</div>
				<div class="col-md-3 text-right">
					<h1 class="text-right">
						삼성 라이온즈 <br> <small>99승 99무 99패 <span
							class="label label-default">2위</span>
						</small>
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
							<div class="col-md-12">
								<h3 class="text-muted">LG 트윈스 타자기록</h3>
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
									<tbody>
										<tr>
											<td>포</td>
											<td>유병수</td>
											<td>유병수</td>
											<td>유병수</td>
											<td>유병수</td>
											<td>유병수</td>
											<td>유병수</td>
											<td>유병수</td>
											<td>유병수</td>
											<td>유병수</td>
											<td>유병수</td>
											<td>유병수</td>
											<td>유병수</td>
										</tr>
									</tbody>
								</table>
								<h3 class="text-muted">LG 트윈스 투수기록</h3>
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
									<tbody>
										<tr>
											<td>유병수</td>
											<td>승</td>
											<td>99</td>
											<td>99</td>
											<td>99</td>
											<td>99</td>
											<td>6 ⅔</td>
											<td>99</td>
											<td>999</td>
											<td>99</td>
											<td>99</td>
											<td>99</td>
										</tr>
									</tbody>
								</table>
							</div>
							<div class="col-md-12">
								<h3 class="text-muted">삼성 라이온즈 타자기록</h3>
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
									<tbody>
										<tr>
											<td>포</td>
											<td>유병수</td>
											<td>유병수</td>
											<td>유병수</td>
											<td>유병수</td>
											<td>유병수</td>
											<td>유병수</td>
											<td>유병수</td>
											<td>유병수</td>
											<td>유병수</td>
											<td>유병수</td>
											<td>유병수</td>
											<td>유병수</td>
										</tr>
									</tbody>
								</table>
								<h3 class="text-muted">삼성 라이온즈 투수기록</h3>
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
									<tbody>
										<tr>
											<td>유병수</td>
											<td>승</td>
											<td>99</td>
											<td>99</td>
											<td>99</td>
											<td>99</td>
											<td>6 ⅔</td>
											<td>99</td>
											<td>999</td>
											<td>99</td>
											<td>99</td>
											<td>99</td>
										</tr>
									</tbody>
								</table>
							</div>
						</div>
					</div>
				</div>
			</div>
</body>
</html>