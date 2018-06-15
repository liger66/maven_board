<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet"href="${pageContext.request.contextPath}/css/style.css"/>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
<link href="http://cdnjs.cloudflare.com/ajax/libs/summernote/0.8.9/summernote.css" rel="stylesheet">
<script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
<script>
	function check(f){
		//유효성검사 (제목, 이름, 내용)
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
			alert("내용은은 2000글자 미만으로 작성하셔야 합니다.");
			f.content.focus();
			return;
		}
		
		f.submit();
	}
</script>
</head>
<body>
	<div class="container">
		<div class="header">
			<h1 class="title text_center">글쓰기</h1>
		</div>
		<div class="body">
			<form action="${pageContext.request.contextPath}/board/update" 
			      method="post">
			<input type="hidden" name="id" value="${board.id }" />
			<table class="board-view">
				<tr>
					<th width="30%">제목</th>
					<td>
						<input type="text" name="title" 
						       value="${board.title }" />
					</td>
				</tr>
				<tr>
					<th>이름</th>
					<td>
						<input type="text" name="name"
						       value="${board.name }" />
					</td>
				</tr>
				<tr>
					<td></td>
					<td class="content">
						<textarea id="content" name="content">${board.content }</textarea>
					</td>
				</tr>
			</table>
			<div class="buttons">
				<button type="button" 
				        onclick="check(this.form)">수정</button>
			</div>
			</form>
		</div>
		<div class="footer">
		
		</div>
	</div>
	<!-- bootstrap -->
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/js/bootstrap.min.js"></script>
	<!-- summernote -->
	<script src="http://cdnjs.cloudflare.com/ajax/libs/summernote/0.8.9/summernote.js"></script>
	<script src="http://cdnjs.cloudflare.com/ajax/libs/summernote/0.8.9/lang/summernote-ko-KR.min.js"></script>
	<script>
		$("#content").summernote({
			height: 300,
			focus: true,
			lang: 'ko-KR',
			callbacks:{
				onImageUpload: function(files, editor, welEditable){
					sendFile(files[0], editor, welEditable);
				}
			}
		});
		
		function sendFile(file, editor, welEditable){
			var data = new FormData();
			data.append('upload', file);
			
			$.ajax({
				url:"${pageContext.request.contextPath}/board/fileupload",
				contentType: false,
				processData: false,
				data:data,
				type:"post",
				success:function(data){
					var $img = $('<img>').attr('src', data.url);
					$('#content').summernote('insertNode', $img[0]);
				},
				error:function(error){
					console.log(error);
				}
			});
		}
	</script>
</body>
</html>