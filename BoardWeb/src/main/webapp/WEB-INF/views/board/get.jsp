<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<jsp:include page="../includes/header.jsp"></jsp:include>
<div class="row">
	<div class="col-lg-12">
		<h1 class="page-header">Board Read</h1>
	</div>
	<!-- /.col-lg-12 -->
</div>
<div class="row">
	<div class="col-lg-12">
		<div class="panel panel-default">
			<div class="panel-heading">Board Read Page</div>
			<!-- /.panel-heading -->
			<div class="panel-body">
				<div class="form-group">
					<label>Title</label> <input class="form-control" type="text"
						name="bno" value="${board.bno }" readonly>
				</div>
				<div class="form-group">
					<label>Title</label> <input class="form-control" type="text"
						name="title" value="${board.title }" readonly>
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
				<button type="submit" data-oper="list" class="btn btn-default">List</button>
				<form id="operForm" action="/board/modify" method="get">
					<input type="hidden" id="bno" name="bno" value="${board.bno }">
					<input type="hidden" id="pageNum" name="pageNum" value="${cri.pageNum }">
					<input type="hidden" id="amount" name="amount" value="${cri.amount }">
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
		var operForm = $('#operForm');
		$('button[data-oper="modify"]').on('click', function(){
			operForm.attr('action', '/board/modify').submit();
		})
		$('button[data-oper="list"]').on('click',function(){
			operForm.find('#bno').remove();
			// ㄴ 목록을 띄울때는 따로 parameter가 필요하지 않음 하지만 지금은 기본설정으로 parameter에 bno가 설정되어있음.
			// 이 작업을 하지 않으면 목록으로 넘어갔을때 주소창에 list?bno=? 형태로 뜨게 됨. 그렇게 하지않으려고 미리 파라메터를 제거해서 보냄.
			operForm.attr('action', '/board/list');
			operForm.submit();
		})
	})
</script>
<jsp:include page="../includes/footer.jsp"></jsp:include>