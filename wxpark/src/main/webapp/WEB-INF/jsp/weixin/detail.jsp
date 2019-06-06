<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
<script type="text/javascript">
function gotos(a){
	window.location.href="orderDetail.shtml?param="+$(a).attr("name");
}
$(function(){
	//支付宝支付
	$("#alipay").click(function(){
		$("#radiochoice").val("0");
		$("#buyform").action="createpay.shtml";
		$("#buyform").submit();
	});
	
	
	//微信支付
	$("#wxpay").click(function(){
		//先请求一下数据，看结果
		$.post("<%=request.getContextPath()%>/weixin/wxpay.shtml",{"total_fee":$("#arrearAmounts").val(),"order_id":$("#order_id").val()},function(data){
    			if(data.status==200){
    				$("#appId").val(data.value.appId);
    				$("#timeStamp").val(data.value.timeStamp)
    				$("#nonceStr").val(data.value.nonceStr);
    				$("#package").val(data.value.prepay_ids);
    				$("#paySign").val(data.value.paySign)
    				callPay();
				}else{
					alert(data.msg);
				}
			});
	});
});


function callPay() {
    if (typeof WeixinJSBridge == "undefined") {
        if (document.addEventListener) {
            document.addEventListener('WeixinJSBridgeReady', onBridgeReady,
                    false);
        } else if (document.attachEvent) {
            document.attachEvent('WeixinJSBridgeReady', onBridgeReady);
            document.attachEvent('onWeixinJSBridgeReady', onBridgeReady);
        }
    } else {
        onBridgeReady();
    }
}

function onBridgeReady() {
    WeixinJSBridge.invoke('getBrandWCPayRequest', {
    	 "appId" :$("#appId").val(),//公众号名称，由商户传入     
         "timeStamp":$("#timeStamp").val(),//时间戳，自1970年以来的秒数     
         "nonceStr":$("#nonceStr").val(),//随机串     
         "package":$("#package").val(),
         "signType":"MD5",//"MD5"   
         "paySign":$("#paySign").val(),//微信签名 
    }, function(res) { // 使用以上方式判断前端返回,微信团队郑重提示：res.err_msg将在用户支付成功后返回    ok，但并不保证它绝对可靠。
    	alert(res);
    	if (res.err_msg == "get_brand_wcpay_request:ok") {
    		alert("支付成功");
        	//执行ajax请求
    		$.post("<%=request.getContextPath()%>/calc/createorder.shtml",{"didaprice":$("#buyprice").val(),"attach":$("#attach").val(),"orderid":$("#orderid").val()},function(data){
    			if(data.status==200){
					window.location.href="<%=request.getContextPath()%>/weixin/wxordersuccess.shtml";
				}else{
					alert(data.msg);
				}
			});
        }
        if (res.err_msg == "get_brand_wcpay_request:cancel") {
            alert("交易取消");
        }
        if (res.err_msg == "get_brand_wcpay_request:fail") {
            alert("支付失败");
        }
    });
}

</script>
</head>
<body>
<div data-role="page" data-dom-cache="false">
	<!--<div data-role="header">
		<h1>车辆停车记录信息</h1>
	</div> /header -->
	<form action="history" id="buyform" name="buyform" method="post">
	<div role="main" class="ui-content">
				<div class="ui-field-contain">		
	    			<label for="textinput-1">
						车 牌 号：${carNo}
					</label>
				</div>
				<div class="ui-field-contain">		
	    			<label for="textinput-1">
						停车总费用：￥${parkvo.data.amount}
					</label>
				</div>
				<div class="ui-field-contain">		
	    			<label for="textinput-1">
						欠费金额：￥${parkvo.data.arrearAmount}
					</label>
				</div>
				
				<div class="ui-field-contain">		
					<label for="textinput-1">
						订单类型：
						<c:if test="${parkvo.data.dataType==1}">
							商业停车场停车
						</c:if>
						<c:if test="${parkvo.data.dataType==0}">
							路面停车
						</c:if>
	                </label>
				</div>
				<div class="ui-field-contain">
					<label for="textinput-1">
							驶入时间：${inTime}
                    </label>
            	</div>
            	<div class="ui-field-contain">
            		<label for="textinput-1">
            			驶出时间:${parkvo.data.outTime}
            		</label>
            	</div>
            	<div class="ui-field-contain">
					<label for="textinput-1">
							停车场名称：${parkName}
                    </label>
	            </div>
	            <c:if test="${parkvo.data.payRecord!=null&&parkvo.data.payRecord.size()!=0}">
	            	<div class="ui-field-contain">
					<label for="textinput-1">
							缴费记录
                    </label>
	            	</div>
	            	<c:forEach items="${parkvo.data.payRecord}" var="payre">
	            		<div class="ui-field-contain">
							<label for="textinput-1">
									订单编号：${payre.orderId}
		                    </label>
		            	</div>
		            	<div class="ui-field-contain">
							<label for="textinput-1">
									支付时间：${payre.payTime}
		                    </label>
		            	</div>
		            	<div class="ui-field-contain">
							<label for="textinput-1">
									支付金额：${payre.realPay}
		                    </label>
		            	</div>
	            	</c:forEach>
	            </c:if>
				<div class="ui-field-contain">		
	    			<label for="textinput-1">
	    				<input type="hidden" id="radiochoice" name="radiochoice"/>
					</label>
					<!-- 显示是否具有支付功能-->
					<%----%>
					<c:if test="${parkvo.data.breakPay}">  
						<input type="hidden" name="order_id" value="${order_id}" id="order_id"/>
						<input type="hidden" name="arrearAmounts" value="${parkvo.data.arrearAmount}" id="arrearAmounts"/>
						
						<input type="hidden" name="appId" id="appId"/>
						<input type="hidden" name="timeStamp" id="timeStamp"/>
						<input type="hidden" name="nonceStr"  id="nonceStr"/>
						<input type="hidden" name="package" id="package"/>
						<input type="hidden" name="signType" id="signType"/>
						<input type="hidden" name="paySign"  id="paySign"/>
						
						
						<c:if test="${flag==null}">
							<button type="button" id="wxpay" class="ui-btn ui-corner-all ui-shadow ui-btn-all">微信支付</button>
						</c:if>
						<c:if test="${flag!=null}">
							<button type="button"  id="alipay" class="ui-btn ui-corner-all ui-shadow ui-btn-all">支付宝支付</button>
						</c:if>
					 </c:if>
					<%--
				    	<button type="button"  onclick="gotos(this);" id="pay"  class="ui-btn ui-corner-all ui-shadow ui-btn-all">查看详情</button>
				 	--%>
				</div>
	</div>
	</form>
	<div data-role="footer">
		<h4>合肥城市泊车投资管理有限公司</h4>
	</div><!-- /footer -->
</div><!-- /page -->
</body>
</html>