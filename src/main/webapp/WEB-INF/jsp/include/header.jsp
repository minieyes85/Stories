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
					<input id="inputUserId" type="text" class="form-control mt-1 mb-2" placeholder="ID"/>
					<input id="inputPassword" type="text" class="form-control mb-2" placeholder="Password"/>
					<div class="w-100 d-grid gap-2">
						<button type="submit" class="btn btn-sm btn-primary">로그인</button>
					</div>
				</div>
				<div id="recommendSignUpBox" class="modal-footer">
					<div class="w-100 text-center">
						아직 회원이 아니신가요?
						<a href="#" class="text-decoration-none">회원가입 하기</a>
					</div>
				</div>
			</div>
		</div>
	</div>