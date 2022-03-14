
$(document).ready(function(){
	
	$("#deleteArticleByAdmin").on("click", function(){
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
	
	$("#bbsTitleByAdmin").on("change", function(){
		
		var bbsId = $(this).val();
		
		$.ajax({
			
			type: "post",
			url: "/bbs/getCategories",
			data: {
				"bbsId": bbsId
			},
			success: function(data){
				$("#bbsCategoryByAdmin").empty();
				data.forEach(function(val){
					let string = "<option value=" + val.id + ">" + val.title + "</option>";
					$("#bbsCategoryByAdmin").append(
						string
					);
				})
			},
			error: function(){
				alert("error");	
			}			
		});
		
	});
	
	$("#changeBBSArticle").on("click", function(){
		var articleId = $(this).data("article-id");
		var targetBBSId = $("#bbsTitleByAdmin option:selected").val();
		var targetCategoryId = $("#bbsCategoryByAdmin option:selected").val();
		
		$.ajax({
			type: "post",
			url: "/article/updateByAdmin",
			data: {
				"articleId": articleId,
				"bbsId": targetBBSId,
				"categoryId": targetCategoryId
			},
			success:function(data){
				if(data.result == "success"){
					alert("게시글 이동이 성공 하였습니다.");
					location.href="/bbs?bbsId="+targetBBSId;
				} else {
					alert("게시글 이동이 실패 하였습니다.");					
				}
			},
			error:function(){
				alert("error");	
			}
		});
		
	})
});