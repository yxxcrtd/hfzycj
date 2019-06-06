<#include "MainTop.ftl" />
    <form action="${request.contextPath}/manage/user/save" method="post" enctype="multipart/form-data">
        <div class="label">用户名：<span class="star">*</span></div>
        <div>
        <@s.formInput "user.user_name" "class='input500'" /><@s.showErrors classOrStyle="red" />
        </div>

        <div class="label">登录密码：<span class="star">*</span></div>
        <div>
        <@s.formPasswordInput "user.user_password" "class='input500'" /><@s.showErrors classOrStyle="red" />
        </div>

        <div class="label">用户角色：<span class="star">*</span></div>
        <div>
        <@s.formInput "user.user_role" "class='input'" /><@s.showErrors classOrStyle="red" />
        </div>

        <@s.formHiddenInput "user.user_id" />
        <@s.formhiddenInput "user.user_status" />

        <div id="operation">
            <input type="submit" value="<#if (0 == user.user_id)>保 存<#else>修 改</#if>" class="button icon_save" />&nbsp;&nbsp;
            <input type="button" value="返 回" class="button icon_return" onClick="javascript:history.back();" />
        </div>
    </form>
<#include "MainBottom.ftl" />
