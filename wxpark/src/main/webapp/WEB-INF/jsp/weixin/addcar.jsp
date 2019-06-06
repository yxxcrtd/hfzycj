<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>车牌号码</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="../resource/jquery.mobile-1.4.5.css" />
<script src="../resource/jquery-1.12.0.min.js" type="text/javascript"></script>
<script src="../resource/jquery.mobile-1.4.5.js" type="text/javascript"></script>
<script type="text/javascript">
$(function(){
	$("#pay").click(function(){
		if($("#carNo").val()==null || $("#carNo").val()==""){
			alert("您输入相应的车牌号码");
			return false;
		}
		if($("#carNo").val().length!=7){
			alert("车牌号码输入错误，请重新输入");
			return false;
		}
		//window.location.href="infos.shtml?buy_price="+$("#carNo").val();
		$("#buyform").submit();
	});
});
</script>
</head>
<body>
<div data-role="page" data-dom-cache="false">
	<!--
	<div data-role="header">
		<h1>车牌号码</h1>
	</div> /header -->
	<form action="addCar.shtml" id="buyform" name="buyform" method="post">
	<div role="main" class="ui-content">
			<div class="ui-field-contain">
			    <label for="textinput-1">请输入车牌号码:</label>
				<input name="carNo" id="carNo" placeholder="请输入车牌号码" value="" type="text">
				<input name="dataStatus" id="dataStatus" type="hidden" value="${dataStatus}"/>
				<input name="flag" id="flag" type="hidden" value="${flag}"/>
			</div>	
			<div class="ui-field-contain">		
    			<label for="textinput-1"></label>
			    <button type="button" id="pay" class="ui-btn ui-corner-all ui-shadow ui-btn-all" data-rel="popup">绑定车牌</button>
			</div>
	</div>
	</form>
	<div data-role="footer">
		<h4> 合肥城市泊车投资管理有限公司</h4>
	</div><!-- /footer -->
</div><!-- /page -->
</body>
</html>