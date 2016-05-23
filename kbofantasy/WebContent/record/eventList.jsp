<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
<%@page import="record.dto.EventDTO"%>
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
		ArrayList<EventDTO> eventList = (ArrayList<EventDTO>) request.getAttribute("eventList");
		String year = (String) request.getAttribute("year");
		String month = (String) request.getAttribute("month");
		
		String eventTodayData = (String) request.getAttribute("eventTodayData");

		int size = eventList.size();
		
		// 현재시간 구하기
		SimpleDateFormat transFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		long nowDate = System.currentTimeMillis();
		String[] weekDay = {"일", "월", "화", "수", "목", "금", "토"};
		
		%>
	$(document).ready(function() {
 		var eventTodayData = '<%= eventTodayData %>';
 		// service에서 IOException 처리 하여 파싱에 실패할 경우 "" 값 전송
 		if(eventTodayData != "") {
	 		var obj = JSON.parse(eventTodayData);
	 
			// 오늘자 실시간 스코어 받아오기
	 		var size = obj.games.length;
	 		for(i = 0; i < size; i++) {
				switch(obj.games[i].statusCode) {
					case "0" :	// 경기 시작 전 : 중계 정보 없음
						break;
					case "1" :	// 경기 시작 직전 : 문자 중계 할 준비가 되어 있는 상황
						$("#" + obj.games[i].gameId + " #event-btn").empty();
						$("#" + obj.games[i].gameId + " #event-btn").append("<a class=\"active btn btn-danger btn-sm\" href=\"/kbofantasy/livetext.do?eventId=" + obj.games[i].gameId + "&month=<%= month %>&pathurl=/liveText/Parse_nsd.jsp\">문자중계</a>&nbsp;&nbsp;");
			 			$("#" + obj.games[i].gameId + " #event-btn").append("<a class=\"btn btn-warning btn-sm disabled\" href=\"eventRecord.do?eventId=" + obj.games[i].gameId + "&pathurl=/record/eventRecord.jsp\">경기기록</a>");
						break;
						
					case "2" :	// 경기 중 : 문자중계 중
					case "3" :	// 경기 종료 직후 : 문자중계는 종료되었으나 아직 경기기록이 정리되지 않은 상태
						$("#" + obj.games[i].gameId + " #event-btn").empty();
						$("#" + obj.games[i].gameId + " #event-btn").append("<a class=\"active btn btn-danger btn-sm\" href=\"/kbofantasy/livetext.do?eventId=" + obj.games[i].gameId + "&month=<%= month %>&pathurl=/liveText/Parse_nsd.jsp\">문자중계</a>&nbsp;&nbsp;");
			 			$("#" + obj.games[i].gameId + " #event-btn").append("<a class=\"btn btn-warning btn-sm disabled\" href=\"eventRecord.do?eventId=" + obj.games[i].gameId + "&pathurl=/record/eventRecord.jsp\">경기기록</a>");
			 			$("#" + obj.games[i].gameId + " #event-score").text(obj.games[i].score.aScore + " (" + obj.games[i].inn + ") " + obj.games[i].score.hScore);
					break;
	
					case "4" :	// 경기 종료 : 경기기록 JSON 데이터를 받아 올 수 있는 상태
						$("#" + obj.games[i].gameId + " #event-btn").empty();
						$("#" + obj.games[i].gameId + " #event-btn").append("<a class=\"btn btn-primary btn-sm\" href=\"/kbofantasy/livetext.do?eventId=" + obj.games[i].gameId + "&month=<%= month %>&pathurl=/liveText/Parse_nsd.jsp\">문자중계</a>&nbsp;&nbsp;");
			 			$("#" + obj.games[i].gameId + " #event-btn").append("<a class=\"btn btn-warning btn-sm\" href=\"eventRecord.do?eventId=" + obj.games[i].gameId + "&pathurl=/record/eventRecord.jsp\">경기기록</a>");
			 			$("#" + obj.games[i].gameId + " #event-score").text(obj.games[i].score.aScore + " (종료) " + obj.games[i].score.hScore);
			 			break;
				}
	 		}
 		}
 		// 이전달, 다음달 링크 생성
 		var year = <%= year %>
 		var month = <%= month %>
 		var prevMonth = month - 1;
 		var nextMonth = month + 1;
		
 		// 월 변경 좌우 버튼 컨트롤
 		if(prevMonth >= 4) { 	// 4월 이전엔 경기가 없음
 			$("#prevMonth").attr("href", "/kbofantasy/record/eventList.do?year=" + year + "&month=" + "0" + prevMonth + "&pathurl=/record/eventList.jsp");
 		} else {
 			$("#prevMonth").attr("href", "javascript:alert('이전 경기가 없습니다.')");
 		}
 		if(nextMonth <= 9) {	// 10월 이후엔 경기가 없음
			$("#nextMonth").attr("href", "/kbofantasy/record/eventList.do?year=" + year + "&month=" + "0" + nextMonth + "&pathurl=/record/eventList.jsp");
 		} else {
 			$("#nextMonth").attr("href", "javascript:alert('다음 경기가 없습니다.')");
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
						<li><a id="prevMonth" href="#">← Prev</a></li>
						<span><%= year %>. <%= month %></span>
						<li><a id="nextMonth" href="#">Next →</a></li>

					</ul>
					<table class="table table-bordered table-striped text-center">
						<tbody>

						<% for(int i = 0; i < size; i++) { 
								EventDTO dto = eventList.get(i);
								Date d = transFormat.parse(dto.getEventDate());
								long eventDate = d.getTime();
								
								// 경기가 없는 날 TR 생성
								String emptyMonth = "";
								String emptyDate = "";
								String emptyDay = "";
								if(i != 0 && transFormat.parse(eventList.get(i-1).getEventDate()).getDate() <= (d.getDate() - 2)) {
									if((d.getMonth() + 1) >= 10) {
										emptyMonth = "" + (d.getMonth() + 1);
									} else {
										emptyMonth = "0" + (d.getMonth() + 1);
									}
									if((d.getDate() - 1) >= 10) {
										emptyDate = "" + (d.getDate() -1);
									} else {
										emptyDate = "0" + (d.getDate() -1);
									}
									emptyDay = weekDay[(d.getDay() - 1)];
						%>
									<tr>
										<td><%= emptyMonth + "." + emptyDate + "(" + emptyDay + ")" %></td>
										<td colspan="6">경기가 없는 날입니다.  오늘만이라도 일상을 돌아보세요.</td></tr>
								<% } %>

							<tr id="<%= dto.getEventCode() %>">
								<td class="event-date"><%= dto.getsEventDate() %></td>
								<td><%= dto.getEventDate().substring(11, 16) %></td>
								<td><img src="/kbofantasy/images/icon/<%= dto.getaTeamCode() %>.png"/><%= dto.getaTeamSName() %></td>
								<td id="event-score">
									// 경기 상태가 4 : 종료이면 스코어 표출
									<% if(dto.getEventStatus().equals("4")) { %>
										<%= dto.getaScore() %> : <%= dto.gethScore() %>
									<% } else { %>
										vs
									<% } %>
								</td>
								<td><%= dto.gethTeamSName() %><img src="/kbofantasy/images/icon/<%= dto.gethTeamCode() %>.png"/></td>
								<td><%= dto.getStadium() %></td>
								<td id="event-btn">
									// 경기 취소 flag가 Y : 취소된 경기일 경우 취소 표시 
									<% if(dto.getCancelFlag().equals("Y")) { %>
										<span>해당 경기는 현지 사정에 의해 취소되었습니다.</span>
									// 취소된 경기가 아니고 종료된 경기일 경우 버튼 활성화
									<% } else if(dto.getEventStatus().equals("4")){ %>
										<a class="btn btn-primary btn-sm" href="/kbofantasy/livetext.do?eventId=<%= dto.getEventCode() %>&month=<%= month %>&pathurl=/liveText/Parse_nsd.jsp">문자중계</a>
										<a class="btn btn-warning btn-sm" href="eventRecord.do?eventId=<%= dto.getEventCode() %>&pathurl=/record/eventRecord.jsp">경기기록</a>
									// 종료되지 않은 경우 버튼 비활성화
									<% } else { %>
										<a class="btn btn-primary btn-sm disabled" href="/kbofantasy/livetext.do?eventId=<%= dto.getEventCode() %>&month=<%= month %>&pathurl=/liveText/Parse_nsd.jsp">문자중계</a>
										<a class="btn btn-warning btn-sm disabled" href="eventRecord.do?eventId=<%= dto.getEventCode() %>&pathurl=/record/eventRecord.jsp">경기기록</a>
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