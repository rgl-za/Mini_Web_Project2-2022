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
	$('#cate li').on('click', function(){
		var cateNo = $('.cate').val();
		var id = ${authUser.id };
		alert(cateNo);
		$.ajax({	
			type: 'POST',
			url: '/jblog/{id}/{cateNo}',
			data: {
				cateNo: cateNo,
				id: id
			},
			dataType: 'json',
			success: function(data){
				str = "<c:forEach items="+ data.postlist + " var='postlist'>";
				str += "<li><a href='/jblog/" + id + "/" + data.postlist.postNo + "'>" + data.postlist.postTitle + "</a><span>" + data.postlist.regDate + "</span></li>";
				str += "</c:forEach>";
				
				$('.blog-list').append(str);
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
<<<<<<< HEAD
					
					<%--  <c:when test="">
						<!-- 등록된 글이 없는경우 -->
						<!-- 
							<h4>등록된 글이 없습니다.</h4>
							<p></p>
						 -->
					</c:when>
					 <c:otherwise>
					 	<c:forEach items="" var="">
							<h4>어벤져스: 인피니티 워</h4>
							<p>
								새로운 조합을 이룬 어벤져스, <br> 역대 최강 빌런 타노스에 맞서 세계의 운명이 걸린<br>
								인피니티 스톤을 향한 무한 대결이 펼쳐진다! <br> <br> 4월, 마블의 클라이맥스를
								목격하라!<br>
							</p>
						</c:forEach>
					 </c:otherwise --%>
					 
=======
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
>>>>>>> 850e78f322f39bec25982cc396c364f91c85bb45
				</div>

				<ul class="blog-list">
					<c:forEach items="${postlist}" var="postlist">
<<<<<<< HEAD
						<li><a href="">${postlist.postTitle }</a> <span>${postlist.regDate }</span></li>
=======
						<li><a href="/jblog/${authUser.id }/${postlist.postNo }">${postlist.postTitle }</a> <span>${postlist.regDate }</span></li>
>>>>>>> 850e78f322f39bec25982cc396c364f91c85bb45
					</c:forEach>
				</ul>
			</div>
		</div>

		<div id="extra">
			<div class="blog-logo">
				<img
					src="${pageContext.request.contextPath}/assets/images/spring-logo.jpg">
			</div>
		</div>

		<div id="navigation">
			<h2>카테고리</h2>
			<ul id="cate">
				<c:forEach items="${catelist}" var="catelist">
					<%-- <li><a href="/jblog/cate/${authUser.id }/${catelist.cateNo }">${catelist.cateName}</a></li> --%>
					<li class="cate" value="${catelist.cateNo }">${catelist.cateName}</li>
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