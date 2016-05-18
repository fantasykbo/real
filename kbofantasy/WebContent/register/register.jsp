<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<html><head>
    <meta name="viewport" content="width=device-width, initial-scale=1" http-equiv="Content-Type" 
    content="text/html" charset="EUC-KR">
    <script type="text/javascript" src="http://cdnjs.cloudflare.com/ajax/libs/jquery/2.0.3/jquery.min.js"></script>
    <script type="text/javascript" src="http://netdna.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>
    <link href="http://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.3.0/css/font-awesome.min.css" rel="stylesheet" type="text/css">
    <link href="../common/css/register.css" rel="stylesheet" type="text/css">
    <script type="text/javascript">
    var xhr;
    function runAjax(){
       xhr = new XMLHttpRequest();
       xhr.onreadystatechange = emailcheck;
       //id = document.getElementById("id");
       xhr.open("POST","/kbofantasy/emailcheck.do",true);
       xhr.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
       xhr.send("email="+myform.email.value);
    }
    
    function emailcheck(){
        console.log("상태값:"+xhr.readyState);
        if(xhr.readyState==4 && xhr.status==200){
     	   //checkVal에 값을 출력 -Ajax통신 결과로 넘어오는 response데이터를 적절하게 출력
           //mydiv.innerHTML = xhr.responseText
           document.getElementById("checkVal").innerHTML=xhr.responseText;
        }
     }

    
    
    </script>
  </head><body>
    <div class="cover">
      <div class="navbar">
        <div class="container">
          <div class="navbar-header">
            <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#navbar-ex-collapse" 
            onclick="alert(11)">
              <span class="sr-only">Toggle navigation</span>
              <span class="icon-bar"></span>
              <span class="icon-bar"></span>
              <span class="icon-bar"></span>
            </button>
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
      <div class="cover-image" style="background-image : url('../images/register.PNG')"></div>
      <div class="container">
        <div class="row">
          <div class="col-md-12 text-center">
            <h1 class="text-inverse">Register</h1>
            <form class="form-horizontal" role="form" action="/kbofantasy/register.do" method="post" name="myform">
              <div class="form-group">
                <div class="col-sm-2">
                  <label for="inputEmail3" class="control-label">Name</label>
                </div>
                <div class="col-sm-10">
                  <input type="text" class="form-control" id="inputEmail3" placeholder="Name" name="name"
                  required>
                </div>
              </div>
              
              <div class="form-group">
                <div class="col-sm-2">
                  <label for="inputEmail3" class="control-label">Email</label>
                </div>
                <div class="col-sm-10">
                  <input type="email" class="form-control" id="email" placeholder="Email" name="email"
                  required onkeyup="runAjax()">
                                
                </div>
                 <span id="checkVal"> </span>
              </div>
              
              <div class="form-group">
                <div class="col-sm-2">
                  <label for="inputPassword3" class="control-label">Password</label>
                </div>
                <div class="col-sm-10">
                  <input type="password" class="form-control" id="inputPassword3" placeholder="Password" name="password">
                </div>
              </div>
               <button type="submit" class="btn btn-default action" >Sign in</button>
            </form>
            <br>
            <br>
           
          </div>
         
        </div>
      </div>
    </div>
  

</body>
</html>