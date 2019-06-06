<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
   <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>车辆停车记录信息</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="../resource/jquery.mobile-1.4.5.css" />
<script src="../resource/jquery-1.12.0.min.js" type="text/javascript"></script>
<script src="../resource/jquery.mobile-1.4.5.js" type="text/javascript"></script>
</head>
<body>
<div data-role="page" data-dom-cache="false">
	<!--<div data-role="header">
		<h1>车辆停车记录信息</h1>
	</div> /header -->
	<div>
		<table margin="0">
            <tbody>
	        	<c:forEach var="userweixin" items="${userWeiXinList}">
	        		<tr>
	                    <th>${userweixin.car_no}</th>
	                    <td><a href="history.shtml?car_no=${userweixin.car_no}&dataStatus=${dataStatus}" data-transition="fade" class="ui-btn ui-corner-all ui-shadow ui-btn-inline">查看详情</a></td>
	            		<td><a href="del.shtml?car_no=${userweixin.car_no}"  data-transition="fade" class="ui-btn ui-corner-all ui-shadow ui-btn-inline">解除绑定</a></td>
	                </tr>
	        	</c:forEach>
            </tbody>
        </table>
	</div>
	<div data-role="footer">
		<h4> 合肥城市泊车投资管理有限公司</h4>
	</div><!-- /footer -->
</div><!-- /page -->
</body>
</html>