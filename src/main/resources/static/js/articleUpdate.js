/**
 * 
 */
$(document).ready(function(){

	$("#updateArticleBtn").on("click",function(){
		
		var bbsId = $(this).data("bbs-id");
		var articleId = $(this).data("article-id");
		var categoryId = $("#updateArticleCategory").val();
		var title = $("#updateArticleTitle").val();
		var content = $("#updateArticleContent").val();
		
		// 입력값 체크
		
		if(categoryId == 999){
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
			url: "/article/update",
			data: {
				"articleId": articleId,
				"categoryId": categoryId,
				"title": title,
				"content": content
			},
			success: function(data){
				if(data.result == "success"){
					alert("글이 성공적으로 수정 되었습니다.");
					location.href="/bbs?bbsId="+bbsId;
				} else {
					alert("글을 수정하는데 실패했습니다.");
				}
			},
			error: function(){
				alert("error");
			}
		});
				
	});
	
	$(".deleteTagBtn").on("click", function(){
		
		var tagId = $(this).data("tag-id");
				
		$.ajax({
			type: "post",
			url: "/tag/delete",
			data: {
				"tagId": tagId
			},
			success: function(data){
				if(data.result == "success"){
					location.reload();
				} else {
					alert("태그를 삭제하는데 실패했습니다.");
				}
			},
			error: function(){
				alert("error");
			}
		});
	});
});
