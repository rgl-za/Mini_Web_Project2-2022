<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>


<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>JBlog</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/assets/css/jblog.css">
<script type="text/javascript"
	src="${pageContext.request.contextPath }/assets/js/jquery/jquery-1.12.4.js"></script>
<script type="text/javascript">
$(document).ready(function (){
	str = "<c:forEach items='${postlist}' var='postlist'><li class='cateNo' value='${catelist.cateNo }'><a href='javascript:void(0);' class='post'>${postlist.postTitle }</a> <span>${postlist.regDate }</span></li></c:forEach>";
	$('#test').html(str);
	
	$('.cate').click(function(){
		var cateNo = $(this).parent().val();
		$.ajax({	
			type: 'POST',
			url: '/jblog/cateNo',
			data: {
				cateNo: cateNo
			},
			dataType: 'json',
			success: function(data){
				str = "";
				$('.blog-list li').empty();
				console.log($('.blog-list li').html());
				for(var i = 0; i < data.length; i++){
					console.log(data[i]);
					console.log(data[i].postNo);
					str += "<li class='cateNo' value='${";
					str += data[i].cateNo; 
					str += "}'><a href='javascript:void(0);' class='post'>";
					str += data[i].postTitle;
					str += "</a><span>";
					str += data[i].regDate;
					str += "</span></li>";
				} 
				console.log(str);
				$('#test').html(str);
				console.log($('#test').html());
			},
			error: function() { alert("에러 발생");}
		});
	});
	
	$('.post').click(function(){
		var postNo = $(this).parent().val();
		$.ajax({	
			type: 'POST',
			url: '/jblog/postNo',
			data: {
				postNo: postNo
			},
			dataType: 'json',
			success: function(data){
				$('.blog-content h4').text(data.postTitle);
				$('.blog-content p').text(data.postContent);
			},
			error: function() { alert("에러 발생");}
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
						<li><a href="/jblog/user/login">로그인</a></li>
					</c:when>
					<c:otherwise>
						<li><a href="/jblog/user/logout">로그아웃</a></li>
						<li><a href="${authUser.id}/admin/basic">내블로그 관리</a></li>
					</c:otherwise>
				</c:choose>

			</ul>
		</div>

		<div id="wrapper">
			<div id="content">
				<div class="blog-content">

					<c:choose>
					<c:when test="${not empty postOne }">
					<h4>${postOne.postTitle }</h4>
							<p>${postOne.postContent }</p>
					</c:when>
						<c:when test="${not empty postlist }">
							<h4>${postlist[0].postTitle }</h4>
							<p>${postlist[0].postContent }</p>
						</c:when>
						<c:otherwise>
							<h4>등록된 글이 없습니다.</h4>
							<p></p>
						</c:otherwise>
					</c:choose>
				</div>

				<ul class="blog-list">
				<div id="test"></div>

				</ul>
			</div>
		</div>

		<div id="extra">
			<div class="blog-logo">
				<img
					src="/jblog/upload/displayFile?fileName=${settingBlog.logoFile}">
			</div>
		</div>

		<div id="navigation">
			<h2>카테고리</h2>
			<ul>
				<c:forEach items="${catelist}" var="catelist">
					<li class="cateNo" value="${catelist.cateNo }"><a href="javascript:void(0);" class="cate">${catelist.cateName}</a></li>
					<%-- <li class="cate" value="${catelist.cateNo }">${catelist.cateName}</li> --%>
				</c:forEach>
			</ul>

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