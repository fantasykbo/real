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
//		String year = (String) request.getAttribute("year");
//		String month = (String) request.getAttribute("month");
		String eventMonthData = (String) request.getAttribute("eventMonthData");
	%>
	$(document).ready(function() {
 		var eventMonthData = '<%= eventMonthData %>';
 		var obj = JSON.parse(eventMonthData);
 		var size = obj.length;
 		for(i=0; i < size; i++) {
 			if(obj[i] != "none") {
 				for(j=0; j < obj[i].games.length; j++) {
					str = "<tr>"
					str += "<td>" + obj[i].games[j].gameId + "</td>";
					str += "<td>" + obj[i].games[j].gdate + "</td>";
					str += "<td>" + obj[i].games[j].gweek + "</td>";
					str += "<td>" + obj[i].games[j].gtime + "</td>";
					str += "<td>" + obj[i].games[j].cancelFlag + "</td>";
					str += "<td>" + obj[i].games[j].statusCode + "</td>";
					str += "<td>" + obj[i].games[j].aCode + "</td>";
					str += "<td>" + obj[i].games[j].hCode + "</td>";
					str += "<td>" + obj[i].games[j].score.aScore + "</td>";
					str += "<td>" + obj[i].games[j].score.hScore + "</td>";
					str += "</tr>"
						$("tbody").append(str);
				}
 			}
 		}
 		
 		
	
	});


</script>
</head>
<body>
	<div class="section">
		<table border=1>
			<thead>
				<tr>
					<th>gameId</th>
					<th>gDate</th>
					<th>gweek</th>
					<th>gtime</th>
					<th>cancelFlag</th>
					<th>statusCode</th>
					<th>aCode</th>
					<th>hCode</th>
					<th>aScore</th>
					<th>hScore</th>
				</tr>
			</thead>
			<tbody>
			</tbody>	
		</table>

	</div>


</body>
</html>