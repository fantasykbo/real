<%@page import="org.json.simple.JSONArray"%>
<%@page import="org.json.simple.parser.JSONParser"%>
<%@page import="org.json.simple.JSONObject"%>
<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<!-- <meta charset="utf-8"> -->
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
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<script src="//code.jquery.com/jquery-1.11.3.min.js"></script>
<title>Insert title here</title>
<%
	String kbo = (String) request.getAttribute("kbo");
	int Inn = (int) request.getAttribute("Inn");
	String eventId = (String) request.getAttribute("eventId");
	String month = (String) request.getAttribute("month");
%>

<script type="text/javascript">
	$(document).ready(function(){
				kbo = '<%=kbo%>';
				Inn = '<%=Inn%>';
                obj = JSON.parse(kbo);
                if(Inn==0){
                	Inn = obj.currentInning;
                }
                
                /*  Object.keys(obj.relayTexts[1][0]).length object ������ ���ϱ� */
                curInn = obj.currentInning;
                rel = obj.relayTexts;
 				curBat = rel.currentBatter.liveText;
 				curTxtSize = rel.currentBatterTexts.length;
 				alert(Inn);
 				
 				$("#awaylogo").attr("src","http://imgsports.naver.net/images/emblem/new/kbo/default/"+obj.gameInfo.aCode+".png");
 				$("#homelogo").attr("src","http://imgsports.naver.net/images/emblem/new/kbo/default/"+obj.gameInfo.hCode+".png");
 				$("#awayimg").attr("src","http://www.koreabaseball.com/FILE/person/middle/"+obj.currentPlayers.away.playerInfo.pCode+".jpg");
 				$("#homeimg").attr("src","http://www.koreabaseball.com/FILE/person/middle/"+obj.currentPlayers.home.playerInfo.pCode+".jpg");
 				
				$("#awayPlayer").append("<h4 class='text-center' id=a_name></h4>");
				$("#a_name").text(obj.currentPlayers.away.playerInfo.name +
								"  /  "+ obj.currentPlayers.away.playerInfo.hitType);
				
				$("#homePlayer").append("<h4 class='text-center' id=h_name></h4>");
				$("#h_name").text(obj.currentPlayers.home.playerInfo.name +
								"  /  "+ obj.currentPlayers.home.playerInfo.hitType);
				$("#tbody").append()
				alert(obj.scoreBoard.inn.home.length);
				if(obj.scoreBoard.inn.home.)
				ascoresize = obj.scoreBoard.inn.home.length;
				hscoresize = obj.scoreBoard.inn.home.length;
				for(i=0; i<ascoresize; i++){
					$(score)
				}
                /* <h3 class="text-center">Thumbnail label</h3>
                <p class="text-center">obj.currentPlayers.away.playerInfo.name</p> */
                /* +obj.currentPlayers.away.playerInfo.name +
				"/t"+obj.currentPlayers.away.playerInfo.hitType */
 				//Inn link�ɱ�
 				<%-- href="javascript:runAjax('<%= deptInfo.getDeptno() %>')" --%>
                
                Totaltext();
                //����� ������ ��� �ٷ� ���� ȣ��
                //�������� ������ ��� Ajaxȣ��
                if(obj.gameInfo.statusCode==4){ 
                	if(Inn<10){
    		 			for(i=1;i<=curInn;i++){
    		 				$("#page"+i).attr("href","javascript:refresh("+$("#page"+i).text()+")");    		 				
    		 			}
    	 			}else{
    	 				$("#pageext").attr("href","javascript:refresh("+curInn+")");
    	 			}
                }else{      	
                	if(Inn<10){	 				
    		 			for(i=1;i<=curInn;i++){
    		 				inn = $('#page'+i).text();
    		 				$("#page"+i).attr("href","javascript:Ajax('<%=month%>','<%=eventId%>',"+inn+")");		 				
    		 			}
    	 			}else{
    	 				inn = $('#page'+i).text();
    	 				$("#pageext").attr("href","javascript:Ajax('<%=month%>','<%=eventId%>',"+curInn+")");
    	 			}
                }
 				
                
	 			
	 			
	 				 			

    })
	function Ajax(month, eventId, Inn) {
		$(".page").on("click", function(){
			$.get("/kbofantasy/livetext.do",{"month":month,"eventId":eventId,"Inn":Inn,"path":"ajax"},live);
		});
	};
	function live(txt){	//������ ����� �����ϸ� ó������� success�� ������ �Լ��� ȣ���ϸ� �Ѱ��ش�.
		kbo = txt;
		Inn = inn;
        obj = JSON.parse(kbo);
        curInn = obj.currentInning;
        rel = obj.relayTexts;
		curBat = rel.currentBatter.liveText;
		curTxtSize = rel.currentBatterTexts.length;
/* 		if(Inn==curInn){
			
		}else{ */
			refresh();
		/* } */
		
		/* $("#live").children().html("get"+txt);	//document.getElementByid().innerhtml = xhr.responseText�� ���� */
	}
    function Totaltext(){
		if(Inn==curInn){
	 		//��Ⱑ ������ ��� final���� ���
	 		if(obj.relayTexts.final.length!=0){
	 			for(i=0;i<rel.final.length;i++){
	 		 		finalinfo = $("<li>"+rel.final[i].liveText+"</li>");
	 		 	 	$("#live").children().append(finalinfo);
	 		 	}
	 		 	$("#live").children().append("<li>============================</li>"); 		 	 			
	 		}
	 		//��Ⱑ ���� �������϶�
	 		
			curBatText();
			gametext(Inn);
	 	}else{
	 		gametext(Inn);
	 	}
	 }
    
	//���� �������� ���
    function gametext(Inn){
		if(Inn>9){
			for(i=10; i<=curInn; i++){
				reltxt = Object.keys(obj.relayTexts[Inn]).length;				
				for(j=1; j<=reltxt; j++){	               	
		            livetext = $("<li>"+obj.relayTexts[i][j-1].liveText+"</li>");
		            $("#live").children().append(livetext);
		            }	
			}
		}else{
			reltxt = Object.keys(obj.relayTexts[Inn]).length;
			for(j=0; j<reltxt; j++){	               	
	            livetext = $("<li>"+obj.relayTexts[Inn][j].liveText+"</li>");
	            $("#live").children().append(livetext);
	            }			
		}

	}
	
	function curBatText(){
			//���� Ÿ���̸� ���
			livecurBat = $("<li>"+"����Ÿ�� : "+curBat+"</li>");
			$("#live").children().append(livecurBat);		
			$("#live").children().append("<li>----------------------------</li>");
			
			//���� Ÿ�� �������� ���
			for(i=0; i< curTxtSize; i++){
				curTxt = $("<li>"+obj.relayTexts.currentBatterTexts[i].liveText+"</li>");
				$("#live").children().append(curTxt);

			}
			$("#live").children().append("<li>----------------------------</li>");
	}
	
	function refresh(inn){
		Inn = inn;
		$("#live1").empty();
		Totaltext();
	}
	
</script>
</head>
<body>
	<div class="section">
		<div class="container">
			<div class="row">
				<div class="col-md-12">
					<div class="page-header text-primary">
						<h1>�����aaaaaaaaaaaaaaa</h1>
					</div>
				</div>
			</div>
			<div class="row">
				<div class="col-md-2">
					<ol>
						<li>One</li>
						<li>Two</li>
						<li>Three</li>
					</ol>
				</div>
				<div class="col-md-2">
					<ol>
						<li>One</li>
						<li>Two</li>
						<li>Three</li>
					</ol>
				</div>
				<div class="col-md-2">
					<ol>
						<li>One</li>
						<li>Two</li>
						<li>Three</li>
					</ol>
				</div>
				<div class="col-md-2">
					<ol>
						<li>One</li>
						<li>Two</li>
						<li>Three</li>
					</ol>
				</div>
				<div class="col-md-2">
					<ol>
						<li>One</li>
						<li>Two</li>
						<li>Three</li>
					</ol>
				</div>
				<div class="col-md-2">
					<ol>
						<li>One</li>
						<li>Two</li>
						<li>Three</li>
					</ol>
				</div>
			</div>
			<div class="row">
				<div class="col-md-2 text-center">
					<img class="center-block img-responsive" id="awaylogo">
				</div>
				<div class="col-md-1">
					<h1 class="text-center">99</h1>
				</div>
				<div class="col-md-6">
					<table class="table table-condensed">
						<thead>
							<tr>
								<th></th>
								<th>1</th>
								<th>2</th>
								<th>3</th>
								<th>4</th>
								<th>5</th>
								<th>6</th>
								<th>7</th>
								<th>8</th>
								<th>9</th>
								<th>10</th>
								<th>11</th>
								<th>12</th>
								<th>R</th>
								<th>H</th>
								<th>E</th>
								<th>B</th>
							</tr>
						</thead>
						<tbody id="score">
							<tr>
								<td>LG</td>
								<td>99</td>
								<td>99</td>
								<td>99</td>
								<td>99</td>
								<td>99</td>
								<td>99</td>
								<td>99</td>
								<td>99</td>
								<td>99</td>
								<td>99</td>
								<td>99</td>
								<td>99</td>
								<td>99</td>
								<td>99</td>
								<td>99</td>
								<td>99</td>
							</tr>
							<tr>
								<td>�Ｚ</td>
								<td>99</td>
								<td>99</td>
								<td>99</td>
								<td>99</td>
								<td>99</td>
								<td>99</td>
								<td>99</td>
								<td>99</td>
								<td>99</td>
								<td>99</td>
								<td>99</td>
								<td>99</td>
								<td>99</td>
								<td>99</td>
								<td>99</td>
								<td>99</td>
							</tr>
						</tbody>
					</table>
					<h3 class="text-center">2016.05.14 (��) 15:00, ���</h3>
				</div>
				<div class="col-md-1">
					<h1 class="text-center">99</h1>
				</div>
				<div class="col-md-2 text-center">
					<img class="center-block img-responsive" id="homelogo">
				</div>
			</div>
			<div class="row" draggable="true">
				<div class="col-md-3">
					<h1 class="text-left">
						LG Ʈ���� <br> <small> <span class="label label-default">1��</span>99��
							99�� 99��
						</small>
					</h1>
				</div>
				<div class="col-md-6">
					<h1 class="text-center">4ȸ�� LG ����</h1>
				</div>
				<div class="col-md-3 text-right">
					<h1 class="text-right">
						�Ｚ ���̿��� <br> <small>99�� 99�� 99�� <span
							class="label label-default">2��</span>
						</small>
					</h1>
				</div>
			</div>
			<div class="row">
				<div class="col-md-4 text-center">
					<div class="col-md-12">
						<ul class="pagination">
							<li><a id="page1" class="page">1</a></li>
							<li><a id="page2" class="page">2</a></li>
							<li><a id="page3" class="page">3</a></li>
							<li><a id="page4" class="page">4</a></li>
							<li><a id="page5" class="page">5</a></li>
							<li><a id="page6" class="page">6</a></li>
							<li><a id="page7" class="page">7</a></li>
							<li><a id="page8" class="page">8</a></li>
							<li><a id="page9" class="page">9</a></li>
							<li><a id="pageext" class="page">����</a></li>
						</ul>
					</div>
					<div class="col-md-12" id="live">
						<ol class="lead list-unstyled" id="live1">
						</ol>
					</div>
				</div>
				<div class="col-md-8">
					<div class="col-md-12">
						<h3 class="text-muted">�ǽð� ��Ÿ����</h3>
						<div class="col-md-6">
							<div class="thumbnail">
								<img class="img-responsive" id="awayimg">
								<div class="caption" id="awayPlayer"></div>
							</div>
						</div>
						<div class="col-md-6">
							<div class="thumbnail">
								<img class="img-responsive" id="homeimg">
								<div class="caption" id="homePlayer"></div>
							</div>
							<div class="col-md-12">
								<h3 class="text-muted">LG Ʈ���� Ÿ�ڱ��</h3>
								<table
									class="table table-bordered table-condensed table-striped">
									<thead>
										<tr>
											<th>Ÿ��</th>
											<th>������</th>
											<th>������</th>
											<th>Ÿ��</th>
											<th>����</th>
											<th>��Ÿ</th>
											<th>Ÿ��</th>
											<th>����</th>
											<th>����</th>
											<th>�籸</th>
											<th>����Ÿ��</th>
											<th>����Ÿ��</th>
											<th>���Ÿ��</th>
										</tr>
									</thead>
									<tbody>
										<tr>
											<td>��</td>
											<td>������</td>
											<td>������</td>
											<td>������</td>
											<td>������</td>
											<td>������</td>
											<td>������</td>
											<td>������</td>
											<td>������</td>
											<td>������</td>
											<td>������</td>
											<td>������</td>
											<td>������</td>
										</tr>
									</tbody>
								</table>
								<h3 class="text-muted">LG Ʈ���� �������</h3>
								<table
									class="table table-bordered table-condensed table-striped">
									<thead>
										<tr>
											<th>������</th>
											<th>�̴�</th>
											<th>������</th>
											<th>��</th>
											<th>��Ȩ��</th>
											<th>����</th>
											<th>����</th>
											<th>����</th>
											<th>��å</th>
											<th>���������å</th>
											<th>���������å</th>
											<th>��������å</th>
										</tr>
									</thead>
									<tbody>
										<tr>
											<td>������</td>
											<td>��</td>
											<td>99</td>
											<td>99</td>
											<td>99</td>
											<td>99</td>
											<td>6 ��</td>
											<td>99</td>
											<td>999</td>
											<td>99</td>
											<td>99</td>
											<td>99</td>
										</tr>
									</tbody>
								</table>
							</div>
							<div class="col-md-12">
								<h3 class="text-muted">�Ｚ ���̿��� Ÿ�ڱ��</h3>
								<table
									class="table table-bordered table-condensed table-striped">
									<thead>
										<tr>
											<th>Ÿ��</th>
											<th>������</th>
											<th>������</th>
											<th>Ÿ��</th>
											<th>����</th>
											<th>��Ÿ</th>
											<th>Ÿ��</th>
											<th>����</th>
											<th>����</th>
											<th>�籸</th>
											<th>����Ÿ��</th>
											<th>����Ÿ��</th>
											<th>���Ÿ��</th>
										</tr>
									</thead>
									<tbody>
										<tr>
											<td>��</td>
											<td>������</td>
											<td>������</td>
											<td>������</td>
											<td>������</td>
											<td>������</td>
											<td>������</td>
											<td>������</td>
											<td>������</td>
											<td>������</td>
											<td>������</td>
											<td>������</td>
											<td>������</td>
										</tr>
									</tbody>
								</table>
								<h3 class="text-muted">�Ｚ ���̿��� �������</h3>
								<table
									class="table table-bordered table-condensed table-striped">
									<thead>
										<tr>
											<th>������</th>
											<th>�̴�</th>
											<th>������</th>
											<th>��</th>
											<th>��Ȩ��</th>
											<th>����</th>
											<th>����</th>
											<th>����</th>
											<th>��å</th>
											<th>���������å</th>
											<th>���������å</th>
											<th>��������å</th>
										</tr>
									</thead>
									<tbody>
										<tr>
											<td>������</td>
											<td>��</td>
											<td>99</td>
											<td>99</td>
											<td>99</td>
											<td>99</td>
											<td>6 ��</td>
											<td>99</td>
											<td>999</td>
											<td>99</td>
											<td>99</td>
											<td>99</td>
										</tr>
									</tbody>
								</table>
							</div>
						</div>
					</div>
				</div>
			</div>
</body>
</html>