<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">
<link rel="Shortcut Icon"
	href="<%=basePath%>resource/image/logo_small.png">
<title>日志下载</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">

<link rel="stylesheet" type="text/css"
	href="<%=basePath%>resource/css/style.css" media="all">

</head>

<body>
	<!-- Main -->
	<div id="main">
		<div class="cl">&nbsp;</div>
		<!-- Content -->
		<div id="content">
			<!-- Box -->
			<div class="box">
				<!-- Box Head -->
				<div class="box-head">
					<h2 class="left">日志列表</h2>
					<div><a id="refresh" class="ico refresh" href="<%=basePath%>View/LogFile/index?page=1">刷新</a></div>
					<!-- <div><a id="add" class="ico add" href="">新增</a></div> -->
				</div>
				<!-- End Box Head -->

				<!-- Table -->
				<div class="table">
					<table width="100%" border="0" cellspacing="0" cellpadding="0">
						<tr>
							<th class="al" width="13px"><input type="checkbox" class="checkbox" /></th>
							<th class="al" width="400px">文件名</th>
							<th class="al" width="200px">最后修改时间</th>
							<th class="al" width="100px">大小</th>
							<th class="ac" width="80px">操作</th>
						</tr>
						<c:forEach var="file" items="${files}" varStatus="status">
							<tr>
								<td><input type="checkbox" class="checkbox" value="${file.fileName}" /></td>
								<td><a class="filename" target="view_window" href="<%=basePath%>log/logs/${file.fileName}">${file.fileName}</a></td>
								<td><span class="ac">${file.fileTime}</span></td>
								<td><span class="ac">${file.fileSize}</span></td>
								<td><a class="ico download" href="<%=basePath%>log/logs/${file.fileName}" download="<%=basePath%>log/logs/${file.fileName}">下载</a></td>
							</tr>
						</c:forEach>
					</table>

					<!-- Pagging -->
					<div class="pagging">
						<div class="left">
							<span>显示</span> <span>${countDesc}</span>
						</div>
						<div class="next-left"><span id="statusDesc">${statusDesc}</span></div>
						<div class="right">
							<a class="page" href="<%=basePath%>View/LogFile/index?page=${lastPage}">上一页</a>
							<c:if test="${pageSize <= 4}">
								<c:forEach var="i" begin="1" end="${pageSize}">
									<c:if test="${i == page}">
										<a class="page-now" href="<%=basePath%>View/LogFile/index?page=<c:out value="${i}" />"><c:out value="${i}" /></a>
									</c:if>
									<c:if test="${i != page}">
										<a class="page" href="<%=basePath%>View/LogFile/index?page=<c:out value="${i}" />"><c:out value="${i}" /></a>
									</c:if>
								</c:forEach>
							</c:if>
							<c:if test="${pageSize > 4}">
								<c:forEach var="i" begin="1" end="3">
									<c:if test="${i == page}">
										<a class="a-now" href="<%=basePath%>View/LogFile/index?page=<c:out value="${i}" />"><c:out value="${i}" /></a>
									</c:if>
									<c:if test="${i != page}">
										<a class="page" href="<%=basePath%>View/LogFile/index?page=<c:out value="${i}" />"><c:out value="${i}" /></a>
									</c:if>
									<c:if test="${i == 3}">
										<span>...</span>
										<a class="page" href="<%=basePath%>View/LogFile/index?page=${pageSize}"><c:out value="${pageSize}" /></a>
									</c:if>
								</c:forEach>
							</c:if>
							<a class="page" href="<%=basePath%>View/LogFile/index?page=${nextPage}">下一页</a>
						</div>
					</div>
					<!-- End Pagging -->
				</div>
				<!-- Table -->
			</div>
			<!-- End Box -->
		</div>
		<!-- End Content -->
	</div>
</body>
</html>
