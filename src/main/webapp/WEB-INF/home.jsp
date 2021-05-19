<%@ page language="java" contentType="text/html; utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!doctype html>
<html>
<head>
<title>Artist</title>
<link rel="stylesheet" href="https://unpkg.com/sakura.css/css/sakura-ink.css" type="text/css">
</head>
<body>
	<h2>Artists</h2>
	
	<form name="testi" method="post">
    	<input name="title" type="text" required placeholder="type item here..." autofocus /> <br />
    	<input type="submit" value="Add artist to list" />
	</form>
	
	<table>
		<thead>
			<tr>
				<th>List of all artists</th>
			</tr>
		</thead>
		
		<tbody>
			<c:forEach items="${ artists }" var="s">
				<tr id="artist-${s.getId()}">

					<td class="artist"><a href="/albums?ArtistId=${s.getId()}"><c:out value="${ s.getName() }"></c:out></a></td>
	
				</tr>
			</c:forEach>
		</tbody>
	</table>
	
</body>
</html>