<ul class="itemContainer">
    <#if (list?? && 0 < list?size)>
        <#list list as l>
            <li>
                <div class="title">
                    <a href="/upload/information_${l.information_id}.html" target="_blank" title="${l.information_title}">
                        <#if (30 < l.information_title?length)>${l.information_title[0..29]}<#else>${l.information_title}</#if>
                    </a>
                </div>
                <div class="detail">
                    <#if (l.information_attachment_url?? && 0 < l.information_attachment_url?length)>
                        <div class="detail_img">
                            <img src="/upload/${l.information_attachment_url}" width="192" height="120" />
                        </div>
                    </#if>
                    <div class="<#if (l.information_attachment_url?? && 0 < l.information_attachment_url?length)>detail_content<#else>detail_content1</#if>">
                        <div class="time">${l.information_create_time?string("yyyy-MM-dd")}</div>
                        <div class="summary">
                            <#if (l.information_attachment_url?? && 0 < l.information_attachment_url?length)>
                                <#if (102 < l.information_summary?length)>${l.information_summary[0..101]}<#else>${l.information_summary}</#if>
                            <#else>
                                <#if (147 < l.information_summary?length)>${l.information_summary[0..146]}<#else>${l.information_summary}</#if>
                            </#if>
                        </div>
                        <div class="hit"><img src="../images/hit.png"><span id="hit${l.information_id}">${l.information_hit}</span></div>
                    </div>
                </div>
            </li>
        </#list>
    </#if>
</ul>
<div class="holder"></div>