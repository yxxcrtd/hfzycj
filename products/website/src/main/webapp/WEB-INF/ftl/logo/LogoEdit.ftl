<#include "MainTop.ftl" />
<form action="${request.contextPath}/manage/logo/save" method="post" enctype="multipart/form-data">
    <div class="label">Logo 地址：（本站默认为：／）<span class="star">*</span></div>
    <div><@s.formInput "logo.logo_url" "class='input500'" /><@s.showErrors classOrStyle="red" /></div>

    <div class="label">Logo 标题：<span class="star">*</span></div>
    <div><@s.formInput "logo.logo_title" "class='input500'" /><@s.showErrors classOrStyle="red" /></div>

    <div id="objPicture">
        <div class="label">Logo 图片：<span class="star">*</span></div>
        <div>
            <input type="file" id="img" onchange="document.getElementById('img').src=this.value;" class="input" style="width: 505px; padding: 3px!important;" name="file" />
            <span class="star"><#if (logo.logo_logo??)><img src="/upload/${logo.logo_logo}" /></#if></span>
		    <@s.formHiddenInput "logo.logo_logo" /><@s.showErrors classOrStyle="red" />
        </div>
    </div>

    <div id="operation">
	    <@s.formHiddenInput "logo.logo_id" />
        <input type="submit" onclick="return aa(${maxSize}, ${maxWidth}, ${maxHeight});" value="保 存" class="button icon_save" />&nbsp;&nbsp;
        <input type="button" value="返 回" class="button icon_return" onClick="javascript:history.back();" />
    </div>
</form>
<#include "MainBottom.ftl" />
