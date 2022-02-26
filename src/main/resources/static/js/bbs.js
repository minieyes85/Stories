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
});