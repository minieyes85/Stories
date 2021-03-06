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

<link rel="stylesheet" href="/static/css/main.css" type="text/css">
<link rel="stylesheet" href="/static/css/section.css" type="text/css">

<meta charset="UTF-8">
<title>Stories</title>
</head>
<body>
	
	<c:import url="/WEB-INF/jsp/include/header.jsp"></c:import>

	<section class="d-flex justify-content-center">
		<div id="mainForm" class="bg-white d-flex align-content-start flex-wrap">
			<c:forEach var="bbsForMain" items="${allBBSForMain }">
			<div class="subForm">
				<table class="table table-sm">
					<thead>
						<tr>
							<td>
								<a href="/bbs?bbsId=${bbsForMain.bbs.id }" class="text-decoration-none">
									${bbsForMain.bbs.title}								
								</a>
							</td>							
						</tr>
					</thead>
					<tbody>
						<c:forEach var="article" items="${bbsForMain.articleList}">
							<tr>
								<td class="mainTable">
									<span class="me-1">[${article.categoryName}]</span>
									<span class="me-1">
										<a class="text-decoration-none text-black" href="/article/detailView?articleId=${article.articleId}">${article.title}</a>
									</span>
									<c:if test="${article.commentNo ne 0}">
									<span class="text-primary">${article.commentNo }</span>	
									</c:if>							
								</td>					
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
			</c:forEach>
		</div>
	
	
	
	</section>
	
	<c:import url="/WEB-INF/jsp/include/footer.jsp"></c:import>

	

</body>
</html>