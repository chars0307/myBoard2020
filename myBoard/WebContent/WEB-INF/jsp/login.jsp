<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인</title>
<link rel="stylesheet" type="text/css" href="/css/common.css" />
</head>
<body>
	<div class="flexContainer flexCenter" style="flex-direction:column;">
		<form class="solidForm" action="login" method="post">
			<div class="marBottom10">
				<input type="text" name="u_id" placeholder="아이디">
			</div>		
			<div class="marBottom10">
				<input type="password" name="u_pw" placeholder="비밀번호">
			</div>
			<div class="flexContainer flexCenter" style="height:40px;">
				<input type="submit" value="로그인">&nbsp;&nbsp;
				<a href="/join">회원가입</a>
			</div>
		</form>	
		<div style="color:red;">
			${msg}
		</div>		
	</div>
</body>
</html>