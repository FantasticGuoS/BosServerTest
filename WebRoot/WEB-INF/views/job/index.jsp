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
<link rel="Shortcut Icon" href="<%=basePath%>resource/image/logo_small.png">
<title>任务管理</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<link rel="stylesheet" type="text/css" href="<%=basePath%>resource/css/style.css" media="all">

<script type="text/javascript" src="<%=basePath%>resource/js/jQuery/jquery-3.3.1.js"></script>
<script type="text/javascript" src="<%=basePath%>resource/js/job/job.js"></script>
<script type="text/javascript">
	$(document).ready(function(){
	});  
</script>
</head>

<body>
	<input id="status" type="hidden" value="">
	<!-- Main -->
	<div id="main">
		<div class="cl">&nbsp;</div>
		<!-- Content -->
		<div id="content">
			<!-- Box -->
			<div class="box">
				<!-- Box Head -->
				<div class="box-head">
					<h2 class="left">任务列表</h2>
					<div><a id="refresh" class="ico refresh" href="<%=basePath%>View/Job/index?page=1">刷新</a></div>
					<!-- <div><a id="add" class="ico add" href="">新增</a></div> -->
				</div>
				<!-- End Box Head -->

				<!-- Table -->
				<div class="table">
					<table width="100%" border="0" cellspacing="0" cellpadding="0">
						<tr>
							<th class="al" width="13px"><input type="checkbox" class="checkbox" /></th>
							<th class="al" width="200px">任务名</th>
							<th class="al" width="400px">任务描述</th>
							<th class="al" width="160px">表达式</th>
							<th class="ac" width="100px">所属任务组</th>
							<th class="ac" width="60px">当前状态</th>
							<th class="ac" width="300px">操作</th>
						</tr>
						<c:forEach var="job" items="${jobs}" varStatus="status">
							<tr>
								<td><input type="checkbox" class="checkbox" value="${job.id}" /></td>
								<td><input id="jobName_${job.id}" type="text" class="text-input text-input-name" value="${job.jobName}" disabled="disabled" /></td>
								<td><input id="description_${job.id}" type="text" class="text-input text-input-desc" value="${job.description}" /></td>
								<td><input id="cronExpression_${job.id}" type="text" class="text-input text-input-cro" value="${job.cronExpression}" /></td>
								<td><input id="groupName_${job.id}" type="text" class="text-input text-input-group" value="${job.groupName}" disabled="disabled" /></td>
								<td class="ac">
									<c:if test="${job.runStatus == 'NORMAL'}">
										<img id="jod_status_${job.id}" alt="正常" src="<%=basePath%>resource/css/images/sungeon_ok.png">
									</c:if>
									<c:if test="${job.runStatus == 'COMPLETE'}">
										<img id="jod_status_${job.id}" alt="完成" src="<%=basePath%>resource/css/images/sungeon_ok.png">
									</c:if>
									<c:if test="${job.runStatus == 'PAUSED'}">
										<img id="jod_status_${job.id}" alt="暂停" src="<%=basePath%>resource/css/images/sungeon_pause.png">
									</c:if>
									<c:if test="${job.runStatus == 'NONE'}">
										<img id="jod_status_${job.id}" alt="无" src="<%=basePath%>resource/css/images/sungeon_stop.png">
									</c:if>
									<c:if test="${job.runStatus == 'ERROR'}">
										<img id="jod_status_${job.id}" alt="错误" src="<%=basePath%>resource/css/images/sungeon_error.png">
									</c:if>
									<c:if test="${job.runStatus == 'BLOCKED'}">
										<img id="jod_status_${job.id}" alt="阻塞" src="<%=basePath%>resource/css/images/sungeon_stop.png">
									</c:if>
								</td>
								<td>
									<div>
										<c:if test="${job.runStatus == 'NORMAL'}">
											<a id="status_opera_${job.id}" class="ico pause" onclick="job.pauseJob(${job.id});">暂停</a>
										</c:if>
										<c:if test="${job.runStatus != 'NORMAL'}">
											<a id="status_opera_${job.id}" class="ico start" onclick="job.resumeJob(${job.id});">恢复</a>
										</c:if>
										<a id="job_trigger_${job.id}" class="ico start" onclick="job.triggerJob(${job.id});">立即执行一次</a>
										<a id="job_edit_${job.id}" class="ico edit" onclick="job.saveJob(${job.id});">修改并应用</a>
										<!-- <a href="<%=basePath%>Servlet/JobContorl/deleteJob?jobId=${job.id}" class="ico delete">删除</a> -->
									</div>
								</td>
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
							<a class="page" href="<%=basePath%>View/Job/index?page=${lastPage}">上一页</a>
							<c:if test="${pageSize <= 4}">
								<c:forEach var="i" begin="1" end="${pageSize}">
									<c:if test="${i == page}">
										<a class="page-now" href="<%=basePath%>View/Job/index?page=<c:out value="${i}" />"><c:out value="${i}" /></a>
									</c:if>
									<c:if test="${i != page}">
										<a class="page" href="<%=basePath%>View/Job/index?page=<c:out value="${i}" />"><c:out value="${i}" /></a>
									</c:if>
								</c:forEach>
							</c:if>
							<c:if test="${pageSize > 4}">
								<c:forEach var="i" begin="1" end="3">
									<c:if test="${i == page}">
										<a class="a-now" href="<%=basePath%>View/Job/index?page=<c:out value="${i}" />"><c:out value="${i}" /></a>
									</c:if>
									<c:if test="${i != page}">
										<a class="page" href="<%=basePath%>View/Job/index?page=<c:out value="${i}" />"><c:out value="${i}" /></a>
									</c:if>
									<c:if test="${i == 3}">
										<span>...</span>
										<a class="page" href="<%=basePath%>View/Job/index?page=${pageSize}"><c:out value="${pageSize}" /></a>
									</c:if>
								</c:forEach>
							</c:if>
							<a class="page" href="<%=basePath%>View/Job/index?page=${nextPage}">下一页</a>
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