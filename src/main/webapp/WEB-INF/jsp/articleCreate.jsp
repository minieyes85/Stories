<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>	
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
<script
  src="https://code.jquery.com/jquery-3.6.0.min.js"
  integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4="
  crossorigin="anonymous"></script>

<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.8.1/font/bootstrap-icons.css">

<link href="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote-lite.min.css" rel="stylesheet">
<script src="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote-lite.min.js"></script>

<script type="text/javascript" src="/static/js/bbs.js"></script>
<link rel="stylesheet" href="/static/css/main.css" type="text/css">
<link rel="stylesheet" href="/static/css/article.css" type="text/css">

<meta charset="UTF-8">
<title>Stories</title>
</head>
<body>
	
	<c:import url="/WEB-INF/jsp/include/header.jsp"></c:import>

	<section class="d-flex justify-content-center">
		<div id="mainForm" class="container bg-white">
			<div id="bbsTitleBox" class="mt-3 mb-2">
			${bbs.title}
			</div>
			
			<div class="d-flex mb-2">
				<select id="createArticleCategory" class="form-select form-select-sm">
					<option value="999" selected>카테고리</option>
					<c:forEach var="category" items="${categories }">
						<option value="${category.id }">${category.title }</option>
					</c:forEach>						
				</select>
				<input id="createArticleTitle" type="text" class="form-control form-control-sm" placeholder="제목을 입력 해 주세요.">
			</div>
			
			<div class="mb-2 d-flex justify-content-between" id="createArticleContent">
			</div>
			
			<div class="mt-2 mb-2">
				<input id="createArticleFile" type="file">
			</div>
			
			<div class="mb-2">
				태그<br>
				<input id="createArticleTag" type="text" class="form-control form-control-sm" placeholder="쉼표(,)를 이용하여 복수 등록">
			</div>
			
			<div class="d-flex justify-content-end">
				<button id="createArticleBtn" class="btn btn-primary" data-bbs-id="${bbs.id}">글 등록</button>
			</div>
		</div>	
	
	</section>
	
	<c:import url="/WEB-INF/jsp/include/footer.jsp"></c:import>

	

</body>
</html>