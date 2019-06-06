<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>停车信息</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="../resource/jquery.mobile-1.4.5.css" />
<script src="../resource/jquery-1.12.0.min.js" type="text/javascript"></script>
<script src="../resource/jquery.mobile-1.4.5.js" type="text/javascript"></script>
</head>
<body>
<div data-role="page" data-dom-cache="false">
	<!-- <div data-role="header">
		<h1>车牌号码</h1>
	</div>/header -->
	<form action="history.shtml" id="buyform" name="buyform" method="post">
	<div role="main" class="ui-content">
			<div class="ui-field-contain">
			    <label for="textinput-1">当前没有停车缴费信息，可以点击按钮查看历史欠缴费！</label>
			</div>	
			<div class="ui-field-contain">		
    			<label for="textinput-1">
					<input type="hidden" name="car_no" id="car_no" value="${carNo}"/>
					<input type="hidden" name="dataStatus" id="dataStatus" value="1"/>
					<input type="hidden" name="flag" id="flag" value="${flag}"/>
				</label>
			    <button type="submit" id="pay" class="ui-btn ui-corner-all ui-shadow ui-btn-all" data-rel="popup">历史欠费</button>
			</div>
	</div>
	</form>
	<div data-role="footer">
		<h4>合肥城市泊车投资管理有限公司</h4>
	</div><!-- /footer -->
</div><!-- /page -->
</body>
</html>