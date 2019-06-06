<#include "MainTop.ftl" />
<form action="${request.contextPath}/manage/feedback/save" method="post" enctype="multipart/form-data">

    <div class="label">反馈的内容：<span class="star">*</span></div>
	<#--<@s.formInput "feedback.feedback_content" "class='input300'" "style='margin-left: auto'"/><@s.showErrors classOrStyle="red" />-->
    <textarea class="input300" name="feedback_content" id="feedback_content" value="${feedback.feedback_content}"></textarea>
    </div>

    <div class="label">反馈人的称呼：<span class="star">*</span></div>
    <div>
    <@s.formInput "feedback.feedback_name" "class='input200'" /><@s.showErrors classOrStyle="red" />
    </div>

    <div class="label">反馈人的联系方式：<span class="star">*</span></div>
    <div>
    <@s.formInput "feedback.feedback_tel" "class='input200'" /><@s.showErrors classOrStyle="red" />
    </div>

    <div id="operation">
        <input type="submit" value="<#if (0 == feedback.feedback_id)>保 存<#else>修 改</#if>" class="button icon_save" />&nbsp;&nbsp;
        <input type="button" value="返 回" class="button icon_return" onClick="javascript:history.back();" />
    </div>
<@s.formHiddenInput "feedback.feedback_id" />
<@s.formHiddenInput "feedback.feedback_status" />
<@s.formHiddenInput "feedback.feedback_approvestatus" />
</form>
<#include "MainBottom.ftl" />
