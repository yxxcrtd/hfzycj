<#include "MainTop.ftl" />
	<form action="${request.contextPath}/manage/link/save" method="post" enctype="multipart/form-data">

		<div class="label">友情链接名称：<span class="star">*</span></div>
		<div>
			<@s.formInput "link.link_name" "class='input500'" /><@s.showErrors classOrStyle="red" />
		</div>
				
		<div class="label">友情链接地址：<span class="star">*</span></div>
		<div>
			<@s.formInput "link.link_url" "class='input500'" /><@s.showErrors classOrStyle="red" />
		</div>
				
		<div id="objPicture">
			<div class="label">友情链接LOGO：</div>
			<div>
				<input type="file" name="file" id="file" class="input" style="width: 510px; padding: 1px;" />
				<@s.formHiddenInput "link.link_logo" /><@s.showErrors classOrStyle="red" />
				<#if (0 lt link.link_logo && link.link_logo??)>
					<br />
					<img src="/upload/${link.link_logo}" />
				</#if>
			</div>
		</div>

        <div class="label">友情链接排序：<span class="star">*</span></div>
        <div>
			<@s.formInput "link.link_order_by" "class='input'" /><@s.showErrors classOrStyle="red" />
        </div>

        <div id="operation">
			<input type="submit" value="<#if (0 == link.link_id)>保 存<#else>修 改</#if>" class="button icon_save" />&nbsp;&nbsp;
			<input type="button" value="返 回" class="button icon_return" onClick="javascript:history.back();" />
		</div>
		<@s.formHiddenInput "link.link_id" />
		<@s.formHiddenInput "link.link_status" />
	</form>
<#include "MainBottom.ftl" />
