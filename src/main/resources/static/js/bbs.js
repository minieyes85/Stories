/**
 * 
 */
 
 $(document).ready(function(){
	
	$("#createArticleBtn").on("click",function(){
		var bbsId = $(this).data("bbs-id");
		var category = $("#createArticleCategory").val();
		var title = $("#createArticleTitle").val();
		var content = $("#createArticleContent").val();
		var file = $("#createArticleFile").val();
		var tag = $("#createArticleTag").val();
		
		if(category == 999){
			alert("카테고리를 정해주세요.");
			return;
		}
		
		if(title == ""){
			alert("제목을 입력하세요.");
			return;
		}
		
		if(content == ""){
			alert("내용을 입력하세요.");
			return;
		}
		
		$.ajax({
			type: "post",
			url: "/article/create",
			data: {
				"bbsId": bbsId,
				"category": category,
				"title": title,
				"content": content
			},
			success: function(data){
				if(data.result == "success"){
					alert("글이 성공적으로 등록 되었습니다.");
					location.href="/bbs?bbsId=" + bbsId;
				} else {
					alert("글 등록이 실패했습니다.");
				}
			},
			error: function(){
				alert("error");
			}
		});		
	});
	
	$("#deleteArticleBtn").on("click",function(){
		var result = confirm("정말 삭제 하시겠습니까?");
		var articleId = $(this).data("article-id");
		var bbsId = $(this).data("bbs-id");
		
		if(result){
			
			$.ajax({
				type: "post",
				url: "/article/delete",
				data: {
					"articleId": articleId
				},
				success: function(data){
					if(data.result == "success"){
						alert("글이 삭제 되었습니다.");
						location.href="/bbs?bbsId="+bbsId;
					} else {
						alert("글을 삭제하는데 실패하였습니다.");
					}
					
				},
				error: function(){
					alert("error");
				}
			});
			
		} else {
			alert("글 삭제를 취소 하셨습니다.");
		}
	});
	
	$("#inputCommentBtn").on("click", function(){
		
		var articleId = $(this).data("article-id");
		var userId = $(this).data("user-id");
		var userName = $(this).data("user-name");
		var content = $("#inputCommentContent").val();
		
		$.ajax({
			type: "post",
			url: "/comment/create",
			data: {
				"articleId": articleId,
				"userId": userId,
				"userName": userName,
				"content": content
			},
			success: function(data){
				if(data.result == "success"){
					alert("댓글이 등록 되었습니다.");
					location.reload();
				} else {
					alert("댓글 등록에 실패하였습니다.");
				}
			},
			error: function(){
				alert("error");
			}
		});
	});
	
});