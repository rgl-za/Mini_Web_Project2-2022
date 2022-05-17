<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>JBlog</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/jblog.css">
<script type="text/javascript" src="${pageContext.request.contextPath }/assets/js/jquery/jquery-1.12.4.js"></script>
<script type="text/javascript">
$(document).ready(function (){	
	var fileData;
	 $('#file').on('change', function(event){
		var formData = new FormData($('#fileForm')[0])
		var inputFile = $("input[name='file']");  
		var file = inputFile.val().split("\\");
		var fileName = file[file.length-1];
		console.log(fileName);
		
		$.ajax({
			type: 'POST',
			url: '/jblog/upload/uploadAjax',
			data: formData,
			dataType: "text",
			async:false,
			processData: false,
			contentType: false,
			success: function(data){
				// str="/Users/jihyeonjeong/Mini_Web_Project2-2022/JBlog/src/main/resources/upload"+data;
				str="<img src= '/jblog/upload/displayFile?fileName="+data+"'>";
				// $(".img").append(str);
				$(".img").replaceWith(str);
				fileData = data;
			}, 
			error: function() {
				alert("이미지 등록 오류! 다시 시도 해주세요.");
			}
		});
	});
	 // console.log(fileData);
	 $('#submit').on('click', function(event){
		 $.ajax({
			 type: 'POST',
			 url: '/jblog/upload/insert',
			 data:{
				 // $('#fileForm').serialize()
				 "blogTitle": $('#blogTitle').val(),
				 "logoFile": fileData
			 }, 
			 success: function(data){
				location.href='/jblog/${authUser.id}';
			 },
			 error: function() {
				alert("변경 오류! 다시 시도 해주세요.");
			 }
		 });
	 });
});
</script>

</head>
<body>

	<div id="container">

		<!-- 블로그 해더 -->
		<div id="header">
			<h1>
				<a href="/jblog/${authUser.id}">${authUser.userName }님의 블로그 입니다.</a>
			</h1>
			<ul>
				<c:choose>
					<c:when test="${authUser == null }">
						<!-- 로그인 전 -->
						<li><a href="">로그인</a></li>
					</c:when>
					<c:otherwise>
						<li><a href="">로그아웃</a></li>
						<li><a href="/jblog/${authUser.id}/admin/basic">내블로그 관리</a></li>
					</c:otherwise>
				</c:choose>
			</ul>
		</div>


		<div id="wrapper">
			<div id="content" class="full-screen">
				<ul class="admin-menu">
					<li class="selected"><a href="">기본설정</a></li>
					<li><a href="/jblog/${authUser.id}/admin/category">카테고리</a></li>
					<li><a href="/jblog/${authUser.id}/admin/write">글작성</a></li>
				</ul>

				<form id="fileForm" action="/jblog/${authUser.id}/admin/basic" method="POST" enctype="multipart/form-data">
					<table class="admin-config">
						<tr>
							<td class="t">블로그 제목</td>
							<td><input type="text" size="40" name="blogTitle" value="" id="blogTitle"></td>
						</tr>
						<tr>
							<td class="t">로고이미지</td>
<<<<<<< HEAD
							<td class="img"><img src="${pageContext.request.contextPath }/upload/${settingBlog.logoFile}"></td>
=======
							<td class="img"><img src="${pageContext.request.contextPath}/upload/${settingBlog.logoFile}"></td>
>>>>>>> bc5b8728ea2bcc1b05aa7cd9b4822f4b89d8dcfa
							
						</tr>
						<tr>
							<td class="t">&nbsp;</td>
							<td><input type="file" name="file" id="file" multiple></td>
						</tr>
						<tr>
							<td class="t">&nbsp;</td>
							<td class="s"><input id="submit" type="submit" value="기본설정 변경"></td>
						</tr>
					</table>
				</form>
			</div>
		</div>


		<!-- 푸터-->
		<div id="footer">
			<p>
				<strong>Spring 이야기</strong> is powered by JBlog (c)2018
			</p>
		</div>

	</div>
</body>

</html>