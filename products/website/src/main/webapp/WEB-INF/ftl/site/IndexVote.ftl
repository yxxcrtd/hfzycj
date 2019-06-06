<div class="title"><a href="/site/parksearch">停车场备案公示</a></div>
<ul>
    <#if (parkList?? && 0 < parkList?size)>
        <#list parkList as l>
            <a href="/upload/park_${l.park_id}.html" target="_blank" title="${l.park_name}">
                <span class="point">&#183;</span>
                <li>
                    <div id="park_li_left">
                        <div id="park_title"><#if (20 < l.park_name?length)>${l.park_name[0..19]}<#else>${l.park_name}</#if></div>
                        <div id="park_address">${l.park_address}</div>
                        <div id="park_type">收费类型：<#if (0 == l.park_type)>地下停车场<#else>地面停车场</#if></div>
                    </div>
                    <div id="park_li_right">${l.park_total}<br>停车位</div>
                </li>
            </a>
            <#if (4 == l_index)><#break></#if>
        </#list>
    </#if>
</ul>





<div class="title"><a href="/upload/vote.html">投票调查</a></div>
<ul>
    <span class="point">&#183;</span>
    <li title="通知公告七通知公告七通知公告七通知公告七通知公告七">
        <a href="/upload/information_36.html" target="_blank">
						<span>
								通知公告七通知公告七通知公告七通知公告七通知
						</span>
        </a>
    </li>
    <span class="point">&#183;</span>
    <li title="通知公告六通知公告六通知公告六通知公告六通知公告六通知公告六通知公告六">
        <a href="/upload/information_35.html" target="_blank">
						<span>
								通知公告六通知公告六通知公告六通知公告六通知
						</span>
        </a>
    </li>
    <span class="point">&#183;</span>
    <li title="通知公告五通知公告五通知公告五通知公告五通知公告五">
        <a href="/upload/information_34.html" target="_blank">
						<span>
								通知公告五通知公告五通知公告五通知公告五通知
						</span>
        </a>
    </li>
    <span class="point">&#183;</span>
    <li title="通知公告四通知公告四通知公告四通知公告四通知公告四通知公告四通知公告四">
        <a href="/upload/information_32.html" target="_blank">
						<span>
								通知公告四通知公告四通知公告四通知公告四通知
						</span>
        </a>
    </li>
</ul>