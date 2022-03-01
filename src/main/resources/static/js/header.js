$(document).ready(function(){
	
	//for header
	
	//로그인
	$("#signInBtn").on("click",function(e){
		e.preventDefault();
		var signInId = $("#signInId").val();
		var signInPassword = $("#signInPassword").val();
		
		// 입력 값 체크
		if(signInId == ""){
			alert("아이디를 입력하세요.");
			return;
		}
		
		if(signInPassword == "") {
			alert("비밀번호를 입력하세요.");
			return;
		}
		
		$.ajax({
			type: "post",
			url: "/user/signIn",
			data: {
				"loginId": signInId,
				"password": signInPassword
			},
			success: function(data){
				if(data.result == "success"){
					alert("안녕하세요. " + data.userName + "님");
					location.reload();
				} else {
					alert("로그인에 실패 하였습니다.");
				}
			},
			error: function(){
				alert("error");
			}
		});
		
	});
	
	//로그아웃
	$("#logoutBtn").on("click",function(){
		$.ajax({
			type: "get",
			url: "/user/signOut",
			success: function(data){
				if(data.result == "success"){
					location.reload();
				} else {
					alert("로그아웃에 실패 하였습니다.");
				}
			},
			error: function(){
				alert("error");
			}
		})
	});
	
	
	// 아이디 중복 체크
	$("#idDuplicationCheckBtn").on("click",function(){
		var signUpId = $("#signUpId").val();
		
		$.ajax({
			type: "get",
			url: "/user/isDuplicatedId",
			data: {
				"loginId": signUpId
			},
			success: function(data){
				if(data.isDuplicated == "true"){
					alert("이미 존재하는 아이디 입니다.");
				} else {
					alert("사용 가능한 아이디 입니다.");
					$("#signUpBtn").removeAttr("disabled");
				}
			},
			error: function(){
				alert(error);
			}
		});
	});
	
	// 아이디 체크 후에 다시 아이디 변경시 회원가입 버튼 block
	$("#signUpId").on("change",function(){
		$("#signUpBtn").attr("disabled", true);
	});
		
	
	//회원가입
	$("#signUpBtn").on("click",function(){
		//입력 값 체크
		var signUpId = $("#signUpId").val();
		var signUpUserName = $("#signUpUserName").val();
		var signUpEmail = $("#signUpEmail").val();
		var signUpPassword = $("#signUpPassword").val();
		var signUpPasswordcheck = $("#signUpPasswordcheck").val();
		
		if(signUpId == ""){
			alert("아이디를 입력 하세요.");
			return;
		};
		
		if(signUpUserName == ""){
			alert("사용자 이름을 입력 하세요.");
			return;
		};
		
		if(signUpEmail == ""){
			alert("이메일 주소를 입력 하세요.");
			return;
		};
		
		if(signUpPassword == ""){
			alert("비밀번호를 입력하세요");
			return;
		};
		
		if(signUpPassword != signUpPasswordcheck){
			alert("비밀번호가 일치하지 않습니다.");
			return;
		};		
		
		// 회원가입 진행
		$.ajax({
			type: "post",
			url: "/user/signUp",
			data: {
				"loginId": signUpId,
				"userName": signUpUserName,
				"email": signUpEmail,
				"password": signUpPassword
			},
			success: function(data) {
				if(data.result == "success"){
					alert(signUpUserName + "님 회원가입을 환영 합니다.");
					$("#signUpModal").modal('hide');
					$("#signInModal").modal('show');
				} else {
					alert("회원가입 실패, 관리자에게 문의 하세요.");
				}
			},
			error: function(){
				alert("error");
			}
		})
		
		
		
	});
	
});