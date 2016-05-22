<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
<%@page import="record.dto.EventDTO"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
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
 
		var d = new Date();
		$("#today").text((d.getMonth() + 1) + "월 " + d.getDate() + "일 현재")
		
		var teamTableData = '<%=teamTableData%>';
 		var obj = JSON.parse(teamTableData);
 		var teamList = obj.teamList;
 		
 		// JSON Array 승률 순으로 sort
 		var sortObj = teamList.sort(function(a, b) {
 		    return parseFloat(b.wra) - parseFloat(a.wra);
 		});

 		var size = sortObj.length;
 		for(i = 0; i < size; i++) {
 			
 			// 게임차 계산
 			var gap = "";
 			if(i == 0) {
 				gap = "-";
 			} else {
 				gap = (((sortObj[0].totalWin - sortObj[i].totalWin)
 						+ (sortObj[i].totalLose - sortObj[0].totalLose)) / 2);
 				gap = gap.toFixed(1)
 			}
 			
 			var teamTable = "<tr>"
 						  + "<td>" + (i + 1) + "</td>"
 						  + "<td>" + "<img src=\"/kbofantasy/images/icon/" + sortObj[i].teamCode + ".png\"/>" + sortObj[i].teamName + "</td>"
 						  + "<td>" + (sortObj[i].totalWin + sortObj[i].totalDraw + sortObj[i].totalLose) + "</td>"
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