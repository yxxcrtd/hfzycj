<#include "MainTop.ftl" />
<div id="search">
    <form>
			<span class="condition">
				菜单分类：
		        <select name="s_menu_category">
		          <option value="">全部类别</option>
                <#list listDictionary as dictionary>
                    <option <#if ('${dictionary.dictionary_value}' == s_menu_category)>selected="selected"</#if>
                            value="${dictionary.dictionary_value}">${dictionary.dictionary_describe}</option>
                </#list>
		        </select>
			</span>
        <span class="condition">
				显示状态：
		        <select name="s_menu_status">
		          <option value="">全部状态</option>
		          <option <#if (0 == s_menu_status)>selected="selected"</#if> value="0">显示</option>
		          <option <#if (1 == s_menu_status)>selected="selected"</#if> value="1">隐藏</option>
		        </select>
			</span>
        <span class="condition">
				菜单导航名称：
				<input type="text" name="s_menu_title" value="${s_menu_title}" class="input160"
                       onMouseOver="this.select();"
                       placeholder="请输入菜单导航名称"/>
				<input type="submit" value="查询" class="button"/>
			</span>
    </form>
</div>
<table width="100%" border="0" cellspacing="0" cellpadding="0">
    <tr id="thead">
        <td width="5%"><input type="checkbox" class="checkbox" id="checkAll" name="checkAllName" title="全选/全不选"/></td>
        <td width="20%" class="left">菜单名称</td>
        <td width="25%" class="left">菜单地址</td>
    <#--<td width="10%" class="left">菜单打开方式</td>-->
        <td width="10%"> 菜单分类</td>
        <td width="10%"> 菜单排序</td>
        <td width="10%">显示状态</td>
        <td width="20%">操作</td>
    </tr>

    </br>
<#if (listMap?? && 0 < listMap?size)>
    <#list listMap as data >
        <tr onMouseOver="changeBgColor(this, '#ECECEC');" onMouseOut="changeBgColor(this, '#FFFFFF');">
            <td><input type="checkbox" class="checkbox" name="checkName" value="${data["menu_id"]}"/></td>
            <td class="left">${data["menu_title"]}</td>
            <td class="left"><a
                    href="<#if (!'${data["menu_url"]}'?starts_with('http'))>http://</#if>${data["menu_url"]}"
                    target="_blank">${data["menu_url"]}</a></td>
        <#--<td class="left">${data["menu_target"]}</td>-->
            <td>${data["menu_parentidtitle"]}</td>
            <td>${data["menu_orderby"]}</td>
            <td><a href="javascript:;" id="${data["menu_id"]}"
                   onClick="updateStatus(${data["menu_id"]});"><#if ('0' == '${data["menu_status"]}')>显示<#else>
                隐藏</#if></a></td>
            <td><a href="${request.contextPath}/manage/menu/edit/${data["menu_id"]}">修改</a></td>
        </tr>
    </#list>
<#else>
    <tr>
        <td colspan="7">没有数据！</td>
    </tr>
</#if>
</table>
<div id="operation">
    <input type="checkbox" class="checkbox" id="unCheckAll" name="unCheckName"/>反选&nbsp;&nbsp;
    <input type="button" id="hideAll" value="设为隐藏" class="button"/>&nbsp;&nbsp;
    <input type="button" id="showAll" value="设为显示" class="button"/>
    <div id="pageNav"><span class="condition">共${count}条数据</span><#include "Pager.ftl" /></div>
</div>
<#include "MainBottom.ftl" />