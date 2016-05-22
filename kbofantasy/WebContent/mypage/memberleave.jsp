<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<meta name="viewport" content="width=device-width, initial-scale=1">
    <script type="text/javascript" src="http://cdnjs.cloudflare.com/ajax/libs/jquery/2.0.3/jquery.min.js"></script>
    <script type="text/javascript" src="http://netdna.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>
    <link href="http://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.3.0/css/font-awesome.min.css" rel="stylesheet" type="text/css">
    <link href="../common/css/memberleave.css" rel="stylesheet" type="text/css">
<title>Insert title here</title>
</head>
<body>
<div class="section">
      <div class="container">
        <div class="row">
          <div class="col-md-12">
            <form role="form" id="myform" name="myform" action="/kbofantasy/memberleave.do" method="post">
              <div class="form-group">
                <h5>
                  이메일, 비밀번호를 입력하시고 확인을 클릭하시면 회원탈퇴가 완료됩니다
                </h5>
              </div>
              <div class="form-group" >
                <label class="control-label" for="exampleInputPassword1">Email</label>
                <input class="form-control" id="NewPassword" placeholder="Email" type="email" required="" name="email">
              </div>
              <div class="form-group">
                <label class="control-label" for="exampleInputPassword1">Password</label>
                <input class="form-control" id="ConfirmPassword" placeholder="Confirm Password" type="password" required="" name="password" >
              </div>
              <span id="checkVal"></span> 
              <button type="submit" class="btn btn-default">확인</button>
            </form>
          </div>
        </div>
      </div>
    </div>
  
</body>
</html>