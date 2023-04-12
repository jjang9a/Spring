<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>BOOK</title>
<style>
h2, h3{
	text-align : center;
}
nav{
	background-color : #aaa
}
nav ul{
    text-align: left;
    padding-right: 50px;
}
nav ul li{
    display: inline-block;
    padding: 20px;
}
nav a{
    text-decoration: none;
    color: white;
}
table, th, td{
	border : 1px solid;
}
#bookListTable th, td{
	padding : 5px;
}
.center{
	text-align : center;
}
</style>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.4/jquery.min.js"></script>
</head>
<body>
	<header>
		<h2>도서대여관리</h2>
		<nav>
			<ul>
				<li><a href="add">도서등록</a></li>
				<li><a href="list">도서목록조회/수정</a></li>
				<li><a href="rent">대여현황조희</a></li>
				<li><a href="index">홈으로</a></li>
			</ul>
		</nav>
	</header>