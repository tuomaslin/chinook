<%@ page language="java" contentType="text/html; utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!doctype html>
<html>
<head>
<title>Albums</title>
<link rel="stylesheet" href="https://unpkg.com/sakura.css/css/sakura-ink.css" type="text/css">
</head>
<body>
	<h2>List of albums</h2>
	
	<table>
		<thead>
			<tr>
				<th>Albums for <c:out value="${ artistName }"></c:out></th>
			</tr>
		</thead>

		<tbody>
			<c:forEach items="${ albums }" var="s">
				<tr id="album-${s.getAlbumId()}">

					<td class="album"><c:out value="${ s.getTitle() }"></c:out></td>

				</tr>
			</c:forEach>
		</tbody>
	</table>

</body>
</html>