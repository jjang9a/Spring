<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<jsp:include page="includes/header.jsp"></jsp:include>
<h4>도서 등록</h4>

<form action="add" id="addForm" method="post">
	<table>
		<tr>
			<th>도서번호</th>
			<td><input type="text" name="bookNo" value="${bno}"></td>
		</tr>
		<tr>
			<th>도서명</th>
			<td><input type="text" name="bookName"></td>
		</tr>
		<tr>
			<th>도서표지</th>
			<td><input type="text" name="bookCoverimg"></td>
		</tr>
		<tr>
			<th>출판일자</th>
			<td><input type="text" name="bookDate"></td>
		</tr>
		<tr>
			<th>금액</th>
			<td><input type="text" name="bookPrice"></td>
		</tr>
		<tr>
			<th>출판사</th>
			<td><input type="text" name="bookPublisher"></td>
		</tr>
		<tr>
			<th>도서소개</th>
			<td><textarea name="bookInfo"></textarea></td>
		</tr>
	</table>
		<button type="button" id="submitBtn">등록</button>
		<button type="button">조회</button>
</form>

<script>
var addForm = $('#addForm');
$('#submitBtn').on('click', function(e){
	console.log(addForm);
  	e.preventDefault();
	if(!addForm.find('input[name="bookName"]').val()){
		alert('도서명이 입력되지 않았습니다')
		addForm.find('input[name="bookName"]').focus()
		return;
	}
	addForm.submit(); 
})

</script>

</body>
</html>