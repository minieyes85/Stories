<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<header>
	<div
		class="d-flex align-items-center justify-content-center text-white">
		<div class="fs-3 fw-bold" id="logo">Stories</div>

		<div id="headerNav">
			<ul class="nav">
				<li class="nav-item"><a class="nav-link text-white" href="#">bbs1</a></li>
				<li class="nav-item"><a class="nav-link text-white" href="#">bbs2</a></li>
				<li class="nav-item"><a class="nav-link text-white" href="#">bbs3</a></li>
				<li class="nav-item"><a class="nav-link text-white" href="#">bbs4</a></li>
			</ul>
		</div>

		<div id="loginBox">
			<div id="loginBtn" class="border border-1 rounded p-1 bg-white"
				onclick="location.href='#'"
				data-bs-toggle="modal" data-bs-target="#signInModal">로그인</div>
		</div>

	</div>
</header>

<!-- Log in Modal -->
	<div class="modal fade" id="signInModal" tabindex="-1"
		aria-labelledby="exampleModalLabel" aria-hidden="true">
		<div class="modal-dialog modal-dialog-centered modal-sm">
			<div class="modal-content">
				<div class="modal-header">
					로그인
					<button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
				</div>
				<div class="modal-body">
					<form>
					<input id="signInId" type="text" class="form-control mt-1 mb-2" placeholder="ID"/>
					<input id="signInPassword" type="text" class="form-control mb-2" placeholder="Password"/>
					<div class="w-100 d-grid gap-2">
						<button id="signInBtn" type="submit" class="btn btn-sm btn-primary">로그인</button>
					</div>
					</form>
				</div>
				<div id="recommendSignUpBox" class="modal-footer">
					<div class="w-100 text-center">
						아직 회원이 아니신가요?
						<a href="#" class="text-decoration-none"
							data-bs-toggle="modal" data-bs-target="#signUpModal">회원가입 하기</a>
					</div>
				</div>
			</div>
		</div>
	</div>
	
<!-- Sign up Modal -->
	<div class="modal fade" id="signUpModal" tabindex="-1"
		aria-labelledby="exampleModalLabel" aria-hidden="true">
		<div class="modal-dialog modal-dialog-centered modal-sm">
			<div class="modal-content">
				<div class="modal-header">
					회원가입
					<button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
				</div>
				<div class="modal-body">
					<div class="d-flex align-items-center">
					<div class="input-group input-group-sm mb-2">
						<input id="signUpId" type="text" class="form-control"
							placeholder="ID"
							aria-label="Recipient's username" aria-describedby="basic-addon2">
						<div class="input-group-append">
							<button id="idDuplicationCheckBtn" class="btn btn-outline-secondary" type="button">중복확인</button>
						</div>
					</div>

				</div>
					<input id="signUpUserName" type="text" class="form-control form-control-sm mb-2"
						placeholder="User Name" />
					<input id="signUpEmail" type="text"
						class="form-control form-control-sm mb-2" placeholder="E-mail Address" />
					<input id="signUpPassword" type="password" class="form-control form-control-sm mb-2"
						placeholder="Password" />
					<input id="signUpPasswordcheck" type="password"	class="form-control form-control-sm mb-2" placeholder="Password check" />
					<div class="w-100 d-grid gap-2">
						<button id="signUpBtn" type="button" class="btn btn-sm btn-primary" disabled>회원가입</button>
					</div>
				</div>
			<div id="recommendSignUpBox" class="modal-footer">
					<div class="w-100 text-center">
						이미 가입하셨나요?
						<a href="#" class="text-decoration-none"
							data-bs-toggle="modal" data-bs-target="#signInModal">로그인 하기</a>
					</div>
				</div>
			</div>
		</div>
	</div>