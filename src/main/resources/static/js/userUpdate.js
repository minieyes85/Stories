/**
 * 
 */
 
 $(document).ready(function(){
	$("#updatedUserInfoPasswordBtn").on("click",function(){
		var presentPassword = $("#updatedUserInfoPassword").val();
		
		$.ajax({
			type: "post",
			url: "/user/checkPassword",
			data: {
				"password": presentPassword
			},
			success: function(data){
				if(data.result == "true"){
					alert("확인 되었습니다.");
					$("#updatedUserInfoPassword").attr("disabled", true);
					$("#updatedUserInfoPasswordBtn").attr("disabled", true);
					$("#userUpdateBtn").attr("disabled", false);
				} else {
					alert("패스워드를 다시 확인해주세요.");
					$("#updatedUserInfoPassword").val("");
				}
			},
			error: function(){				
			}
		});		
	});
	
	$("#userUpdateBtn").on("click", function(){
		
		var userName = $("#updateUserInfoUserName").val();
		var email = $("#updateUserInfoEmail").val();
		
		var password = $("#updateUserInfoNewPassword").val();
		var passwordchk = $("#updateUserInfoNewPasswordCheck").val();
		
		if(password != passwordchk){
			alert("패스워드가 일치하지 않습니다.");
			$("#updateUserInfoNewPassword").val("");
			$("#updateUserInfoNewPasswordCheck").val("");
			return;
		};
		
		$.ajax({
			type: "post",
			url: "/user/update",
			data: {
				"userName": userName,
				"email": email,
				"password": password
			},
			success: function(data){
				if(data.result == "success"){
					alert("회원정보가 변경 되었습니다.");
					location.href="/";
				} else {
					alert("회원정보 변경에 실패하였습니다.");
				};
			},
			error: function(){
				alert("error");
			}
		});
		
		
		
	});
	
});