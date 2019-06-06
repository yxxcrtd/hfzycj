<p class="box-link pa">
    <em class="sp">用户信息</em>
    <table width="100%" border="0" cellspacing="0" cellpadding="0">
        <tr id="thead">
            <td width="5%"><input type="checkbox" style="margin-top: 5px;"/></td>
            <td width="20%" class="left">用户名称</td>
            <td width="25%" class="left">用户角色</td>
            <td width="20%" class="left">用户状态</td>
            <td width="10%">操作</td>
        </tr>
    <#if (list?? && 0 < list?size)>
        <#list list as l>
            <tr onMouseOver="changeBgColor(this, '#ECECEC');" onMouseOut="changeBgColor(this, '#FFFFFF');">
                <td><input type="checkbox" class="checkbox" name="checkName" value="${l.user_id}"/></td>
                <td class="left">${l.user_name}</td>
                <td class="left"><#if (1 == l.user_role)>超级管理员<#else>普通管理员</#if></td>
                <td class="left"><#if (0 == l.user_status)>关闭<#else>正常</#if></td>
                <td>待定</td>
            </tr>
        </#list>
    <#else>
        <tr bgColor="F9F9F9">
            <td colspan="7">没有数据！</td>
        </tr>
    </#if>
    </table>
</p>