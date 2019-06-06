<#assign filterHTML = "com.hfzycj.util.FreemarkerFilterHTML"?new()>

<#macro getNameByObj obj>
	<#if obj??>
		<#if ("manage" == obj)>后台
		<#elseif ("seo" == obj)>SEO设置
		<#elseif ("logo" == obj)>LOGO设置
		<#elseif ("link" == obj)>友情链接
		<#elseif ("copyright" == obj)>版权信息
		<#elseif ("feedback" == obj)>反馈
		<#elseif ("category" == obj)>分类设置
		<#elseif ("information" == obj)>资讯
		<#elseif ("menu" == obj)>菜单导航
		<#elseif ("communal" == obj)>公共设施
		<#elseif ("park" == obj)>停车场基本信息
		<#elseif ("user" == obj)>用户信息
		<#elseif ("changePassword" == obj)>修改密码
		<#elseif ("log" == obj)>日志管理
		<#elseif ("vote" == obj)>投票
		<#else>Oops！
		</#if>
	</#if>
</#macro>

<!-- 资讯法规类型 -->
<#macro getinformationRule obj>
	<#if obj??>
		<#if ("0" == obj)>地方法规
		<#elseif ("1" == obj)>国家法规
		<#else>Oops！
		</#if>
	</#if>
</#macro>

<#macro getFeedbackStatus obj>
    <#if obj??>
        <#if (0 == obj)>待审核
        <#elseif (1 == obj)><span>通过</span>
		<#else>Oops！
		</#if>
    </#if>
</#macro>

<#macro getParkType obj>
	<#if obj??>
		<#if ("0" == obj)>地下停车场
		<#elseif ("1" == obj)><span>地面停车场</span>
		<#else>Oops！
		</#if>
	</#if>
</#macro>