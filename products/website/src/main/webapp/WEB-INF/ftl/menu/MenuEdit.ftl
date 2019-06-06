<#include "MainTop.ftl" />
<form action="${request.contextPath}/manage/menu/save" method="post">
    <div class="label">菜单类别：<span class="star">*</span></div>
    <div>
        <select name="menu_parentid" id="menu_parentid" class='input500'>
        <#--<option value="">--请选择--</option>-->
        <#list listDictionary2 as dictionary>
            <option value="${dictionary.dictionary_value}"
                    <#if ((('${menu.menu_parentid}')!'') == '${dictionary.dictionary_value}')>selected="selected"</#if>>${dictionary.dictionary_describe}</option>
        </#list>
        </select><@s.showErrors classOrStyle="red" />
    </div>
    <div class="label">菜单名称：<span class="star">*</span></div>
    <div><@s.formInput "menu.menu_title" "class='input500'" /><@s.showErrors classOrStyle="red" /></div>

    <div class="label">菜单地址：<span class="star">*</span></div>
    <div><@s.formInput "menu.menu_url" "class='input500'" /><@s.showErrors classOrStyle="red" /></div>

    <div class="label">菜单打开方式：<span class="star">*</span></div>
    <div>
        <select name="menu_target" id="menu_target" class='input500'>
        <#--<option value="">请选择打开方式</option>-->
        <#list listDictionary as dictionary>
            <option value="${dictionary.dictionary_value}"
                    <#if ((('${menu.menu_target}')!'') == '${dictionary.dictionary_value}')>selected="selected"</#if>>${dictionary.dictionary_value}</option>
        </#list>
        </select>
    <#--<@s.showErrors classOrStyle="red" />-->
    </div>
    <div class="label">菜单描述：<span class="star">*</span></div>
    <div><@s.formInput "menu.menu_describe" "class='input500'" /></div>
    <div class="label">菜单状态：<span class="star">*</span></div>
    <div>
    <@s.formRadioButtons "menu.menu_status" statusMap  />
    </div>
    <div class="label">菜单排序：<span class="star">*</span></div>
    <div><@s.formInput "menu.menu_orderby" "class='input500'" /><@s.showErrors classOrStyle="red" /></div>
    <div id="operation">
    <@s.formHiddenInput "menu.menu_id" />
    <@s.formHiddenInput "menu.menu_createtime" />
        <input type="submit" value="保 存" class="button icon_save"/>&nbsp;&nbsp;
        <input type="button" value="返 回" class="button icon_return" onClick="javascript:history.back();"/>
    </div>

</form>
<#include "MainBottom.ftl" />