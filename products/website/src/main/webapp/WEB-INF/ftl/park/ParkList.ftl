<#include "MainTop.ftl" />
<div id="search">
    <form>
			<span class="condition">
				显示状态：
				<select name="s1">
					<option value="">全部</option>
					<option <#if (0 == status)>selected="selected"</#if> value="0">显示</option>
					<option <#if (1 == status)>selected="selected"</#if> value="1">隐藏</option>
				</select>&nbsp;&nbsp;&nbsp;

				停车场类型：
				<select name="s2">
					<option value="">全部</option>
					<option <#if (0 == type)>selected="selected"</#if> value="0">地下停车场</option>
					<option <#if (1 == type)>selected="selected"</#if> value="1">地面停车场</option>
				</select>
			</span>
            <span class="condition">
				停车场名称：
				<input type="text" name="k" value="${k}" class="input160" onMouseOver="this.select();"
                       placeholder="请输入名称"/>&nbsp;&nbsp;&nbsp;
				停车场编码：
				<input type="text" name="k2" value="${k2}" class="input160" onMouseOver="this.select();"
                       placeholder="请输入编码"/>
				<input type="submit" value="查询" class="button"/>
			</span>
    </form>
</div>

<table width="100%" border="0" cellspacing="0" cellpadding="0">
    <tr id="thead">
        <td width="5%"><input type="checkbox" style="margin-top: 5px;"/></td>
        <td width="20%" class="left">名称</td>
        <td width="10%">编码</td>
        <td width="10%">总车位数</td>
        <td width="10%">停车场类型</td>
        <td width="10%" class="left">联系人</td>
        <td width="10%" class="left">联系方式</td>
        <td width="10%">更新时间</td>
        <td width="5%">显示状态</td>
        <td width="5%">操作</td>
    </tr>
<#if (list?? && 0 < list?size)>
    <#list list as l>
        <tr onMouseOver="changeBgColor(this, '#ECECEC');" onMouseOut="changeBgColor(this, '#FFFFFF');">
            <td><input type="checkbox" class="checkbox" name="checkName" value="${l.park_id}"/></td>
            <td class="left">${l.park_name}</td>
            <td>${l.park_code}</td>
            <td>${l.park_total}</td>
            <td><@compress single_line=true><@getParkType l.park_type /></@compress></td>
            <td class="left">${l.park_contact_name}</td>
            <td class="left">${l.park_tel}</td>
            <td>${l.park_update_time}</td>
            <td><a href="javascript:;" id="${l.park_id}" onClick="updateStatus(${l.park_id});"><#if (0 == l.park_status)>显示<#else>隐藏</#if></a></td>
            <td><a href="${request.contextPath}/manage/park/edit/${l.park_id}">修改</a></td>
        </tr>
    </#list>
<#else>
    <tr>
        <td colspan="11">没有数据！</td>
    </tr>
</#if>
</table>

<div id="operation">
    <input type="checkbox" class="checkbox" id="unCheckAll" name="unCheckName"/>全选/反选&nbsp;&nbsp;
    <input type="button" id="hideAll" value="设为隐藏" class="button"/>&nbsp;&nbsp;
    <input type="button" id="showAll" value="设为显示" class="button"/>
    <div id="pageNav"><span class="condition">共${count}条数据</span><#include "Pager.ftl" /></div>
</div>
<#include "MainBottom.ftl" />