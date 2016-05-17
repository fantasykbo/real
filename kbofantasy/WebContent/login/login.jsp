<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
 <meta charset="utf-8"> 
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <script type="text/javascript" src="http://cdnjs.cloudflare.com/ajax/libs/jquery/2.0.3/jquery.min.js"></script>
    <script type="text/javascript" src="http://netdna.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>
    <link href="http://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.3.0/css/font-awesome.min.css"
    rel="stylesheet" type="text/css">
    <link href="../common/css/login.css" rel="stylesheet" type="text/css">
  </head>
  
  <body>
    <div class="section">
      <div class="container">
        <div class="row">
          <div class="col-md-12">
            <h1 class="text-center">Sign in-Fantasy Game&nbsp;</h1>
          </div>
        </div>
      </div>
    </div>
    <div class="cover">
      <div class="cover-image" style="background-image : url('../images/baseball_stadium.jpg')"></div>
    </div>
    <div class="section">
      <div class="container">
        <div class="row"> 
          <div class="col-md-12">
            <form class="form-horizontal" action="/kbofantasy/login.do" method="post">
              <div class="form-group">
                <div class="col-sm-2">
                  <label for="inputEmail3" class="control-label">Email</label>
                </div>
                <div class="col-sm-10">
                  <input type="email" class="form-control" id="inputEmail3" placeholder="Email" name="email">
                </div>
              </div>
              <div class="form-group">
                <div class="col-sm-2">
                  <label for="inputPassword3" class="control-label">Password</label>
                </div>
                <div class="col-sm-10">
                  <input type="password" class="form-control" id="inputPassword3" placeholder="Password" name="password">
                </div>
              </div>
              <div class="form-group">
                <div class="col-sm-offset-2 col-sm-10 col-md-1">
                  <button type="submit" class="btn btn-default action" >Sign in</button>
                </div>
              </div>
            </form>
          </div>
        </div>
      </div>
    </div>
  

</body>
</html>

