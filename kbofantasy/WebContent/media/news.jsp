<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
	<head>
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
		<style type="text/css">
			a:HOVER{
				cursor: pointer;
			}
			.list-group-item:HOVER {
				background-color: #337cbb;
				cursor: pointer;	
			}
			#addcontent{
				border: solid 1px #337cbb;
				padding-top: 10px;
				padding-bottom: 10px;
				padding-left: 50px;
				padding-right: 50px;
				cursor: pointer;
			}
		</style>
		
	</head>
	<body>
	<script type="text/javascript">
		
		$(document).ready(function() {
			content="";
			teamname='kbo����';
			getNews(teamname);
			count = 1;
			num = 10;
			
			$("#addcontent").on("click",function() {
				count++;
				if(count>10){
					if(count==11){
						content=content+"<br/><p>��� ������ �ε��Ͽ����ϴ�.</p>";
						$(".media-list").html(content);
					}else{
					}
					
				}else{
					num = count*10;
					getNews(teamname);	
				}
					
			});
			
			$(".list-group-item").on("click",function() {
				teamname = $(this).text();
				count=1;
				num=10;
				$("#title").html(teamname);
				getNews(teamname);
			});
	
			function getNews(teamname) {
	
				var xhr = new XMLHttpRequest();
				
				xhr.onreadystatechange = function() {
					if (xhr.readyState == 4 && xhr.status == 200) {
						news = xhr.responseText;
						printcontent(num, news);
					}
				}
				xhr.open("GET", "/kbofantasy/newssearch.do?teamname=" +teamname, true);
				xhr.send();
	
			}
			
			function printcontent(num, news){
				var line = news.split(";;;;;;;;;;\n");
				content="";
				for(i=1;i<num;i++){
					var arr = line[i].split(";;; ");
					var title = arr[0];
					var link = arr[1];
					var date = arr[2];
					var description = arr[3];
					
					str = "<li class='media'>"
					+"<div class='media-body'><h4 class='media-heading'><a href='"+link+"' target=\"_blank\">"+title+"</a><small>"+date+"</small></h4>"
					+"<p>"+ description +"</p></div>"+
					"</li>";
					
					content = content+str;
				}
				$(".media-list").html(content);

			}
	
		});
		</script>	
		
		<!-- view�κ� ���� -->
		<div class="section">
			<div class="container">
				<div class="row">
					<div class="col-md-12">
						<div class="page-header text-primary">
							<h1>
								�������� <small id="title">��ü����</small>
							</h1>
						</div>
					</div>
				</div>
				<div class="row">
					<div class="col-md-10">
						<ul class="media-list">
						<!-- news ���� �� �� -->
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
							<li class="list-group-item">��ü����</li>
							<li class="list-group-item">�λ� ���</li>
							<li class="list-group-item">�Ｚ ���̿���</li>
							<li class="list-group-item">NC ���̳뽺</li>
							<li class="list-group-item">�ؼ� �������</li>
							<li class="list-group-item">SK ���̹���</li>
							<li class="list-group-item">��ȭ �̱۽�</li>
							<li class="list-group-item">KIA Ÿ�̰���</li>
							<li class="list-group-item">�Ե� ���̾���</li>
							<li class="list-group-item">LG Ʈ����</li>
							<li class="list-group-item">kt ����</li>
						</ul>
					</div>
				</div>
			</div>
		</div>
	</body>
</html>