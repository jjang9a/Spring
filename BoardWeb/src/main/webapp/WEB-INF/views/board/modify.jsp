<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<jsp:include page="../includes/header.jsp"></jsp:include>
<div class="row">
	<div class="col-lg-12">
		<h1 class="page-header">Board Modify</h1>
	</div>
	<!-- /.col-lg-12 -->
</div>
<div class="row">
	<div class="col-lg-12">
		<div class="panel panel-default">
			<div class="panel-heading">Board Modify Page</div>
			<!-- /.panel-heading -->
			<div class="panel-body">
				<form action="/board/modify" method="post">
					<input type="hidden" id="pageNum" name="pageNum" value="${cri.pageNum }">
					<input type="hidden" id="amount" name="amount" value="${cri.amount }">
					<input type="hidden" name="type" value="${cri.type }">
					<input type="hidden" name="keyword" value="${cri.keyword }">
					<div class="form-group">
						<label>Title</label> <input class="form-control" type="text"
							name="bno" value="${board.bno }" readonly>
					</div>
					<div class="form-group">
						<label>Title</label> <input class="form-control" type="text"
							name="title" value="${board.title }">
					</div>
					<div class="form-group">
						<label>Writer</label> <input class="form-control" type="text"
							name="writer" value="${board.writer }" readonly>
					</div>
					<div class="form-group">
						<label>Text Area</label>
						<textarea class="form-control" name="content">${board.content }</textarea>
					</div>
					<button type="submit" data-oper="modify" class="btn btn-default">Modify</button>
					<button type="submit" data-oper="remove" class="btn btn-default">Remove</button>
					<button type="submit" data-oper="list" class="btn btn-default">List</button>
				</form>
			</div>
			<!-- /.panel-body -->
		</div>
		<!-- /.panel -->
	</div>
	<!-- /.col-lg-12 -->
</div>
<!-- /.row -->
<script>
	$(document).ready(function(){
		var formObj = $('form');
		
		$('button').on('click', function(e){ // 매개값 = 이벤트정보 e
			e.preventDefault(); // 기본기능 차단
			var operation = $(this).data('oper');
			console.log(operation);
			
			if(operation == 'remove'){
				formObj.attr('action', '/board/remove');
			} else if(operation == 'list'){
				// self.location = '/board/list';
				// return;
				
				// 현재 pageNum, amount를 clone() -> empty() -> append(clone item)
				var pageNumTag = formObj.find('input[name="pageNum"]').clone();
				var amountTag = formObj.find('input[name="amount"]').clone();
				formObj.attr('action', '/board/list').attr('method', 'get')
				// 목록은 parameter인 bno가 필요없으므로 비우고, 겟방식으로 바꿔서(지금 기본:post) 보냄
				formObj.empty();
				formObj.append(pageNumTag);
				formObj.append(amountTag);
				console.log(formObj)
			}
			formObj.submit(); // submit 호출
		})
	})

</script>

<jsp:include page="../includes/footer.jsp"></jsp:include>