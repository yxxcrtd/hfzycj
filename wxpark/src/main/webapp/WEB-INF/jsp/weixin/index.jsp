<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
 <div id="main">
		<div id="form">
			<form action="weixin/" method="post">
				<div id="nickName" name="nickName">
					账号：${userInfo.nickName} &nbsp;头像：<img alt="头像" src="${userInfo.headImage}">
				</div>
				<div id="sex" name="sex">
					性别：${userInfo.sex}
				</div>
				<div id="adress" name="adress">
					地址：${userInfo.province}-${userInfo.city}-${userInfo.country}
				</div>
				<div class="div">
					<input type="text" name="carNo" id="carNo" value="${userInfo.carNo}" /> &nbsp;
					<button type="submit" id="submit">绑定车牌号 </button>
				</div>
			</form>
		</div>
	</div>
</body>
</html>