<#include "MainTop.ftl" />
    <form action="${request.contextPath}/manage/user/changePassword" method="post" enctype="multipart/form-data">
        <div class="label">当前登录账户：${user.user_name}</div><br />

        <div class="label">原密码：<span class="star">*</span></div>
        <input type="password" name="oldp" class="input160" onMouseOver="this.select();" placeholder="请输入原密码">

        <div class="label">新密码：<span class="star">*</span></div>
        <input type="password" name="newp" class="input160" onMouseOver="this.select();" placeholder="请输入新密码">

        <div class="label">确认新密码：<span class="star">*</span></div>
        <input type="password" name="confp" class="input160" onMouseOver="this.select();" placeholder="请再次输入新密码">

        <input type="hidden" name="userId" value="${user.user_id}">

        <div id="operation">
            <input type="submit" value="保 存" class="button icon_save" />&nbsp;&nbsp;
            <input type="button" value="返 回" class="button icon_return" onClick="javascript:history.back();" />
        </div>
    </form>
<#include "MainBottom.ftl" />