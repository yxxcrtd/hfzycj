<%@page import="com.wxpay.util.GetWeiXinCode"%>
<%@page import="com.wxpay.config.WxPayConfig"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<title>小车嘀嗒订单</title>
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<link rel="stylesheet" href="<%=request.getContextPath()%>/resource/jquery.mobile-1.4.5.css" />
	<script src="<%=request.getContextPath()%>/resource/jquery-1.12.0.min.js"></script>
	<script src="<%=request.getContextPath()%>/resource/jquery.mobile-1.4.5.min.js"></script>
	<script src="<%=request.getContextPath()%>/resource/js/validate.js"></script>
	<script type="text/javascript">
		$(function(){
			$("#pay").click(function(){
				if($("input[type='radio']:checked").val()=="0"){
					$("#payform").submit();
				}else{
					callPay();
				}
			});
		});
	</script>
	<script type="text/javascript">    
    function onBridgeReady() {
        WeixinJSBridge.invoke('getBrandWCPayRequest', {
        	 "appId" : "${appid}",//公众号名称，由商户传入     
             "timeStamp" :"${timeStamp}",//时间戳，自1970年以来的秒数     
             "nonceStr" : "${nonce_str}",//随机串     
             "package" : "${prepay_ids}",
             "signType" : "MD5",//"MD5"   
             "paySign" : "${paySign}",//微信签名 
        }, function(res) { // 使用以上方式判断前端返回,微信团队郑重提示：res.err_msg将在用户支付成功后返回    ok，但并不保证它绝对可靠。
        	alert(res);
        	if (res.err_msg == "get_brand_wcpay_request:ok") {
        		alert("支付成功");
            	//执行ajax请求
        		$.post("<%=request.getContextPath()%>/calc/createorder.shtml",{"didaprice":$("#buyprice").val(),"attach":$("#attach").val(),"orderid":$("#orderid").val()},function(data){
        			if(data.status==200){
						window.location.href="<%=request.getContextPath()%>/calc/order.shtml?didaprice="+$("#buyprice").val()+"&orderid="+$("#orderid").val();
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
</script>
</head>
<body>
<div data-role="page" data-dom-cache="false">
	<div data-role="header">
		<h1>小车嘀嗒订单</h1>
	</div><!-- /header -->
	<div role="main" class="ui-content">
		 <form name="alipayment" action="<%=request.getContextPath()%>/calc/createpay.shtml" method="post" id="payform">
			<div class="ui-field-contain" id="showprice">
    			<label for="textinput-1">盗抢险价格：</label>
    			<input name="didaprice" id="didaprice"  placeholder="请输入新车购置价" value="${buy_price}" type="text">
    			<div data-role="popup" id="popupArrow" data-arrow="true">
					<p>您输入的3年盗抢险的价格必须是数字</p>
				</div>
			</div>
			<div class="ui-field-contain">
			    <fieldset data-role="controlgroup">
			        <legend>支付方式：</legend>
			         <input name="radiochoice" id="radio-choice-v-1b" value="1" type="radio" checked="checked">
			        <label for="radio-choice-v-1b"><img src="<%=request.getContextPath()%>/resource/images/wecha.png" style="width: 50px;height: 50px;text-align: center;"/>&nbsp;&nbsp;<span style="position: absolute;padding-top: 10px;">微信支付</span></label>
			        <input name="radiochoice" id="radio-choice-v-1a" value="0" type="radio">
			        <label for="radio-choice-v-1a"><img src="<%=request.getContextPath()%>/resource/images/alipay.png" style="width: 50px;height: 50px;text-align: center;"/>&nbsp;&nbsp;<span style="position: absolute;padding-top: 13px;">支付宝支付</span></label>
			    </fieldset>
			</div>						
			<div class="ui-field-contain" id="querod">		
    			<label for="textinput-1"></label>
			    <button type="button" id="pay" class="ui-shadow ui-btn ui-corner-all">支付</button>
			    <input type="hidden" name="attach" id="attach" value="${attach}"/>
			    <input type="hidden" name="orderid" id="orderid" value="${orderid}"/>
			    <input type="hidden" name="buyprice" id="buyprice" value="${buy_price}">
			</div>
		</form>
	</div>
	<div data-role="footer">
		<h4>深圳市熙盛恒科技有限公司</h4>
	</div><!-- /footer -->
</div><!-- /page -->
</body>
</html>
