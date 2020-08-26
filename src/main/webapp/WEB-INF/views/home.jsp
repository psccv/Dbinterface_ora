<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>
<html>
<head>
<title>Home</title>
<style>
table {border:1px solid black;}
tr, th, td {border:1px solid black;} 
</style>
</head>
<body>
	<h1>Hello world!</h1>

	<P>The time on the server is ${serverTime}.</P>
	<table class="bbsListTbl" summary="회원정보리스트">
		<caption class="hdd">회원정보 목록:제작자-${jspMaker}</caption>
		<thead>
			<tr>
				<th scope="col">번호</th>
				<th scope="col">사용자ID</th>
				<th scope="col">사용자암호</th>
				<th scope="col">사용자이름</th>
				<th scope="col">사용자이메일</th>
				<th scope="col">사용자등록일</th>
				<th scope="col">사용자수정일</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${memberList}" var="memberVO" varStatus="status">
			<tr>
				<td>${status.count}</td>
				<td>${memberVO.userid}</td>
				<td>${memberVO.userpw}</td>
				<td>${memberVO.username}</td>
				<td>${memberVO.email}</td>
				<td>${memberVO.regdate}</td>
				<td>${memberVO.updatedate}</td>
			</tr>
		</c:forEach>
			
		</tbody>
	</table>
</body>
</html>
