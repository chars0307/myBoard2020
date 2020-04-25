<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>글쓰기</title>
</head>
<body>
	<body>
	<div>
		<form action="/boardRegmod" method="post">			
			<div><input type="text" name="title" 글쓰기">&nbsp;&nbsp;
				<a href="/BoardRegMod">글쓰기</a>
			</div>
		</form>	
		<div style="color:red;">
			${msg}
		</div>		
	</div>
</body>
</body>
</html>