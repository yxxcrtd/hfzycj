<%@page import="com.wxpay.util.SHA1"%>
<%@page import="java.util.Arrays"%>
<%@page import="com.wxpay.util.Sha1Util"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<%--
//自定义 token
	String TOKEN = "xishengheng20140318zhangwei";
	// 微信加密签名
    String signature = request.getParameter("signature");
    // 随机字符串
    String echostr = request.getParameter("echostr");
    // 时间戳
    String timestamp = request.getParameter("timestamp");
    // 随机数
    String nonce = request.getParameter("nonce");
    String[] str = { TOKEN, timestamp, nonce };
	Arrays.sort(str); // 字典序排序
	String bigStr = str[0] + str[1] + str[2];
	// SHA1加密
	String digest = new SHA1().getDigestOfString(bigStr.getBytes()).toLowerCase();
	// 确认请求来至微信
	if (digest.equals(signature)) {
		response.getWriter().print(echostr);
	}

	out.print(request.getParameter("echostr"));
	out.print("aaa");
--%>

<script type="text/javascript">
//登陆页面跳转
	window.onload=function(){
		window.location.href="${pageContext.request.contextPath}/weixin/";
	}
</script>
</body>
</html>