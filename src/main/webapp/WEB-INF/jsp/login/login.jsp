<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %>    
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">
    <title>Please sign in</title>
    <link href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" rel="stylesheet">
    <link href="https://getbootstrap.com/docs/4.0/examples/signin/signin.css" rel="stylesheet"/>
    <script type="text/javascript">
    	function fnMemberSelect(event){
    		for(let op of event.target.options){
    			if(op.selected){
    				document.loginForm.memId.value = op.dataset.memId??"";
    				document.loginForm.memPass.value = op.dataset.memPass??"";
    			}
    		}	
    	}
    </script>
  </head>
  <body>
     <div class="container">
      <form name="loginForm" class="form-signin" method="post" action="${cPath }/login">
        <h2 class="form-signin-heading">Please sign in</h2>
        <h2 class="form-signin-heading">아이디와 비번 입력!</h2>
        <p>
          <label for="memId" class="sr-only">ID</label>
          <input type="text" id="memId" name="memId" class="form-control" placeholder="memId" required autofocus>
        </p>
        <p>
          <label for="memPass" class="sr-only">Password</label>
          <input type="password" id="memPass" name="memPass" class="form-control" placeholder="memPass" required>
        </p>
        <p>
          <select class="custom-select" onchange="fnMemberSelect(event);">
          	<option>로그인유저 선택</option>
          	<option data-mem-id="a001" data-mem-pass="java">일반유저(a001)[GROUP1_GROUP_MANAGER]</option>
          	<option data-mem-id="b001" data-mem-pass="java">일반유저(b001)[GROUP1_GROUP_NORMAL]</option>
          	<option data-mem-id="c001" data-mem-pass="java">관리자(c001)[GROUP1_GROUP_NORMAL, GROUP2_GROUP_MANAGER]</option>
          	<option data-mem-id="d001" data-mem-pass="java">일반유저(d001)[GROUP2_GROUP_NORMAL]</option>
          </select>
        </p>
		<security:csrfInput/>
        <button class="btn btn-lg btn-primary btn-block" type="submit">Sign in</button>
      </form>
</div>
</body>
</html>