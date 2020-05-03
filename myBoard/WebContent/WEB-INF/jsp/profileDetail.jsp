<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>프로필</title>
<link rel = "stylesheet" type="text/css" href = "/css/common.css" />

<style>
	.circular--portrait {
	  position: relative;
	  width: 250px;
	  height: 250px;
	  overflow: hidden;
	  border-radius: 50%;
	}
	
	.circular--portrait img {
	  width: 100%;
	  height: 100%;	  
	}
	
	.frmContainer {
		margin-top: 20px;
	}
</style>

</head>
<body>
	<div class = "flexContainer flexCenter" style = "flex-direction:column;">
	<c:if test="${img == null }">
		<div>
			<img src="/img/No images.jpg" alt = "프로필 이미지 없음"
				style="width:800px; height:800px; border:10px solid black">
		</div>
	</c:if>
	<c:if test="${img != null }">
		<div>
			<img src="/img/${loginUser.i_user}/${img}" alt="프로필 이미지"
				style="width:800px; height:1080px; border:10px solid black">
		</div>
	</c:if>
		<div>
			<form id ="frm" action="profileDetail" 
				method="post" onsubmit="return chkProfile()" 
				enctype="multipart/form-data">
				<label>수정 이미지: <input type="file" name="profileImg"></label>
				<div>
					<input type="submit" value="업로드">
				</div>			
			</form>
		</div>
		<script>
			function chkProfile(){
				if(frm.profileImg.value ==''){
					alert('이미지를 선택해주세요:)')
					return false
				}
				
			}
		</script>
</body>
</html>