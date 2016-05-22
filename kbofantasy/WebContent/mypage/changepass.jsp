<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html><head>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <script type="text/javascript" src="http://cdnjs.cloudflare.com/ajax/libs/jquery/2.0.3/jquery.min.js"></script>
    <script type="text/javascript" src="http://netdna.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>
    <link href="http://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.3.0/css/font-awesome.min.css" rel="stylesheet" type="text/css">
    <link href="http://pingendo.github.io/pingendo-bootstrap/themes/default/bootstrap.css" rel="stylesheet" type="text/css">
    <script type="text/javascript">
    //New Password, Confirm Password 로직
    function CheckPass(){
    	if(document.myform.NewPassword.value==document.myform.ConfirmPassword.value){
    		alert("비밀번호가 일치합니다");
    		System.out.println("일치");
    	}else{
    		alert("비밀번호를 확인해주세요");
    		System.out.println("불일치");
    	}
    }
    </script>
  </head><body>
    
    <div class="section">
      <div class="container">
        <div class="row">
          <div class="col-md-12">
            <form role="form" id="myform" name="myform" action="/kbofantasy/changepass.do" method="post">
              <div class="form-group">
                <label class="control-label" for="exampleInputPassword1">Email</label>
                <input class="form-control" id="email" placeholder="Email" type="email" 
                required name="email">
              </div>
             
              <div class="form-group">
                <label class="control-label" for="exampleInputPassword1">New Password</label>
                <input class="form-control" id="NewPassword" placeholder="New Password" type="password" 
                required name="password">
              </div>
              <div class="form-group">
                <label class="control-label" for="exampleInputPassword1">Confirm Password</label>
                <input class="form-control" id="ConfirmPassword" placeholder="Confirm Password" type="password"
                required onblur="CheckPass()" name="password1">
              </div>
              <span id="checkVal"> </span>
              <button type="submit" class="btn btn-default">Submit</button>
            </form>
          </div>
        </div>
      </div>
    </div>
  

</body></html>