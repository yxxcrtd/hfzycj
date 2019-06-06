<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>停车支付</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="<%=request.getContextPath()%>/resource/jquery.mobile-1.4.5.css" />
<script src="<%=request.getContextPath()%>/resource/jquery-1.12.0.min.js" type="text/javascript"></script>
<script src="<%=request.getContextPath()%>/resource/jquery.mobile-1.4.5.js" type="text/javascript"></script>
</head>
<body>
<div data-role="page" data-dom-cache="false">
	<!--<div data-role="header">
		<h1>车辆停车记录信息</h1>
	</div> /header -->
	<form action="history" id="buyform" name="buyform" method="post">
	<div role="main" class="ui-content">
		<c:forEach var="parkvo" items="${parkVo.data}">
				<div class="ui-field-contain">		
	    			<label for="textinput-1">
						车 牌 号：${parkvo.carNo}
					</label>
				</div>
				<div class="ui-field-contain">		
					<label for="textinput-1">
						订单类型：
						<c:if test="${parkvo.dataType==1}">
							商业停车场停车
						</c:if>
						<c:if test="${parkvo.dataType==0}">
							路面停车
						</c:if>
	                </label>
				</div>
				<div class="ui-field-contain">
					<label for="textinput-1">
							驶入时间：${parkvo.inTime}
                    </label>
            	</div>
            	<div class="ui-field-contain">
					<label for="textinput-1">
							停车场名称：${parkvo.parkName}
                    </label>
	            </div>
				<div class="ui-field-contain">		
	    			<label for="textinput-1">
					</label>
					<c:if test="${flag==1}">
						<button id="btn11" type="button" class="ui-btn ui-corner-all ui-shadow ui-btn-all" onclick="window.location.href='orderDetail.shtml?id=${parkvo.id}&dataType=${parkvo.dataType}&dataStatus=${dataStatus}&carNo=${parkvo.carNo}&inTime=${parkvo.inTime}&parkName=${parkvo.parkName}&flag=${flag}'">查看详情</button>
					</c:if>
					<c:if test="${flag==null || flag==''}">
						<button id="btn11" type="button" class="ui-btn ui-corner-all ui-shadow ui-btn-all" onclick="window.location.href='orderDetail.shtml?id=${parkvo.id}&dataType=${parkvo.dataType}&dataStatus=${dataStatus}&carNo=${parkvo.carNo}&inTime=${parkvo.inTime}&parkName=${parkvo.parkName}&flag=0'">查看详情</button>
					</c:if>
				    	
				    <!-- 
				    <a href="javascript:void(0)" title="${parkvo.id}-${parkvo.dataType}-${dataStatus}" data-transition="fade" class="ui-btn ui-corner-all ui-shadow ui-btn-inline" onclick="btnClick(this)">查看详情</a>
				    -->
				</div>
		</c:forEach>
	</div>
	</form>
	<div data-role="footer">
		<h4>合肥城市泊车投资管理有限公司</h4>
	</div><!-- /footer -->
</div><!-- /page -->
</body>
</html>