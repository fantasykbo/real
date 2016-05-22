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
</head>
<body>
	<script type="text/javascript">
		$(document).ready(
				function() {
					getRank("default");

					$("li a").on("click", function() {
						selector = $(this).text();
						if (selector == "Ÿ�ڼ���") {
							selector = "b";
						} else if (selector == "��������") {
							selector = "p";
						} else if (selector == "���Ӽ���") {
							selector = "g";
						}
						getRank(selector);
					});

					function getRank(selector) {
						var xhr = new XMLHttpRequest();

						xhr.onreadystatechange = function() {
							if (xhr.readyState == 4 && xhr.status == 200) {
								$("#ranking").html(xhr.responseText);
							}
						}
						xhr.open("GET", "/kbofantasy/ranking.do?selector="
								+ selector, true);
						xhr.send();
					}

				});
	</script>
	<div class="section">
		<div class="container">
			<div class="row">
				<div class="col-md-12">
					<div class="page-header text-primary">
						<h1>���ӷ�ŷ <small>Top 20</small></h1>
					</div>
					<ul class="pager">
						<li><a>���Ӽ���</a></li>
						<li><a>Ÿ�ڼ���</a></li>
						<li><a>��������</a></li>
					</ul>
					<table class="table" id="ranking"></table>
				</div>
			</div>
		</div>
	</div>
</body>
</html>