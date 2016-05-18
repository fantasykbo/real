<%@page import="java.util.ArrayList"%>
<%@page import="real.dto.*"%>
<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
	<meta charset="utf-8">
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
		
	<% 
		ArrayList<NewsDTO> news = (ArrayList<NewsDTO>)request.getAttribute("news");
		String teamname= (String)request.getParameter("teamname");

		int size = news.size();
		int view = 0;
	%>
	
	<script type="text/javascript">
	$(document).ready(function() {
		end = 0;
		count = 0;
		$("#addcontent").on("click",function() {
				count = count+1;
				end = count*10;
				getNews(end);

			
		});
		function getNews(end) {
			s = end.toString();
			teamname='<%=teamname%>';

			var xhr = new XMLHttpRequest();
			mydiv = document.getElementById("content");
			xhr.onreadystatechange = function() {
				if (xhr.readyState == 4 && xhr.status == 200) {
					mydiv.innerHTML = xhr.responseText;
				}
			}
			xhr.open("GET", "/kbofantasy/ajaxnewssearch.do?end="+s+"&teamname="+teamname, true);
			xhr.send();

		}

	});
</script>
	</head>
	<body>
		<div class="section">
			<div class="container">
				<div class="row">
					<div class="col-md-12">
						<div class="page-header text-primary">
							<h1>
								�������� <small><%=teamname %></small>
							</h1>
						</div>
					</div>
				</div>
				<div class="row">
					<div class="col-md-10">
						<ul id="content" class="media-list">
						<%for(int i=0; i<10;i++){
							NewsDTO obj = news.get(i);
							String[] pubdate = obj.getPubdate();%>
							<li class="media"><a href="#" class="pull-left"><img
									class="media-object"
									src="http://pingendo.github.io/pingendo-bootstrap/assets/placeholder.png"
									height="64" width="64"></a>
								<div class="media-body">
									<h4 class="media-heading">
										<a href="<%=obj.getLink()%>"><%=obj.getTitle()%></a>&nbsp; <small><%=pubdate[0]%>�� <%=pubdate[1]%>�� <%=pubdate[2] %>��</small>
									</h4>
									<p><%=obj.getDescription() %></p>
								</div></li>
							<%} %>
							<div id="content"></div>
						</ul>
						<div class="row">
							<div class="col-md-12 text-center">
								<ul class="pagination">
									<li id="addcontent">������</li>
								</ul>
							</div>
						</div>
					</div>
					<div class="col-md-2">
						<ul class="list-group text-center">
							<li class="list-group-item">
								<a href="/kbofantasy/newssearch.do?teamname=kbo����">��ü����</a></li>
							<li class="list-group-item">
								<a href="/kbofantasy/newssearch.do?teamname=�λ꺣�">�λ� ���</a></li>
				
							<li class="list-group-item">
								<a href="/kbofantasy/newssearch.do?teamname=�Ｚ���̿���">�Ｚ ���̿���</a></li>
						
							<li class="list-group-item">
								<a href="/kbofantasy/newssearch.do?teamname=NC���̳뽺">NC ���̳뽺</a></li>
							<li class="list-group-item">
							<a href="/kbofantasy/newssearch.do?teamname=�ؼ��������">�ؼ� �������</a></li>
							<li class="list-group-item">
							<a href="/kbofantasy/newssearch.do?teamname=SK���̹���">SK ���̹���</a></li>
							<li class="list-group-item">
							<a href="/kbofantasy/newssearch.do?teamname=��ȭ�̱۽�">��ȭ �̱۽�</a></li>
							<li class="list-group-item">
							<a href="/kbofantasy/newssearch.do?teamname=KIAŸ�̰���">KIA Ÿ�̰���</a></li>
							<li class="list-group-item">
							<a href="/kbofantasy/newssearch.do?teamname=�Ե����̾���">�Ե� ���̾���</a></li>
							<li class="list-group-item">
							<a href="/kbofantasy/newssearch.do?teamname=LGƮ����">LG Ʈ����</a></li>
							<li class="list-group-item">
							<a href="/kbofantasy/newssearch.do?teamname=kt����">kt ����</a></li>
						</ul>
					</div>
				</div>
			</div>
		</div>
	
	
	</body>
</html>