<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<html>
  
  <head>
    <meta charset="EUC-KR">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <script type="text/javascript" src="http://cdnjs.cloudflare.com/ajax/libs/jquery/2.0.3/jquery.min.js"></script>
    <script type="text/javascript" src="http://netdna.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>
    <link href="http://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.3.0/css/font-awesome.min.css"
    rel="stylesheet" type="text/css">
    <link href="http://pingendo.github.io/pingendo-bootstrap/themes/default/bootstrap.css"
    rel="stylesheet" type="text/css">
    <script type="text/javascript">
    	function eventListUrl() {
        	var d = new Date();
			var year = d.getFullYear() + "";
			var month = (d.getMonth() + 1) + "";
			if(month.length == 1) {
				month = "0" + month;
			}
			location.href = "/kbofantasy/record/eventList.do?year=" + year + "&month=" + month + "&pathurl=record/eventList.jsp";
    	}
    	
    </script>
  </head>
  
  <body>
    <div class="navbar navbar-default navbar-inverse navbar-static-top">
      <div class="container">
        <div class="navbar-header">
          <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#navbar-ex-collapse">
            <span class="sr-only">Toggle navigation</span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
          </button>
          <a class="navbar-brand" href="#">FantasyKBO</a>
        </div>
        <div class="collapse navbar-collapse" id="navbar-ex-collapse">
          <ul class="nav navbar-nav navbar-right">
            <li class="active">
              <a href="#">Home</a>
            </li>
            <li>
              <a href="#">Contacts</a>
            </li>
          </ul>
        </div>
      </div>
    </div>
    <div class="section">
      <div class="background-image" style="background-image : url('/kbofantasy/images/2012.png')"></div>
      <div class="container">
        <div class="row">
          <div class="col-md-12 text-center">
            <div class="col-md-12 text-center">
              <div class="btn-group btn-group-lg">
                <a class="btn btn-primary dropdown-toggle" data-toggle="dropdown"><i class="fa fa-3x fa-fw fa-gamepad"></i><br>
                  Game <span class="fa fa-caret-down"></span></a>
                <ul class="dropdown-menu" role="menu">
                  <li>
                    <a href="#">Action</a>
                  </li>
                </ul>
              </div>
              <div class="btn-group btn-group-lg">
                <a class="btn btn-primary dropdown-toggle" data-toggle="dropdown"><i class="fa fa-3x fa-table"></i><br>
                Record <span class="fa fa-caret-down"></span></a>
                <ul class="dropdown-menu" role="menu">
                  <li>
                    <a href="javascript:eventListUrl()">일정 / 결과</a>
                  </li>
                </ul>
              </div>
              <div class="btn-group btn-group-lg">
                <a class="btn btn-primary dropdown-toggle menu-icon" data-toggle="dropdown"><i class="fa fa-3x fa-fw fa-newspaper-o"></i><br>
                  Media <span class="fa fa-caret-down"></span></a>
                <ul class="dropdown-menu" role="menu">
                  <li>
                    <a href="#">Action</a>
                  </li>
                </ul>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </body>

</html>