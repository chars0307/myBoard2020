<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인</title>
<link rel="stylesheet" type = "text/css" href ="/css/common.css">
</head>
<body>
 	<div class="flexContainer flexCenter">
 		<form class="solidForm" action="login" method="post">
 			<div class = "marBottom10">
 				<input type="text" name="id" placeholder="아이디">
 			</div>
 			<div class="marBottom10">
 				<input type="password" name="pw" placeholder="비밀번호">
 			</div>
		 	<div class="flexContainer flexCenter">
 				<input type="submit" value="로그인">&nbsp;&nbsp;
 				<a href="/join">회원가입</a>		
 			</div>
 		</form>
 	</div>
</body>
</html>