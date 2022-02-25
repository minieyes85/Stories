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

<script type="text/javascript" src="/static/js/userUpdate.js"></script>
<link rel="stylesheet" href="/static/css/main.css" type="text/css">
<link rel="stylesheet" href="/static/css/userUpdate.css" type="text/css">

<meta charset="UTF-8">
<title>Stories</title>
</head>
<body>

	<c:import url="/WEB-INF/jsp/include/header.jsp"></c:import>

	<section class="d-flex align-items-center justify-content-center">
		<!-- 
		${signedUser.id }
		${signedUser.loginId }
		${signedUser.userName }
		${signedUser.email }
		${signedUser.createdAt }
		${signedUser.updatedAt }
		 -->
		
		<div id="userUpdateForm" class="border-1 rounded">
			<div class="p-2">
				<div class="mb-2 text-center fs-3">
					회원정보 수정
				</div>
				<div class="mb-2">
					<span>아이디</span>
					<input type="text" class="form-control" value="${signedUser.loginId }" disabled>
				</div>
				<div class="mb-2">
					<span>사용자 이름</span>
					<input type="text" id="updateUserInfoUserName" class="form-control" value="${signedUser.userName }">
				</div>
				<div class="mb-2">
					<span>이메일 주소</span>
					<input type="text" id="updateUserInfoEmail" class="form-control" value="${signedUser.email }">
				</div>
				<div class="mb-2">
					<span>현재 패스워드</span>
					<div class="input-group">
						<input id="updatedUserInfoPassword" type="password" class="form-control">
						<button id="updatedUserInfoPasswordBtn" class="btn btn-outline-secondary" type="button">확인</button>
					</div>
				</div>
				<div class="mb-2">
					<span>새로운 패스워드</span>
					<input type="password" id="updateUserInfoNewPassword" class="form-control">
				</div>
				<div class="mb-3">
					<span>새로운 패스워드 확인</span>
					<input type="password" id="updateUserInfoNewPasswordCheck" class="form-control">
				</div>
				<div class="d-grid gap-2">
					<button id="userUpdateBtn" type="button" class="btn btn-block btn-primary" disabled>회원정보 수정</button>
				</div>
			</div>
		</div>
	
	</section>

	<c:import url="/WEB-INF/jsp/include/footer.jsp"></c:import>

</body>
</html>