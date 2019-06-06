<div id="link_title">友情链接</div>
<div id="link_content">
	<#if (list?? && 0 < list?size)>
		<#list list as l>
			<a href="<#if (!l.link_url?starts_with('http'))>http://</#if>${l.link_url}" target="_blank" title="${l.link_name}">${l.link_name}</a>
		</#list>
	</#if>
</div>