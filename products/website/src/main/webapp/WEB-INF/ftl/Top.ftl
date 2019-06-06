<!doctype html>
<html lang="en">
	<head>
		<meta charset="UTF-8" />
		<link rel="styleSheet" type="text/css" href="${request.contextPath}/css/manage.css" />
	</head>
	
	<body>
		<div id="top">
			<div id="logo">
                <a href="/" target="_blank"><img src="${request.contextPath}/images/logob.png" width="170" height="50" alt="Logo" /></a>
			</div>
			<div id="welcome">后台管理中心</div>
			<div id="profile">
				欢迎您，${user.user_name}！&nbsp;&nbsp;&nbsp;&nbsp;
				<a target="mainFrame" href="${request.contextPath}/manage/user/editPassword" ><span class="red">修改密码</span></a>&nbsp;&nbsp;
				<a href="${request.contextPath}/logout" onClick="javascript:return confirm('确认要注销吗?');"><span class="red">注销</span></a>
			</div>
		</div>
	</body>
</html>