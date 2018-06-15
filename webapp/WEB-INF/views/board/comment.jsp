<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/style.css" />
<script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
<script>
	function send(f){
		//유효성
		if(/^.{1,30}$/.test(f.title.value) == false){
			alert("제목은 1~30글자 입니다.");
			f.title.focus();
			return;
		}
		if(/^.{2,10}$/.test(f.name.value) == false){
			alert("이름은 2~10글자 입니다.");
			f.name.focus();
			return;
		}
		if(/^.{1,2000}$/.test(f.content.value) == false){
			alert("내용은 2000글자 미만으로 작성하셔야 합니다.");
			f.content.focus();
			return;
		}
		
		f.action = "${pageContext.request.contextPath}/board/comment"
			
		f.submit();
	}
</script>
</head>
<body>
	<div class="container">
		<div class="header">
			<h1 class="title text_center">자유게시판</h1>
		</div>
		<div class="body">
			<table class="board-view">
				<tr>
					<th width="30%">제목</th>
					<td>${board.title }</td>
				</tr>
				<tr>
					<th>이름</th>
					<td>${board.name }</td>
				</tr>

				<tr>
					<td></td>
					<td class="content">${board.content }</td>
				</tr>
			</table>
			<hr />
			<form method="post">
				<input type="hidden" name="id" value="${board.id}" />
				<table class="board-view">
					<tr>
						<th>제목</th>
						<td>
							<input type="text" name="title" />
						</td>
					</tr>
					<tr>
						<th>이름</th>
						<td>
							<input type="text" name="name" />
						</td>
					</tr>
					<tr>
						<td></td>
						<td>
							<textarea name="content"></textarea>
						</td>
					</tr>
					<tr>
						<th colspan="2">
							<button type="button" onclick="send(this.form)">전송</button>
						</th>
					</tr>
				</table>
			</form>
		</div>
		<div class="footer">
			
		</div>
	</div>
</body>
</html>



