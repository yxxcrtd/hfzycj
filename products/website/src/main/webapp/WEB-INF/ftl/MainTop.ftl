<#include "Obj.ftl" />

<!doctype html>
<html lang="en">
	<head>
		<meta charset="UTF-8" />
		<title><@compress single_line=true><@getNameByObj active /></@compress></title>
		<link type="text/css" rel="styleSheet" href="${request.contextPath}/css/manage.min.css" />
        <script language="javascript" src="${request.contextPath}/js/jquery-1.11.1.min.js"></script>
	</head>
	
	<body>
		<div id="mainTop">
			<span id="mainTitle">
				<@compress single_line=true><@getNameByObj active /></@compress>
			</span>
			<span id="tips"><#if tips??>${tips}</#if></span>
			<#if ("changePassword" != active && "manage" != active && "seo" != active && "log" != active && "logo" != active && "copyright" != active)>
				<span id="new"><a class="button1" href="${request.contextPath}/manage/${active}/edit/0">添加<@compress single_line=true><@getNameByObj active /></@compress></a></span>
			</#if>
		</div>
		
		<div id="mainMain">
			<#--<div id="mainLeft"></div>-->
			<div id="mainCenter">
