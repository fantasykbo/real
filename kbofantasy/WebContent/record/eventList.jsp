<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
<%@page import="record.dto.RecordDTO"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>

<html>
<head>
<meta charset="EUC-KR">
<meta name="viewport" content="width=device-width, initial-scale=1">
<script type="text/javascript" src="http://cdnjs.cloudflare.com/ajax/libs/jquery/2.0.3/jquery.min.js"></script>
<script type="text/javascript" src="http://netdna.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>
<link href="http://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.3.0/css/font-awesome.min.css" rel="stylesheet" type="text/css">
<link href="http://pingendo.github.io/pingendo-bootstrap/themes/default/bootstrap.css" rel="stylesheet" type="text/css">
<script type="text/javascript">
	<%
		ArrayList<RecordDTO> eventList = (ArrayList<RecordDTO>) request.getAttribute("eventList");
		String year = (String) request.getAttribute("year");
		String month = (String) request.getAttribute("month");
		String eventTodayData = (String) request.getAttribute("eventTodayData");

		int size = eventList.size();
		// 현재시간 구하기
		SimpleDateFormat transFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		long nowDate = System.currentTimeMillis(); 
		
		%>
	$(document).ready(function() {
 		var eventTodayData = '<%= eventTodayData %>';
 		var obj = JSON.parse(eventTodayData);
 		var size = obj.games.length;
 
 		for(i = 0; i < size; i++) {
			if(parseInt(obj.games[i].statusCode) >= 2) {
	 			$("#" + obj.games[i].gameId + " #event-score").text(obj.games[i].score.aScore + " : " + obj.games[i].score.hScore);
			}
		}

		// 같은 날짜 TD 합치기
		$(".event-date").each(function () {
		    var rows = $(".event-date:contains('" + $(this).text() + "')");
		    if(rows.length > 1) {
				rows.eq(0).attr("rowspan", rows.length);
				rows.not(":eq(0)").remove(); 
		    }
		});
	
	});

</script>
</head>
<body>
	<div class="section">
		<div class="container">
			<div class="row">
				<div class="col-md-12">
					<div class="page-header text-primary">
						<h1>경기일정 / 결과</h1>
					</div>
				</div>
			</div>
			<div class="row">
				<div class="col-md-12">
					<ul class="lead pager">
						<li><a href="#">← Prev</a></li>
						<span><%= year %>. <%= month %></span>
						<li><a href="#">Next →</a></li>
					</ul>
					<table class="table table-bordered table-striped text-center">
						<tbody>

						<% for(int i = 0; i < size; i++) { 
								RecordDTO dto = eventList.get(i);
								Date d = transFormat.parse(dto.getEventDate());
								long eventDate = d.getTime();
						%>

							<tr id="<%= dto.getEventCode() %>">
								<td class="event-date"><%= dto.getsEventDate() %></td>
								<td><%= dto.getEventDate().substring(11, 16) %></td>
								<td><img src="/kbofantasy/images/icon/<%= dto.getaTeamCode() %>.png"/><%= dto.getaTeamSName() %></td>
								<td id="event-score">
									<% if(dto.getEventStatus() != null && dto.getEventStatus().equals("4")) { %>
										<%= dto.getaScore() %> : <%= dto.gethScore() %>
									<% } else { %>
										vs
									<% } %>
								</td>
								<td><%= dto.gethTeamSName() %><img src="/kbofantasy/images/icon/<%= dto.gethTeamCode() %>.png"/></td>
								<td><%= dto.getStadium() %></td>
								<td>
									<% if (eventDate <= nowDate) { %>
										<a class="btn btn-primary" href="eventRecord.do?eventId=<%= dto.getEventCode() %>">라이브스코어</a>
										<a class="btn btn-warning" href="eventRecord.do?eventId=<%= dto.getEventCode() %>&pathurl=record/eventRecord.jsp">경기결과</a>
									<% } %>
								</td>
							</tr>
							<% } %>
						</tbody>
						<thead>
							<tr>
								<th class="text-center">날짜</th>
								<th class="text-center">시간</th>
								<th class="text-center" colspan="3">경기</th>
								<th class="text-center">구장</th>
								<th class="text-center">중계/기록</th>

							</tr>
						</thead>
					</table>
				</div>
			</div>
		</div>
	</div>


</body>
</html>