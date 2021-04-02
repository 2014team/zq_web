<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>登录</title>
<link rel="stylesheet" type="text/css" href="/admin/css/login.css?t=<%=new java.util.Date().getTime() %>">
<script type="text/javascript" src="/js/jquery.min.js"></script>
<script type="text/javascript" src="/js/url_map.js?t=<%=new java.util.Date().getTime() %>"></script>
<script type="text/javascript" src="/js/http_util.js?t=<%=new java.util.Date().getTime() %>"></script>
<script type="text/javascript" src="/admin/js/login.js?t=<%=new java.util.Date().getTime() %>"></script>
</head>

<script type="text/javascript">
	$(function(){
		if (top.location.href != location.href) {
	         top.location.href ="/";
	     }
	})
</script>


<div id="wrapper" class="login-page">
	<div id="login_form" class="form">
		<form class="login-form" id="login_form_id">
			<h2>管理登录</h2>
			<input type="text" placeholder="用户名" value="admin" id="user_name"  />
			<input type="password" placeholder="密码" value="123456" id="password" />
			<button id="login">登 录</button>
		</form>
	</div>
</div>

</body>
</html>