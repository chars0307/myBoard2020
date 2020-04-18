<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입</title>
<link rel="stylesheet" type = "text/css" href ="/css/common.css">
</head>
<body>
 	<div class="flexContainer flexCenter">
 		<form class="solidForm" action="login" method="post">
 			<div class = "marBottom10">
 				<input type="text" name="u_id" placeholder="아이디">
 			</div>
 			<div class="marBottom10">
 				<input type="password" name="u_pw" placeholder="비밀번호">
 			</div>
 			<div class="marBottom10">
 				<input type="password" name="re_pw" placeholder="비밀번호확인">
 			</div> 			
 			<div class="marBottom10">
 				<input type="text" name="u_nickname" placeholder="닉네임">
 			</div> 			
 			<div class="marBottom10">
 				<input type="text" name="email" placeholder="이메일">
 			</div> 			
 			<div class="marBottom10">
 				<input type="text" name="ph" placeholder="휴대폰번호 ex(010-1234-1234)">
 			</div> 			
 			<div class="marBottom10">
 				<input type="text" name="addr" placeholder="주소">
 			</div> 			
 			<div class="marBottom10">
 				<label>여 <input type="radio" name="sex" value="0" checked></label>
 				<label>남 <input type="radio" name="sex" value="1" checked></label>
 			</div> 	
 			<div class="marBottom10">
 				<input type="text" name="birth" placeholder="생년월일">
 			</div> 				
		 	<div class="flexContainer flexCenter">
 				<input type="submit" value="회원가입">
 				<a href="/join">회원가입</a>		
 			</div>
 		</form>
 	</div>
</body>
</html>