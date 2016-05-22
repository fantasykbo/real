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
			teamname='kbo리그';
			getNews(teamname);
			count = 1;
			num = 10;
			
			$("#addcontent").on("click",function() {
				count++;
				if(count>10){
					if(count==11){
						content=content+"<br/><p>모든 뉴스를 로드하였습니다.</p>";
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
		
		<!-- view부분 시작 -->
		<div class="section">
			<div class="container">
				<div class="row">
					<div class="col-md-12">
						<div class="page-header text-primary">
							<h1>
								뉴스센터 <small id="title">전체보기</small>
							</h1>
						</div>
					</div>
				</div>
				<div class="row">
					<div class="col-md-10">
						<ul class="media-list">
						<!-- news 내용 들어갈 곳 -->
						</ul>
						<div class="row">
							<div class="col-md-12 text-center">
								<ul class="pagination">
									<li id="addcontent">더보기</li>

								</ul>
							</div>
						</div>
					</div>
					<div class="col-md-2">
						<ul class="list-group text-center">
							<li class="list-group-item">전체보기</li>
							<li class="list-group-item">두산 베어스</li>
							<li class="list-group-item">삼성 라이온즈</li>
							<li class="list-group-item">NC 다이노스</li>
							<li class="list-group-item">넥센 히어로즈</li>
							<li class="list-group-item">SK 와이번스</li>
							<li class="list-group-item">한화 이글스</li>
							<li class="list-group-item">KIA 타이거즈</li>
							<li class="list-group-item">롯데 자이언츠</li>
							<li class="list-group-item">LG 트윈스</li>
							<li class="list-group-item">kt 위즈</li>
						</ul>
					</div>
				</div>
			</div>
		</div>
	</body>
</html>