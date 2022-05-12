<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions"  prefix="fn" %>

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
	$('img').on('click', function(){
		var clickBtn = $(this);
		
		var tr = clickBtn.parent().parent();
	 	var td = tr.children();

		$.ajax({
			type: 'POST',
			url: '/jblog/category/delete',
			data: {
				cateNo: td.eq(0).text(),
				postCount: td.eq(2).text()
			},
			dataType: 'json',
			success: function(isExist){
				if(isExist == false){
					alert("삭제할 수 없습니다.");
				}
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
				<a href="">${authUser.userName }님의 블로그 입니다.</a>
			</h1>
			<ul>
				<c:choose>
					<c:when test="${authUser == null }">
						<!-- 로그인 전 -->
						<li><a href="/jblog/user/login">로그인</a></li>
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
					<li><a href="/jblog/${authUser.id}/admin/basic">기본설정</a></li>
					<li class="selected"><a href="">카테고리</a></li>
					<li><a href="/jblog/${authUser.id}/admin/write">글작성</a></li>
				</ul>

				<table class="admin-cat">
					<thead>
						<tr>
							<th>번호</th>
							<th>카테고리명</th>
							<th>포스트 수</th>
							<th>설명</th>
							<th>삭제</th>
						</tr>
					</thead>
					<tbody id=cateList>
						<c:forEach items="${catelist }" var="catelist" varStatus="status">
							<tr>
								<td>${catelist.cateNo }</td>
								<td>${catelist.cateName }</td>
								<td>${catelist.postCount }</td>
								<td>${catelist.description }</td>
								<td><img src='${pageContext.request.contextPath}/assets/images/delete.jpg'></td>
							</tr>
						</c:forEach>
					</tbody>
				</table>

				<h4 class="n-c">새로운 카테고리 추가</h4>
				<form action="/jblog/category/insert" method="post">
					<table id="admin-cat-add">
						<tr>
							<td class="t">카테고리명</td>
							<td><input type="text" name="cateName" value=""></td>
						</tr>
						<tr>
							<td class="t">설명</td>
							<td><input type="text" name="description"></td>
						</tr>
						<tr>
							<td class="s">&nbsp;</td>
							<td><input id="btnAddCate" type="submit" value="카테고리 추가"></td>
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