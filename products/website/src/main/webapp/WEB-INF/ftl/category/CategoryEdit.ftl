<#include "MainTop.ftl" />
<form action="${request.contextPath}/manage/category/save" method="post" enctype="multipart/form-data">

    <div class="label">分类名称：<span class="star">*</span></div>
    <div>
	<@s.formInput "category.category_name" "class='input500'" /><@s.showErrors classOrStyle="red" />
    </div>

    <div id="operation">
        <input type="submit" value="<#if (0 == category.category_id)>保 存<#else>修 改</#if>" class="button icon_save" />&nbsp;&nbsp;
        <input type="button" value="返 回" class="button icon_return" onClick="javascript:history.back();" />
    </div>
<@s.formHiddenInput "category.category_id" />
<@s.formHiddenInput "category.category_status" />
</form>
<#include "MainBottom.ftl" />
