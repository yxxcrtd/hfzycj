<ul class="itemContainer">
    <#if (list?? && 0 < list?size)>
        <#list list as l>
            <li>
                <div class="feedback_list_top">
                    <div class="feedback_list_top_left">用户${l.feedback_name}：</div>
                    <div class="feedback_list_top_right" id="prettyTime${l.feedback_id}" title="${l.feedback_create_time?string("yyyy-MM-dd HH:mm:ss")}">${l.feedback_create_time}</div>
                </div>
                <div class="feedback_list_content" title="${l.feedback_content}">
                    <pre>${l.feedback_content}</pre>
                </div>
                <#if l.feedback_reply_content?? && 0 < l.feedback_reply_content?length>
                    <div class="feedback_list_reply_title">管理人员回复：</div>
                    <div class="feedback_list_reply_content"><pre>${l.feedback_reply_content}</pre></div>
                </#if>
                <hr>
            </li>
        </#list>
    </#if>
</ul>
<div class="holder"></div>