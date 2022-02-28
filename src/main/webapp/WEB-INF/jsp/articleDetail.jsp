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
			
			<div class="mb-2">
				<c:forEach var="category" items="${categories }">
					${category.title }
				</c:forEach>
			</div>
			
			<div id="articleCategory" class="mb-2 d-flex justify-content-between">
				category
			</div>
			
			<div id="articleTitle" class="mb-2 d-flex justify-content-between">
				${article.title}
			</div>
			
			<div id="articleUserName" class="mb-5 pt-1 pb-1 d-flex justify-content-between border-top border-2 border-dark">
				<div class="ms-2">
					<span>${article.userName}</span>
					<span id="articleTime">
						<fmt:formatDate value="${article.updatedAt}" pattern="yyyy-MM-dd hh:mm"/>
					</span>
				</div>
				
				<div class="me-2"><i class="bi bi-three-dots-vertical"></i></div>
			</div>
			
			<div id="articleContent" class="mb-5 d-flex justify-content-between">
				${article.content}
			</div>
			
			<div class="mb-2 d-flex justify-content-center">
				<i class="bi bi-hand-thumbs-up"></i>추천하기 추천갯수
			</div>
			
			<div class="mb-2">
				<a href="/bbs?bbsId=${bbs.id }">
					<i class="bi bi-card-list me-1"></i>
				</a>
			</div>
			
			<div class="bg-warning mb-2 d-flex justify-content-between">
				댓글들
			</div>
			
			<div class="bg-warning mb-2 d-flex justify-content-between">
				댓글 작성
			</div>
						
		</div>
	
	
	
	</section>
	
	<c:import url="/WEB-INF/jsp/include/footer.jsp"></c:import>

	

</body>
</html>