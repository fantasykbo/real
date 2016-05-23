<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
<%@page import="record.dto.EventDTO"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
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
</head>

<body>
	<script type="text/javascript">
  	<%
  	String teamTableData = (String) request.getAttribute("teamTableData");
	%>

	$(document).ready(function() {
 
		// 오늘날짜 호출
		var d = new Date();
		$("#today").text((d.getMonth() + 1) + "월 " + d.getDate() + "일 현재")
		
		// JSON 객체 샘플  : {"teamList":[{"teamName":"LG 트윈스","wra":"0.538","teamCode":"LG","awayLose":10,"totalLose":18,"totalWin":21,"awayWin":8,"awayDraw":0,"homeLose":8,"gap":0,"totalDraw":0,"homeWin":13,"homeDraw":0},
		//                             	  {"teamName":"SK 와이번즈","wra":"0.535","teamCode":"SK","awayLose":11,"totalLose":20,"totalWin":23,"awayWin":8,"awayDraw":0,"homeLose":9,"gap":0,"totalDraw":0,"homeWin":15,"homeDraw":0},
		//								  {"teamName":"kt 위즈","wra":"0.450","teamCode":"KT","awayLose":8,"totalLose":22,"totalWin":18,"awayWin":10,"awayDraw":2,"homeLose":14,"gap":0,"totalDraw":2,"homeWin":8,"homeDraw":0},
		//								  {"teamName":"넥센 히어로즈","wra":"0.512","teamCode":"WO","awayLose":10,"totalLose":20,"totalWin":21,"awayWin":10,"awayDraw":1,"homeLose":10,"gap":0,"totalDraw":1,"homeWin":11,"homeDraw":0},
		//								  {"teamName":"두산 베어스","wra":"0.707","teamCode":"OB","awayLose":6,"totalLose":12,"totalWin":29,"awayWin":16,"awayDraw":0,"homeLose":6,"gap":0,"totalDraw":1,"homeWin":13,"homeDraw":1},
		//								  {"teamName":"한화 이글스","wra":"0.275","teamCode":"HH","awayLose":19,"totalLose":29,"totalWin":11,"awayWin":4,"awayDraw":0,"homeLose":10,"gap":0,"totalDraw":1,"homeWin":7,"homeDraw":1},
		//								  {"teamName":"KIA 타이거즈","wra":"0.475","teamCode":"HT","awayLose":14,"totalLose":21,"totalWin":19,"awayWin":5,"awayDraw":0,"homeLose":7,"gap":0,"totalDraw":0,"homeWin":14,"homeDraw":0},
		//								  {"teamName":"NC 다이노스","wra":"0.564","teamCode":"NC","awayLose":10,"totalLose":17,"totalWin":22,"awayWin":12,"awayDraw":0,"homeLose":7,"gap":0,"totalDraw":1,"homeWin":10,"homeDraw":1},
		//								  {"teamName":"삼성 라이온즈","wra":"0.476","teamCode":"SS","awayLose":11,"totalLose":22,"totalWin":20,"awayWin":9,"awayDraw":0,"homeLose":11,"gap":0,"totalDraw":0,"homeWin":11,"homeDraw":0},
		//								  {"teamName":"롯데 자이언츠","wra":"0.465","teamCode":"LT","awayLose":12,"totalLose":23,"totalWin":20,"awayWin":11,"awayDraw":0,"homeLose":11,"gap":0,"totalDraw":0,"homeWin":9,"homeDraw":0}
		//					]}
		
		// String으로 받아온 JSON 파싱
		var teamTableData = '<%=teamTableData%>';
 		var obj = JSON.parse(teamTableData);
 		var teamList = obj.teamList;
 		
 		// JSON Array를 승률 순으로 sort
 		var sortObj = teamList.sort(function(a, b) {
 		    return parseFloat(b.wra) - parseFloat(a.wra);
 		});

 		// 승률 순으로 sort된 객체 출력
 		var size = sortObj.length;
 		for(i = 0; i < size; i++) {
 			
 			// 게임차 계산
 			var gap = "";
 			if(i == 0) {
 				gap = "-";
 			} else {
 				gap = (((sortObj[0].totalWin - sortObj[i].totalWin)
 						+ (sortObj[i].totalLose - sortObj[0].totalLose)) / 2);
 				// 소숫점 자릿수를 한자리로 고정
 				gap = gap.toFixed(1)
 			}
 			var teamTable = "<tr>"
 						  + "<td>" + (i + 1) + "</td>"
 						  + "<td>" + "<img src=\"/kbofantasy/images/icon/" + sortObj[i].teamCode + ".png\"/>" + sortObj[i].teamName + "</td>"
 						  + "<td>" + (sortObj[i].totalWin + sortObj[i].totalDraw + sortObj[i].totalLose) + "</td>"	// 승+무+패 합으로 경기 수 계산
						  + "<td>" + sortObj[i].totalWin + "</td>"
						  + "<td>" + sortObj[i].totalDraw + "</td>"
						  + "<td>" + sortObj[i].totalLose + "</td>"
						  + "<td>" + sortObj[i].wra + "</td>"
						  + "<td>" + gap + "</td>"
						  + "<td>" + sortObj[i].homeWin + "승 " + sortObj[i].homeDraw + "무 " + sortObj[i].homeLose + "패" + "</td>"
						  + "<td>" + sortObj[i].awayWin + "승 " + sortObj[i].awayDraw + "무 " + sortObj[i].awayLose + "패" + "</td>"
						  + "</td>";
			$("#team-table").append(teamTable);	

 		}
	
	});
	</script>
	<div class="section">
		<div class="container">
			<div class="row">
				<div class="col-md-12">
					<div class="page-header text-primary">
						<h1>
							KBO리그 팀 순위&nbsp; <small id="today"></small>
						</h1>
					</div>
				</div>
			</div>
			<div class="row">
				<div class="col-md-12">
					<table class="table table-striped">
						<tbody id="team-table">
						</tbody>
						<thead>
							<tr>
								<th class="info">#</th>
								<th class="info">팀명</th>
								<th class="info">경기</th>
								<th class="info">승</th>
								<th class="info">무</th>
								<th class="info">패</th>
								<th class="info">승률</th>
								<th class="info">게임차</th>
								<th class="info">홈 전적</th>
								<th class="info">원정 전적</th>
							</tr>
						</thead>
					</table>
				</div>
			</div>
		</div>
	</div>
</body>

</html>