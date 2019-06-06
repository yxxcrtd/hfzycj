<!doctype html>
<html lang="en">
	<head>
		<meta charset="UTF-8" />
		<link rel="styleSheet" type="text/css" href="${request.contextPath}/css/manage.css" />
		<style type="text/css">
			li a { display: block; }
		</style>
	</head>
	
	<body style="background-color: #242424;">
		<ul id="menu" class="menu noaccordion expandfirst">
			<li>
				<a id="firstMenu" class="subMenu" href="javascript:;">常用功能</a>
				<ul>
                    <li style="list-style-type:disc;"><a target="mainFrame" href="${request.contextPath}/manage/information">资讯管理</a></li>
                    <li style="list-style-type:disc;"><a target="mainFrame" href="${request.contextPath}/manage/park">停车场信息</a></li>
                    <li style="list-style-type:disc;"><a target="mainFrame" href="${request.contextPath}/manage/feedback">反馈信息</a></li>
                    <#--<li style="list-style-type:disc;"><a target="mainFrame" href="${request.contextPath}/manage/vote">投票管理</a></li>-->
                    <li><a target="mainFrame" href="${request.contextPath}/manage/log">系统日志</a></li>
				</ul>
			</li>
            <li>
                <a class="subMenu" href="javascript:;">资讯管理</a>
                <ul>
					<#list informationTypeMap?keys as k>
                        <li><a target="mainFrame" href="${request.contextPath}/manage/information/edit/0?t=${k}">添加${informationTypeMap[k]}</a></li>
					</#list>
                    <li><a target="mainFrame" href="${request.contextPath}/manage/information">资讯管理</a></li>
                </ul>
            </li>
            <li>
                <a class="subMenu" href="javascript:;">停车场管理</a>
                <ul>
                    <li><a target="mainFrame" href="${request.contextPath}/manage/park/edit/0">添加停车场</a></li>
                    <li><a target="mainFrame" href="${request.contextPath}/manage/park">停车场信息</a></li>
                </ul>
            </li>
			<li>
				<a class="subMenu" href="javascript:;">网站设置</a>
				<ul>
                    <li><a target="mainFrame" href="${request.contextPath}/manage/menu">菜单配置</a></li>
                    <li><a target="mainFrame" href="${request.contextPath}/manage/communal">公共标签配置</a></li>
                    <li><a target="mainFrame" href="${request.contextPath}/manage/feedback">反馈信息</a></li>
				</ul>
			</li>
			<li>
                <a class="subMenu" href="javascript:;">系统管理</a>
				<ul>
                    <li><a target="mainFrame" href="${request.contextPath}/manage/seo">SEO设置</a></li>
                    <li><a target="mainFrame" href="${request.contextPath}/manage/logo">LOGO设置</a></li>
                    <li><a target="mainFrame" href="${request.contextPath}/manage/link">友情链接</a></li>
                    <li><a target="mainFrame" href="${request.contextPath}/manage/copyright">版权信息</a></li>
                    <li><a target="mainFrame" href="${request.contextPath}/manage/log">系统日志</a></li>
				</ul>
			</li>
            <li>
                <a class="subMenu" href="javascript:;">用户管理</a>
                <ul>
                    <li><a target="mainFrame" href="${request.contextPath}/manage/user/editPassword">修改密码</a></li>
                    <li><a target="mainFrame" href="${request.contextPath}/manage/user">用户信息</a></li>
                    <li><a target="mainFrame" href="${request.contextPath}/logout" onClick="javascript:return confirm('确认要注销吗?');">注销</a></li>
                </ul>
            </li>
		</ul>
		<script type="text/javascript" src="${request.contextPath}/js/jquery-1.11.1.min.js"></script>
		<script type="text/javascript" src="${request.contextPath}/js/public.min.js"></script>
	</body>
</html>