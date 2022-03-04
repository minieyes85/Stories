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

<script type="text/javascript" src="/static/js/articleUpdate.js"></script>
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
				<select id="updateArticleCategory" class="form-select form-select-sm" data-selected-category-id="${article.categoryId}">
					<option value="999">카테고리</option>
					<c:forEach var="category" items="${categories }">
						<option value="${category.id }"
						<c:if test="${category.id eq article.categoryId}">selected = "selected"</c:if>>${category.title }</option>
					</c:forEach>						
				</select>
				<input id="updateArticleTitle" type="text" class="form-control form-control-sm" value="${article.title }">
			</div>
			
			<div class="mb-2 d-flex justify-content-between">
				<textarea id="updateArticleContent" class="form-control" rows="20">${article.content }</textarea>
			</div>

			<!-- 
			<div class="mb-2">
				<input id="updateArticleFile" type="file">
			</div>
			 -->
			
			<div class="mb-2">
				태그<br>
				<input id="updateArticleTag" type="text" class="form-control form-control-sm" placeholder="쉼표(,)를 이용하여 복수 등록">
			</div>
			
			<div>
			<c:choose>
				<c:when test="${tags eq null}">
				</c:when>
				<c:when test="${tags ne null }">
					<c:forEach var="tag" items="${tags }">
						<button class="deleteTagBtn btn btn-sm btn-light" data-tag-id="${tag.id}">${tag.title } X</button>
					</c:forEach>
				</c:when>
			</c:choose>
			</div>
			
			<div class="d-flex justify-content-end">
				<button id="updateArticleBtn" class="btn btn-primary" data-article-id="${article.id}" data-bbs-id="${bbs.id}">글 수정</button>
			</div>
		</div>	
	
	</section>
	
	<c:import url="/WEB-INF/jsp/include/footer.jsp"></c:import>

	

</body>
</html>