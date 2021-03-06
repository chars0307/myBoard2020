<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>  
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>리스트</title>
<style>
	table { 
		border: 1px solid #000;
		border-collapse: collapse;
		width: 80%;
	}
	
	th, td { border: 1px solid #000; }
	fontCenter {text-align:center; }
	pointer { cursor: pointer; }
	trSelected:hover { background
		display:flex;
		justify-content: center;
	}
	#pageContainer span:not(:Last-child) {
		margin-right: 30px;
	}



		
	}
	.fontCenter {
		text-align: center;
	}
	img {
	width: 200px;
	}

</style>
</head>
<body>
	<div>
		${ loginUser.u_nickname }님 환영합니다.
		<a href="/boardRegmod">
			<button>글쓰기</button>
		</a>
		<a href="/profileDetail">
			<button>프로필</button>
		</a>
	</div>
	<div>
		<table>
			<tr>
				<th>번호</th>
				<th>제목</th>
				<th>조회수</th>
				<th>작성일</th>
				<th>작성자</th>
				<th>이미지</th>
			</tr>
			
			<c:forEach var="vo" items="${list}">
			<tr>
				<td class="fontCenter">${vo.i_board }</td>
				<td>${vo.title }</td>
				<td class="fontCenter">${vo.hits }</td>
				<td class="fontCenter">${vo.r_dt }</td>
				<td class="fontCenter">${vo.u_nickname }</td>
				<td class="fontCenter">
							
					<c:if test = "${vo.img != ''}"> 
					
					<img src = "/img/${vo.i_user}/${vo.img}">
					</c:if>
					
					<c:if test = "${vo.img == ''}"> 
					<img src = "/img/No images.jpg">
					</c:if>
					
					
				</td>
				
			</tr>
			</c:forEach>
			
		</table>
	</div>
	<script>
		function moveToDetail(i_board) {
			location.href = '/boardDetail?i_board=' + i_board
		}
	</script>
</body>
</html>