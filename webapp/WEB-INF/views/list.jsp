<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<h1>전호번호 리스트</h1>

	<p>입력한 정보 내역입니다. ${pageContext.request.contextPath}</p>

	<c:forEach items="${personList}" var="pList">
		<table border="1">
			<tr>
				<td>이름</td>
				<td>${pList.name}</td>
			</tr>

			<tr>
				<td>핸드폰</td>
				<td>${pList.hp}</td>
			</tr>

			<tr>
				<td>회사</td>
				<td>${pList.company}</td>
			</tr>

			<tr>
				<td align="center"><a href="${pageContext.request.contextPath}/delete?personId=${pList.personId}">삭제</a></td>
				<td align="center"><a href="${pageContext.request.contextPath}/updateForm?personId=${pList.personId}">수정</a></td>
			</tr>
		</table>
		<br>
	</c:forEach>
	
	<a href="${pageContext.request.contextPath}/writeForm">추가번호 등록</a>
</body>
</html>