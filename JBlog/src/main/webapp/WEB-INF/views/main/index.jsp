<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>JBlog</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/jblog.css">
</head>
<body>
	<div class="center-content">
		
		<!-- 메인해더 -->
	 	<a href="">
			<img class="logo" src="${pageContext.request.contextPath}/assets/images/logo.jpg">
		</a>
		<ul class="menu">
			<c:choose>
				<c:when test="${empty authUser }">
					<li><a href="user/login">로그인</a></li>
					<li><a href="user/join">회원가입</a></li>
				</c:when>

				<c:otherwise>
					<li><a href="user/logout">로그아웃</a></li>
					<li><a href= "${authUser.id }">내블로그</a></li>
				</c:otherwise>
			</c:choose>
		</ul>
		
		<form class="search-form">
			<fieldset>
				<input type="text" name="keyword" />
				<input type="submit" value="검색" />
			</fieldset>
			<fieldset>
				<input type="radio" name="which" value="blog-title"> <label>블로그 제목</label>
				<input type="radio" name="which" value="blog-user"> <label>블로거</label>
			</fieldset>
		</form>
	</div>
</body>
</html>