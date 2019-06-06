<div class="title"><a href="/site/parksearch">停车场备案公示</a></div>
<ul>
    <#if (list?? && 0 < list?size)>
        <#list list as l>
            <a href="/upload/park_${l.park_id}.html" target="_blank" title="${l.park_name}">
                <span class="point">&#183;</span>
                <li>
                    <div id="park_li_left">
                        <div id="park_title"><#if (20 < l.park_name?length)>${l.park_name[0..19]}<#else>${l.park_name}</#if></div>
                        <div id="park_address">${l.park_address}</div>
                        <div id="park_type">收费类型：<#if (0 == l.park_type)>地下停车场<#else>地面停车场</#if></div>
                    </div>
                    <div id="park_li_right">
                        <div id="park_li_right_number">
                            ${l.park_total}
                            <div id="park_li_right_name">停车位</div>
                        </div>
                    </div>
                </li>
            </a>
            <#if (2 == l_index)><#break></#if>
        </#list>
    </#if>
</ul>