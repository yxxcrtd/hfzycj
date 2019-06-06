<#include "MainTop.ftl">
	<form action="${request.contextPath}/manage/copyright/save" method="post">

        <div class="label">版权信息：<span class="star">*</span></div>
        <div>
            <@s.formTextarea "copyright.copyright_content" "class='input' style='width: 800px; height: 250px;'" /><@s.showErrors classOrStyle="red" />
        </div>

        <div id="operation">
		<@s.formHiddenInput "copyright.copyright_id" />
            <input type="submit" value="保 存" class="button icon_save" />&nbsp;&nbsp;
            <input type="button" value="返 回" class="button icon_return" onClick="javascript:history.back();" />
        </div>
	</form>

    <script language="javascript" src="${request.contextPath}/js/kindeditor.min.js"></script>
    <script>
        var editor;
        KindEditor.ready(function(K) {
            editor = K.create("#copyright_content", {
                newlineTag : "br"
            });
        });
    </script>
<#include "MainBottom.ftl">