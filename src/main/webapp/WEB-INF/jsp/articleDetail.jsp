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
				
				<div class="me-2">
					<c:if test="${userId eq article.userId}">
					<div class="dropdown">
						<a class="dropdown-toggle" href="#"
							role="button" id="dropdownMenuLink" data-bs-toggle="dropdown"
							aria-expanded="false"></a>

						<ul class="dropdown-menu" aria-labelledby="dropdownMenuLink">
							<li><a class="dropdown-item" href="/article/updateView?articleId=${article.id}">수정</a></li>
							<li><a class="dropdown-item" href="#" id="deleteArticleBtn" data-article-id="${article.id}" data-bbs-id="${bbs.id}">삭제</a></li>
						</ul>
					</div>
					</c:if>
				</div>
			</div>
			<c:if test="${article.imagePath ne null }">
				<div class="ms-2 me-2 mb-5 d-flex justify-content-center">
					<img id="articleImage"  src="${article.imagePath}"/>
				</div>
			</c:if>
			
			<div id="articleContent" class="ms-2 me-2 mb-5 d-flex justify-content-between">
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
			
			<hr>
			
			<div class="mb-2">
				<c:forEach var="comment" items="${comments }">
					<div class="mb-1">
						<div class="commentUserNameBox p-1 mb-1 d-flex justify-content-between">
							<div>${comment.userName }</div>
							<div class="commentTime d-flex align-items-center"><fmt:formatDate value="${comment.updatedAt}" pattern="yyyy-MM-dd hh:mm"/></div>
						</div>
						<div id="commentContentBox${comment.id}" class="mb-2">${comment.content }</div>
						<div id="commentContentUpdateBox${comment.id}" class="mb-2 me-1" hidden="true">
							<input id="commentContentUpdateInput${comment.id}" type="text" class="form-control form-control-sm mb-1" value="${comment.content }">
							<div class="d-flex justify-content-end">
								<button class="commentContentUpdateBtn btn btn-sm btn-primary" data-comment-id="${comment.id}">확인</button>
							</div>
						</div>
						<div class="d-flex justify-content-end mb-2">
							<c:if test="${userName ne null}">
								<div class="me-1">
									<button class="btn btn-sm btn-light">댓글</button>
								</div>
							</c:if>
							<c:if test="${comment.userId eq userId}">
								<div class="me-1">
									<button class="commentUpdateBtn btn btn-sm btn-secondary" data-comment-id="${comment.id}">수정</button>
								</div>
													
								<div class="me-1">
									<button class="commentDeleteBtn btn btn-sm btn-danger" data-comment-id="${comment.id}">삭제</button>
								</div>
							</c:if>
						</div>
					</div>
				</c:forEach>
			</div>
			<c:if test="${userName ne null}">
				<div class="mb-2">
					<div class="mb-1">
						<input id="inputCommentContent" type="text" class="form-control form-control-sm">
					</div>
					<div class="d-flex justify-content-end">
						<button id="inputCommentBtn" class="btn btn-sm btn-primary"
						data-user-id="${userId}" data-article-id="${article.id}" data-user-name="${userName}"
						>등록</button>
					</div>
				</div>
			</c:if>
						
		</div>
	
	
	
	</section>
	
	<c:import url="/WEB-INF/jsp/include/footer.jsp"></c:import>

	

</body>
</html>