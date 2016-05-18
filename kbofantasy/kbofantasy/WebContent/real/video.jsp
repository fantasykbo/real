<%@page import="openapi.YoutubeSearchAPI"%>
<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
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

		<script type="text/javascript">
		
		$(document).ready(function() {
			content='';
			teamname='kbo����';
			pagetoken='';
			getVideo(teamname,pagetoken);
			
			$("#addcontent").click(function() {
				getVideo(teamname, pagetoken);
			});
			
			$(".list-group-item").click(function() {
				content="";
				teamname = $(this).text();
				document.getElementById("title").innerHTML= teamname;
				pagetoken="";	//�ʱ�ȭ
				getVideo(teamname, pagetoken);
			});
	
			function getVideo(teamname, pagetoken) {
	
				var xhr = new XMLHttpRequest();
				
				xhr.onreadystatechange = function() {
					if (xhr.readyState == 4 && xhr.status == 200) {
						var myjson = xhr.responseText;
						jsonparser(myjson);
					}
				}
				xhr.open("GET", "/kbofantasy/videosearch.do?teamname=" +teamname+"&pagetoken="+pagetoken, true);
				xhr.send();
	
			}
			
			function jsonparser(myjson){
				
				var data= JSON.parse(myjson);
				var videoid;	
				var publishedAt;
				var title;
				var description;
				var images;
				
				pagetoken = data.nextPageToken;
				
				for(i=0;i<6;i++){
					videoid = data.items[i].id.videoId;
					publishedAt = data.items[i].snippet.publishedAt;
					title= data.items[i].snippet.title;
					description = data.items[i].snippet.description;
					images = data.items[i].snippet.thumbnails.medium.url;
					
					str = "<div class='col-md-4'><div class='thumbnail'><img src='"+images+"' class='img-responsive'>"
					+"<div class='caption'><a href='https://www.youtube.com/watch?v="+videoid+"'><h5>"+title+"</h5></a><p class='text-muted'>"+publishedAt+"</p></div></div></div>";
					
					content = content + str;
					

				}
				printpage(content);
				
			}
			
			function printpage(content){
				document.getElementById("content").innerHTML = content;
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
								������ <small id="title">��ü����</small>
							</h1>
						</div>
					</div>
				</div>
				<div class="row">
					<div class="col-md-10">
						<div id="content"></div>
						<div class="row">
							<div class="col-md-12">
								<ul class="pager">
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