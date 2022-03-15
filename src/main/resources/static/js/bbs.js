/**
 * 
 */
 
 $(document).ready(function(){
	
	$("#createArticleContent").summernote({
		tabsize: 1,
        height: 500,
        maxHeight: 500,
        lang : 'ko-KR',
        toolbar: [
          ['style', ['style']],
          ['font', ['bold', 'underline', 'clear']],
          ['color', ['color']],
          ['para', ['ul', 'ol', 'paragraph']],
          ['table', ['table']],
          ['insert', ['link']],
          ['view', ['codeview', 'help']]
        ]
      });
	
	$("#createArticleBtn").on("click",function(){
		var bbsId = $(this).data("bbs-id");
		var category = $("#createArticleCategory").val();
		var title = $("#createArticleTitle").val();
		var content = $("#createArticleContent").summernote("code");
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
				
		var formData = new FormData();
		formData.append("bbsId", bbsId);
		formData.append("category", category);
		formData.append("title", title);
		formData.append("content", content);
		formData.append("file", $("#createArticleFile")[0].files[0]);
		formData.append("tags", tag);
		
		$.ajax({
			type: "post",
			url: "/article/create",
			data: formData,
			
			enctype:"multipart/form-data",
			processData:false,
			contentType:false,
			
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
		
		if(content == "") {
			alert("댓글을 입력하세요.");
			return;
		}
		
		if(content.length >= 256){
			alert("댓글 글자수를 초과 하였습니다. (256자 제한)");
			return;
		}
		
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
	
	$(".commentDeleteBtn").on("click", function(){
		
		var flag = confirm("댓글을 삭제 하시겠습니까?");
		
		if(flag){
			var commentId = $(this).data("comment-id");
		
			$.ajax({
				type: "post",
				url: "/comment/delete",
				data: {
					"commentId": commentId
				},
				success: function(data){
					if(data.result == "success"){
						alert("댓글이 삭제 되었습니다.");
						location.reload();
					} else {
						alert("댓글 삭제에 실패하였습니다.");
					}
				},
				error: function(){
					alert("error");
				}
			});	
		} else {
			alert("댓글 삭제를 취소 하셨습니다.");
		}		
	});
	
	
	$(".commentUpdateBtn").on("click", function(){
		var commentId = $(this).data("comment-id");
		$("#commentContentBox"+commentId).attr("hidden", true);		
		$("#commentContentUpdateBox"+commentId).attr("hidden", false);
		$("#commentContentChangeBox"+commentId).hide();
	});
	
	$(".commentContentUpdateBtn").on("click", function(){
		var commentId = $(this).data("comment-id");
		var content = $("#commentContentUpdateInput"+commentId).val();
				
		$.ajax({
			type: "post",
			url: "/comment/update",
			data: {
				"commentId": commentId,
				"content" : content
			},
			success: function(data) {
				if (data.result == "success") {
					location.reload();
				} else {
					alert("댓글 수정에 실패하였습니다.");
				}
			},
			error: function() {
				alert("error");
			}
		});
		
	});
	
	$("#recommendBtn").on("click",function(){
		var articleId = $(this).data("article-id");
		var userId = $(this).data("user-id");
		
		if(userId == ""){
			alert("로그인이 필요합니다.");
			return;
		}
		
		$.ajax({
			type: "get",
			url: "/recommend",
			data: {
				"articleId": articleId
			},
			success: function(data) {
				if (data.result == "success") {
					location.reload();
				} else {
					alert("추천에 실패하였습니다.");
				}
			},
			error: function() {
				alert("error");
			}
		})

	});
	
	$("#searchBtn").on("click",function(e){
		e.preventDefault();
		
		var bbsId = $(this).data("bbs-id");
		var searchType = $("#searchSelect").val();
		
		
		if(searchType == 1){
			//제목 검색
			var searchInput = $("#searchTitleInput").val();
			
		} else if(searchType == 2){
			//글쓴이 검색
			var searchInput = $("#searchUserInput").val();
			
		} else if(searchType == 3){
			//카테고리 검색
			var searchInput = $("#searchCategory option:selected").val();
		}
				
		var targetUrl = "/bbs?bbsId="+bbsId+"&searchType="+searchType+"&searchInput="+searchInput;
				
		$.ajax({
			type: "get",
			success: function(){
				location.href=targetUrl;
			}
		});
	});
	
	$("#searchSelect").on("change",function(){
		
		var searchType = $(this).val();
		
		if(searchType == 1){
			$("#searchTitle").attr("hidden", false);
			$("#searchUser").attr("hidden", true);
			$("#searchCategory").attr("hidden", true);
		} else if(searchType == 2){
			$("#searchTitle").attr("hidden", true);
			$("#searchUser").attr("hidden", false);
			$("#searchCategory").attr("hidden", true);
		} else if(searchType == 3){
			$("#searchTitle").attr("hidden", true);
			$("#searchUser").attr("hidden", true);
			$("#searchCategory").attr("hidden", false);
		}
		
	});
	
	$(".reCommentOpenBtn").on("click", function(){
		var targetCommentId = $(this).data("comment-id");
		$("#reCommentBox"+targetCommentId).attr("hidden", false);	
	});
	
	$(".reCommentCloseBtn").on("click", function(){
		var targetCommentId = $(this).data("comment-id");
		$("#reCommentBox"+targetCommentId).attr("hidden", true);	
	});
	
	$(".reCommentCreateBtn").on("click", function(){
		
		var count = $(this).data("count");
		var userId = $(this).data("user-id");
		var userName = $(this).data("user-name");
		var articleId = $(this).data("article-id");
		var grpId = $(this).data("grp-id");
		var depth = $(this).data("depth");
		var content = $("#reCommentInput"+count).val();
				
		$.ajax({
			type: "post",
			url: "/reComment/create",
			data: {
				"userId": userId,
				"userName": userName,
				"articleId": articleId,
				"grpId": grpId,
				"depth": depth+1,
				"content": content
			},
			success: function(data){
				if(data.result == "success"){
					alert("대댓글이 성공적으로 등록 되었습니다.");
					location.reload();
				} else {
					alert("대댓글 작성에 실패했습니다.");
				}
			},
			error: function(){
				alert("error");
			}
			
		});
		
	});
	
	
	
	
});