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
<link rel="stylesheet" href="/static/css/bbs.css" type="text/css">

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
			
			<div class="mb-2 d-flex justify-content-between">
				<div>
					<form class="d-flex">
						<c:choose>
							<c:when test="${isSearch eq false}">
							<input id="searchInput" class="form-control form-control-sm me-1">
							</c:when>
							<c:when test="${isSearch eq true}">
							<input id="searchInput" class="form-control form-control-sm me-1" value="${searchKeyWord}">
							</c:when>
						</c:choose>
						<button id="searchBtn" type="submit" class="bbsBtn btn btn-sm text-white" data-bbs-id="${bbs.id}">
							<i class="bi bi-search"></i> 검색
						</button>
					</form>
				</div>				
				
				<c:choose>
					<c:when test="${userName ne null}">
						<button class="bbsBtn btn btn-sm text-white"
							onclick="location.href='/article/createView?bbsId=${bbs.id}'">
							글쓰기 <i class="bi bi-pencil-square"></i>
						</button>
					</c:when>
					<c:when test="${userName eq null}">
					
					</c:when>				
				</c:choose>
			</div>
			
			<div>
				<table class="table table-sm">
					<thead class="text-center fw-bold">
						<tr>
							<td id="theadTitle">제목</td>
							<td id="theadUserName">작성자</td>
							<td id="theadRecommend">추천 수</td>
							<td id="theadDate">날짜</td>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="article" items="${articles}">
						<tr>
							<td>
								[${article.categoryName }] 
								<a href="/article/detailView?articleId=${article.articleId }" class="text-decoration-none text-black">${article.title }</a>
								<c:if test="${article.imagePath ne null}">
									<i class="bi bi-card-image ms-1"></i>
								</c:if>
								<c:if test="${article.commentNo > 0}">
									<span class="ms-1 text-primary">${article.commentNo}</span>
								</c:if>
								
							</td>
							<td class="text-center">
								${article.userName }
							</td>
							<td class="text-center">
								${article.recommendNo }
							</td>
							<td class="text-center">
								<fmt:formatDate value="${article.createdAt }" pattern="yy-MM-dd HH:mm"/>
							</td>
						</tr>
				</c:forEach>
					</tbody>
				</table>
			</div>
			
			<div class="d-flex justify-content-center">
				<table class="text-center">
					<tr>
						<c:choose>
						<c:when test="${isSearch eq false}">
							<c:choose>
								<c:when test="${fwdPage ne null}">
									<td class="pageListCell rounded"
									onclick="location.href='/bbs?bbsId=${bbs.id}&page=${fwdPage}'"><i class="bi bi-skip-backward"></i></td>
								</c:when>
								<c:when test="${fwdPage eq null }">
									<td class="pageListNone"></td>
								</c:when>
							</c:choose>
							
							<c:forEach var="pageNo" items="${pageNOs}">
								<c:choose>
									<c:when test="${pageNo eq pageNO}">
										<td class="pageListCell rounded fw-bold"
										onclick="location.href='/bbs?bbsId=${bbs.id}&page=${pageNo}'">${pageNo}</td>
									</c:when>
									<c:when test="${pageNo ne pageNO}">
										<td class="pageListCell rounded"
										onclick="location.href='/bbs?bbsId=${bbs.id}&page=${pageNo}'">${pageNo}</td>
									</c:when>
								</c:choose>
							</c:forEach>
							
							<c:choose>
								<c:when test="${awdPage ne null}">
									<td class="pageListCell rounded"
								onclick="location.href='/bbs?bbsId=${bbs.id}&page=${awdPage}'"><i class="bi bi-skip-forward"></i></td>
								</c:when>
								<c:when test="${awdPage eq null }">
									<td class="pageListNone"></td>
								</c:when>
							</c:choose>
						</c:when>
						<c:when test="${isSearch eq true}">
							<c:choose>
								<c:when test="${fwdPage ne null}">
									<td class="pageListCell rounded"
									onclick="location.href='/bbs?bbsId=${bbs.id}&page=${fwdPage}'"><i class="bi bi-skip-backward"></i></td>
								</c:when>
								<c:when test="${fwdPage eq null }">
									<td class="pageListNone"></td>
								</c:when>
							</c:choose>
							
							<c:forEach var="pageNo" items="${pageNOs}">
								<c:choose>
									<c:when test="${pageNo eq pageNO}">
										<td class="pageListCell rounded fw-bold"
										onclick="location.href='/bbs?bbsId=${bbs.id}&page=${pageNo}&search=${searchKeyWord}'">${pageNo}</td>
									</c:when>
									<c:when test="${pageNo ne pageNO}">
										<td class="pageListCell rounded"
										onclick="location.href='/bbs?bbsId=${bbs.id}&page=${pageNo}&search=${searchKeyWord}'">${pageNo}</td>
									</c:when>
								</c:choose>
							</c:forEach>
							
							<c:choose>
								<c:when test="${awdPage ne null}">
									<td class="pageListCell rounded"
								onclick="location.href='/bbs?bbsId=${bbs.id}&page=${awdPage}'"><i class="bi bi-skip-forward"></i></td>
								</c:when>
								<c:when test="${awdPage eq null }">
									<td class="pageListNone"></td>
								</c:when>
							</c:choose>
						</c:when>
						</c:choose>
					</tr>
				</table>
			</div>
			
			
			
			
			
		</div>	
	</section>
	
	<c:import url="/WEB-INF/jsp/include/footer.jsp"></c:import>

	

</body>
</html>