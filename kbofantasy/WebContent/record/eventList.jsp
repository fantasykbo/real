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
		
		// ����ð� ���ϱ�
		SimpleDateFormat transFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		long nowDate = System.currentTimeMillis();
		String[] weekDay = {"��", "��", "ȭ", "��", "��", "��", "��"};
		
		%>
	$(document).ready(function() {
 		var eventTodayData = '<%= eventTodayData %>';
 		var obj = JSON.parse(eventTodayData);
 
		// ������ �ǽð� ���ھ� �޾ƿ���
 		var size = obj.games.length;
 		for(i = 0; i < size; i++) {
			switch(obj.games[i].statusCode) {
				case "1" :
					$("#" + obj.games[i].gameId + " #event-btn").empty();
					$("#" + obj.games[i].gameId + " #event-btn").append("<a class=\"active btn btn-danger btn-sm\" href=\"/kbofantasy/livetext.do?eventId=" + obj.games[i].gameId + "&month=<%= month %>&pathurl=/liveText/Parse_nsd.jsp\">�����߰�</a>&nbsp;&nbsp;");
		 			$("#" + obj.games[i].gameId + " #event-btn").append("<a class=\"btn btn-warning btn-sm disabled\" href=\"eventRecord.do?eventId=" + obj.games[i].gameId + "&pathurl=/record/eventRecord.jsp\">�����</a>");
					break;
					
				case "2" :
				case "3" :
					$("#" + obj.games[i].gameId + " #event-btn").empty();
					$("#" + obj.games[i].gameId + " #event-btn").append("<a class=\"active btn btn-danger btn-sm\" href=\"/kbofantasy/livetext.do?eventId=" + obj.games[i].gameId + "&month=<%= month %>&pathurl=/liveText/Parse_nsd.jsp\">�����߰�</a>&nbsp;&nbsp;");
		 			$("#" + obj.games[i].gameId + " #event-btn").append("<a class=\"btn btn-warning btn-sm disabled\" href=\"eventRecord.do?eventId=" + obj.games[i].gameId + "&pathurl=/record/eventRecord.jsp\">�����</a>");
		 			$("#" + obj.games[i].gameId + " #event-score").text(obj.games[i].score.aScore + " (" + obj.games[i].inn + ") " + obj.games[i].score.hScore);
				break;

				case "4" :
					$("#" + obj.games[i].gameId + " #event-btn").empty();
					$("#" + obj.games[i].gameId + " #event-btn").append("<a class=\"btn btn-primary btn-sm\" href=\"/kbofantasy/livetext.do?eventId=" + obj.games[i].gameId + "&month=<%= month %>&pathurl=/liveText/Parse_nsd.jsp\">�����߰�</a>&nbsp;&nbsp;");
		 			$("#" + obj.games[i].gameId + " #event-btn").append("<a class=\"btn btn-warning btn-sm\" href=\"eventRecord.do?eventId=" + obj.games[i].gameId + "&pathurl=/record/eventRecord.jsp\">�����</a>");
		 			$("#" + obj.games[i].gameId + " #event-score").text(obj.games[i].score.aScore + " (����) " + obj.games[i].score.hScore);
		 			break;
					
			}
 		}

 		// ������, ������ ��ũ ����
 		var year = <%= year %>
 		var month = <%= month %>
 		var prevMonth = month - 1;
 		var nextMonth = month + 1;
		
 		if(prevMonth >= 4) {
 			$("#prevMonth").attr("href", "/kbofantasy/record/eventList.do?year=" + year + "&month=" + "0" + prevMonth + "&pathurl=/record/eventList.jsp");
 		} else {
 			$("#prevMonth").attr("href", "javascript:alert('���� ��Ⱑ �����ϴ�.')");
 		}
 		if(nextMonth <= 9) {
			$("#nextMonth").attr("href", "/kbofantasy/record/eventList.do?year=" + year + "&month=" + "0" + nextMonth + "&pathurl=/record/eventList.jsp");
 		} else {
 			$("#nextMonth").attr("href", "javascript:alert('���� ��Ⱑ �����ϴ�.')");
 		}		
 		
		// ���� ��¥ TD ��ġ��
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
						<h1>������� / ���</h1>
					</div>
				</div>
			</div>
			<div class="row">
				<div class="col-md-12">
					<ul class="lead pager">
						<li><a id="prevMonth" href="#">�� Prev</a></li>
						<span><%= year %>. <%= month %></span>
						<li><a id="nextMonth" href="#">Next ��</a></li>

					</ul>
					<table class="table table-bordered table-striped text-center">
						<tbody>

						<% for(int i = 0; i < size; i++) { 
								EventDTO dto = eventList.get(i);
								Date d = transFormat.parse(dto.getEventDate());
								long eventDate = d.getTime();
								
								// ��Ⱑ ���� �� TR ����
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
										<td colspan="6">��Ⱑ �����ϴ�.</td></tr>
								<% } %>

							<tr id="<%= dto.getEventCode() %>">
								<td class="event-date"><%= dto.getsEventDate() %></td>
								<td><%= dto.getEventDate().substring(11, 16) %></td>
								<td><img src="/kbofantasy/images/icon/<%= dto.getaTeamCode() %>.png"/><%= dto.getaTeamSName() %></td>
								<td id="event-score">
									<% if(dto.getEventStatus().equals("4")) { %>
										<%= dto.getaScore() %> : <%= dto.gethScore() %>
									<% } else { %>
										vs
									<% } %>
								</td>
								<td><%= dto.gethTeamSName() %><img src="/kbofantasy/images/icon/<%= dto.gethTeamCode() %>.png"/></td>
								<td><%= dto.getStadium() %></td>
								<td id="event-btn">
									<% if(dto.getCancelFlag().equals("Y")) { %>
										<span>�ش� ���� ���� ������ ���� ��ҵǾ����ϴ�.</span>
									<% } else if(dto.getEventStatus().equals("4")){ %>
										<a class="btn btn-primary btn-sm" href="/kbofantasy/livetext.do?eventId=<%= dto.getEventCode() %>&month=<%= month %>&pathurl=/liveText/Parse_nsd.jsp">�����߰�</a>
										<a class="btn btn-warning btn-sm" href="eventRecord.do?eventId=<%= dto.getEventCode() %>&pathurl=/record/eventRecord.jsp">�����</a>
									<% } else { %>
										<a class="btn btn-primary btn-sm disabled" href="/kbofantasy/livetext.do?eventId=<%= dto.getEventCode() %>&month=<%= month %>&pathurl=/liveText/Parse_nsd.jsp">�����߰�</a>
										<a class="btn btn-warning btn-sm disabled" href="eventRecord.do?eventId=<%= dto.getEventCode() %>&pathurl=/record/eventRecord.jsp">�����</a>
									<% } %>
								</td>
							</tr>
							<% } %>
						</tbody>
						<thead>
							<tr>
								<th class="text-center">��¥</th>
								<th class="text-center">�ð�</th>
								<th class="text-center" colspan="3">���</th>
								<th class="text-center">����</th>
								<th class="text-center">�߰�/���</th>

							</tr>
						</thead>
					</table>
				</div>
			</div>
		</div>
	</div>


</body>
</html>