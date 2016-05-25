<%@page import="java.util.Collections"%>
<%@page import="record.dto.PitcherDTO"%>
<%@page import="record.dto.BatterDTO"%>
<%@page import="record.dto.PlayerDTO"%>
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
  	BatterDTO batterStat = null;
  	ArrayList<BatterDTO> batterLastStatList = null;
  	PitcherDTO pitcherStat = null;
  	ArrayList<PitcherDTO> pitcherLastStatList = null;
	ArrayList<String> statHeader = new ArrayList<String>();
	ArrayList<String> lastHeader = new ArrayList<String>();
	PlayerDTO info = (PlayerDTO) request.getAttribute("playerInfoData");
	// ���� �������� �������� P, ������ ��� 
	Boolean isP = info.getPositionDetail().equals("P");

	// ������̺��� �������� ����
  	if(isP) {
  		pitcherStat = (PitcherDTO) request.getAttribute("pitcherStatData");
	 	pitcherLastStatList = (ArrayList<PitcherDTO>) request.getAttribute("pitcherLastStatList");
		String[] statH = {"����Ʈ", "���", "��", "��", "Ȧ", "��", "�����", "�̴�", "Ÿ��", "������", "Ż����", "�Ǿ�Ÿ", "��Ȩ��", "4�籸", "����", "��å��"};
		String[] lastH = {"��¥", "����Ʈ", "���", "�����", "�̴�", "Ÿ��", "������", "Ż����", "�Ǿ�Ÿ", "��Ȩ��", "4�籸", "����", "��å��"};
		Collections.addAll(statHeader, statH);
		Collections.addAll(lastHeader, lastH);
	// Ÿ�ڸ��
  	} else {
	 	batterStat = (BatterDTO) request.getAttribute("batterStatData");
	 	batterLastStatList = (ArrayList<BatterDTO>) request.getAttribute("batterLastStatList");
		String[] statH = {"����Ʈ", "���", "Ÿ��", "�����", "��Ÿ��", "OPS", "Ÿ��", "1��Ÿ", "2��Ÿ", "3��Ÿ", "Ȩ��", "����", "Ÿ��", "4�籸", "����4��", "���Ÿ", "�ƿ�", "����", "����Ÿ"};
		String[] lastH = {"��¥", "����Ʈ", "Ÿ��", "Ÿ��", "1��Ÿ", "2��Ÿ", "3��Ÿ", "Ȩ��", "����", "Ÿ��", "4�籸", "����4��", "���Ÿ", "�ƿ�", "����", "����Ÿ"};

		Collections.addAll(statHeader, statH);
		Collections.addAll(lastHeader, lastH);
  	}
	%>

	$(document).ready(function() {;
	});
	</script>
	<div class="section">
		<div class="container">
			<div class="row">
				<div class="col-md-12">
					<div class="page-header text-primary">
						<h1>���� ����</h1>
					</div>
				</div>
			</div>
			<div class="row">
				<div class="col-md-12">
					<h3>
						<img src="/kbofantasy/images/icon/<%=info.getTeamCode()%>.png" />
						<%="No." + info.getBackNo() + " " + info.getPlayerName() + " "%>
						<small><%=info.getTeamName()%></small> <span
							class="label label-default"><%=info.getPointSum()%>p</span>
					</h3>
				</div>
			</div>
			<div class="row">
				<div class="col-md-2">
					<img
						src="http://www.koreabaseball.com/FILE/person/middle/<%=info.getPlayerCode()%>.jpg"
						class="center-block img-responsive img-thumbnail">
				</div>
				<div class="col-md-10">
					<table class="table table-condensed">
						<tbody>
							<tr>
								<td class="info text-right">������</td>
								<td><%=info.getPlayerName()%></td>
								<td class="info text-right">���ȣ</td>
								<td><%=info.getBackNo()%></td>
							</tr>
							<tr>
								<td class="info text-right">�Ҽ���</td>
								<td><%=info.getTeamName()%></td>
								<td class="info text-right">������</td>
								<td><%=info.getPositionName() + "(" + info.getPositionDetail() + ") / " + info.getHand()%></td>
							</tr>
							<tr>
								<td class="info text-right">�������</td>
								<td><%=info.getBirth()%></td>
								<td class="info text-right">����</td>
								<td><%=info.getSalary()%></td>
							</tr>
							<tr>
								<td class="info text-right">���</td>
								<td colspan="3"><%=info.getSchool()%></td>
							</tr>
						</tbody>
					</table>
				</div>
			</div>
			<div class="row">
				<div class="col-md-12">
					<h3>
						<i class="fa fa-fw fa-asterisk"></i>2016 ����
					</h3>
				</div>
			</div>
			<div class="row">
				<div class="col-md-12">
					<table class="table">
						<thead>
							<tr>
							<%	int ssize = statHeader.size();
								for(int i = 0; i < ssize; i++) { %>
								<th><%= statHeader.get(i) %></th>
							<% } %>
							</tr>
						</thead>
						<tbody>
							<tr>
							<% if(isP) { %>
								<td><%= pitcherStat.getPoint() %>
								<td><%= pitcherStat.getPl() %>
								<td><% if(pitcherStat.getWin() == null) { %>
									-
									<% } else { %>
										<%= pitcherStat.getWin() %>
									<% } %></td>
								<td><% if(pitcherStat.getLose() == null) { %>
									-
									<% } else { %>
										<%= pitcherStat.getLose() %>
									<% } %></td>
								<td><% if(pitcherStat.getHold() == null) { %>
									-
									<% } else { %>
										<%= pitcherStat.getHold() %>
									<% } %></td>
								<td><% if(pitcherStat.getSave() == null) { %>
									-
									<% } else { %>
										<%= pitcherStat.getSave() %>
									<% } %></td>
								<td><%= pitcherStat.getEra() %>
								<td><%= pitcherStat.getInn()/3 + "." + pitcherStat.getInn()%3 %></td>
								<td><%= pitcherStat.getPa() %>
								<td><%= pitcherStat.getBf() %>
								<td><%= pitcherStat.getKk() %>
								<td><%= pitcherStat.getHt() %>
								<td><%= pitcherStat.getHr() %>
								<td><%= pitcherStat.getBb() %>
								<td><%= pitcherStat.getRs() %>
								<td><%= pitcherStat.getEr() %>
							<% } else{ %>
								<td><%= batterStat.getPoint() %></td>
								<td><%= batterStat.getPl() %></td>
								<td><%= batterStat.getHra() %></td>
								<td><%= batterStat.getObp() %></td>
								<td><%= batterStat.getSlg() %></td>
								<td><%= batterStat.getOps() %></td>
								<td><%= batterStat.getPa() %></td>
								<td><%= batterStat.getH1() %></td>
								<td><%= batterStat.getH2() %></td>
								<td><%= batterStat.getH3() %></td>
								<td><%= batterStat.getHr() %></td>
								<td><%= batterStat.getRs() %></td>
								<td><%= batterStat.getRb() %></td>
								<td><%= batterStat.getBb() %></td>
								<td><%= batterStat.getIb() %></td>
								<td><%= batterStat.getSh() %></td>
								<td><%= batterStat.getOt() %></td>
								<td><%= batterStat.getSo() %></td>
								<td><%= batterStat.getDp() %></td>
							<% } %>
							</tr>
						</tbody>
					</table>
				</div>
			</div>
			<div class="row">
				<div class="col-md-12">
					<h3>
						<i class="fa fa-fw fa-clock-o"></i>�ֱ� 10��� ����
					</h3>
				</div>
			</div>
			<div class="row">
				<div class="col-md-12">
					<table class="table">
						<thead>
							<tr>
							<%	int lsize = lastHeader.size();
								for(int i = 0; i < lsize; i++) { %>
								<th><%= lastHeader.get(i) %></th>
							<% } %>
							</tr>
						</thead>
						<tbody>
							<% if(isP) {
								int size = pitcherLastStatList.size();
								for(int i = 0; i < size; i++) {
									PitcherDTO last = pitcherLastStatList.get(i);
									%>
							<tr>
								<td><%= last.getEventCode().substring(0, 8) %></td>
								<td><%= last.getPoint() %></td>
								<td><% if(last.getWlhs() == null) { %>
									-
									<% } else { %>
									<%= last.getWlhs() %>
									<% } %>
								</td>
								<td><%= last.getEra() %></td>
								<td><%= last.getInn()/3 + "." + last.getInn()%3 %></td>
								<td><%= last.getPa() %></td>
								<td><%= last.getBf() %></td>
								<td><%= last.getKk() %></td>
								<td><%= last.getHt() %></td>
								<td><%= last.getHr() %></td>
								<td><%= last.getBb() %></td>
								<td><%= last.getRs() %></td>
								<td><%= last.getEr() %></td>
							</tr>
							<%	 }
								} else {
									int size = batterLastStatList.size();
									for(int i = 0; i < size; i++) {
										BatterDTO last = batterLastStatList.get(i);
										%>
							<tr>
								<td><%= last.getEventCode().substring(0, 8) %></td>
								<td><%= last.getPoint() %></td>
								<td><%= last.getHra() %></td>
								<td><%= last.getPa() %></td>
								<td><%= last.getH1() %></td>
								<td><%= last.getH2() %></td>
								<td><%= last.getH3() %></td>
								<td><%= last.getHr() %></td>
								<td><%= last.getRs() %></td>
								<td><%= last.getRb() %></td>
								<td><%= last.getBb() %></td>
								<td><%= last.getIb() %></td>
								<td><%= last.getSh() %></td>
								<td><%= last.getOt() %></td>
								<td><%= last.getSo() %></td>
								<td><%= last.getDp() %></td>
							</tr>
						<% } %>
						<% } %>
						</tbody>
					</table>
				</div>
			</div>
		</div>
	</div>
</body>

</html>