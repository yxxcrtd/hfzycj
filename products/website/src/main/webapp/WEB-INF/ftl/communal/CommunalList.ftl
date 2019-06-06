<#include "MainTop.ftl" />
<div id="search">
    <form>
			<span class="condition">
				分类：
                <select name="s_communal_menuid">
                    <option value="">全部类别</option>
                <#list menuList as menu>
                    <option value="${menu.menu_id}"
                            <#if ((('${s_communal_menuid}')!'') == '${menu.menu_id}')>selected="selected"</#if>>${menu.menu_title}</option>
                </#list>
                </select>

			</span>
        <span class="condition">
				显示状态：
		        <select name="s_status">
		          <option value="">全部状态</option>
		          <option <#if (0 == s_status)>selected="selected"</#if> value="0">显示</option>
		          <option <#if (1 == s_status)>selected="selected"</#if> value="1">隐藏</option>
		        </select>
			</span>
        <span class="condition">
				搜索名称：
				<input type="text" name="s_name" value="${s_name}" class="input160"
                       onMouseOver="this.select();"
                       placeholder="请输入搜索名称"/>
				<input type="submit" value="查询" class="button"/>
			</span>
    </form>
</div>
<table width="100%" border="0" cellspacing="0" cellpadding="0">
    <tr id="thead">
        <td width="5%"><input type="checkbox" class="checkbox" id="checkAll" name="checkAllName" title="全选/全不选"/></td>
        <td width="20%" class="left">名称</td>
        <td width="25%" class="left">地址</td>
        <#--<td width="10%"> 坐标</td>-->
        <td width="10%"> 排序</td>
        <td width="10%">显示状态</td>
        <td width="20%">操作</td>
    </tr>
<#if (list?? && 0 < list?size)>
    <#list list as l>
        <tr onMouseOver="changeBgColor(this, '#ECECEC');" onMouseOut="changeBgColor(this, '#FFFFFF');">
            <td><input type="checkbox" class="checkbox" name="checkName" value="${l.communal_id}" /></td>
            <td class="left">${l.communal_name}</td>
            <td class="left">${l.communal_address}</td>
            <#--<td class="left">${l.communal_coordinate}</td>-->
            <td>${l.communal_orderby}</td>
            <td><a href="javascript:;" id="${l.communal_id}" onClick="updateStatus(${l.communal_id});"><#if (0 == l.communal_status)>显示<#else>隐藏</#if></a></td>
            <td><a href="${request.contextPath}/manage/communal/edit/${l.communal_id}">修改</a>
                <#--<a id="test${l.communal_id}" href="javascript:void(0);" onclick="alert('dddddddddddddd${l.communal_id}')">修改</a>-->
            </td>
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
<input type="hidden" id="sbfWhere" value="${sbfWhere}" />
<#include "MainBottom.ftl" />