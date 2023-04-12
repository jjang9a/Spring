<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<jsp:include page="includes/header.jsp"></jsp:include>
<h3>도서 조회/수정</h3>

<table id="bookListTable">
	<thead>
		<tr>
			<th>도서번호</th>
			<th>도서명</th>
			<th>표지</th>
			<th>출판일자</th>
			<th>금액</th>
			<th>출판사</th>
			<th>도서소개</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach var="book" items="${list }">
			<tr>
				<td class="center"><c:out value="${book.bookNo }"></c:out></td>
				<td><c:out value="${book.bookName }"></c:out></td>
				<td><c:out value="${book.bookCoverimg }"></c:out></td>
				<td><fmt:parseDate value="${book.bookDate }" var="bookdate" pattern="yyyy-MM-dd"></fmt:parseDate>
				<fmt:formatDate pattern="yyyy/MM/dd" value="${bookdate }" /></td>
				<td><fmt:formatNumber value="${book.bookPrice }" /></td>
				<td><c:out value="${book.bookPublisher }"></c:out></td>
				<td><c:out value="${book.bookInfo }"></c:out></td>
			</tr>
		</c:forEach>
	</tbody>
</table>

</body>
</html>