<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>My JSP 'vueStudy.jsp' page</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

<script type="text/javascript"
	src="<%=basePath%>resource/js/vue/vue-2.6.9.js"></script>
<script type="text/javascript">
	window.onload = function() {
		var app = new Vue({
			el : '#app',
			data : {
				message : 'Hello Vue!'
			}
		});

		var vm = new Vue({
			el : '#example',
			data : {
				message : 'Hello'
			},
			computed : {
				// 计算属性的 getter
				reversedMessage : function() {
					// `this` 指向 vm 实例
					return this.message.split('').reverse().join('')
				}
			}
		})
	}
</script>
</head>

<body>
	<div id="app">{{ message }}</div>
	<a v-bind:href="www.baidu.com">百度</a>
	<div id="example">
		<p>Original message: "{{ message }}"</p>
		<p>Computed reversed message: "{{ reversedMessage }}"</p>
	</div>
</body>
</html>
