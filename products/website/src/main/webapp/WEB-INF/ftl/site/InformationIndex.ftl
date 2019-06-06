<div class="title"><a href="/upload/${information.information_type}.html">${title}</a></div>
<ul>
	<#if (list?? && 0 < list?size)>
		<#list list as l>
			<#if ("news" == l.information_type && 0 == l_index)>
				<li>
                    <a href="/upload/information_${l.information_id}.html" target="_blank">
						<div id="news_first">
							<div id="news_img"><img src="<#if (l.information_attachment_url?? && 0 < l.information_attachment_url?length)>/upload/${l.information_attachment_url}<#else>./images/index_news_default.png</#if>" title="${l.information_title}" width="203" height="139" alt="${l.information_title}"></div>
							<div id="news_first_right">
								<div id="news_first_right_top" title="${l.information_title}"><#if (26 < l.information_title?length)>${l.information_title[0..25]}<#else>${l.information_title}</#if></div>
								<div id="news_first_right_bottom" title="${l.information_summary}"><#if (37 < l.information_summary?length)>${l.information_summary[0..36]}...<#else>${l.information_summary}</#if></div>
							</div>
						</div>
					</a>
                </li>
			<#else>
				<span class="<#if ("rules" == information.information_type)>point1<#else>point</#if>">&#183;</span>
				<li title="${l.information_title}">
					<a href="/upload/information_${l.information_id}.html" target="_blank">
						<span>
							<#if ("rules" == information.information_type)>
								<span class="span_rule">[<#list informationRuleMap?keys as k><#if (l.information_rule?string == k)>${informationRuleMap[k]}</#if></#list>]</span>
								<#if (17 < l.information_title?length)>${l.information_title[0..16]}<#else>${l.information_title}</#if>
							<#else>
								<#if (22 < l.information_title?length)>${l.information_title[0..21]}<#else>${l.information_title}</#if>
							</#if>
						</span>
					</a>
				</li>
			</#if>
			<#if ("news" == information.information_type)>
				<#if (5 == l_index)><#break></#if>
			<#else>
				<#if (3 == l_index)><#break></#if>
			</#if>
		</#list>
	</#if>
</ul>