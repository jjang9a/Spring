<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<jsp:include page="includes/header.jsp"></jsp:include>
<h3>도서별 대여매출현황</h3>

<table id="bookListTable">
	<thead>
		<tr>
			<th>도서번호</th>
			<th>도서명</th>
			<th>대여총계</th>
			<th>대여횟수</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach var="book" items="${list }">
			<tr>
				<td class="center"><c:out value="${book.bookNo }"></c:out></td>
				<td><c:out value="${book.bookName }"></c:out></td>
				<td><c:out value="${book.rentSum }"></c:out></td>
				<td><c:out value="${book.rentCnt }"></c:out></td>
			</tr>
		</c:forEach>
	</tbody>
</table>

</body>
</html>