<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>车牌号码不能为空</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="../resource/jquery.mobile-1.4.5.css" />
<script src="../resource/jquery-1.12.0.min.js" type="text/javascript"></script>
<script src="../resource/jquery.mobile-1.4.5.js" type="text/javascript"></script>
<script type="text/javascript">
$(function(){
	$("#pay").click(function(){
		window.location.href="history";
	});
});
</script>
</head>
<body>
<div data-role="page" data-dom-cache="false">
	<!--<div data-role="header">
		<h1>车牌号码不能为空</h1>
	</div> /header -->
	<form action="addCar" id="buyform" name="buyform" method="post">
	<div role="main" class="ui-content">
			<div class="ui-field-contain">
			    <label for="textinput-1">车牌号码不能为空</label>
			</div>
	</div>
	</form>
	<div data-role="footer">
		<h4>合肥城市泊车投资管理有限公司</h4>
	</div><!-- /footer -->
</div><!-- /page -->
</body>
</html>