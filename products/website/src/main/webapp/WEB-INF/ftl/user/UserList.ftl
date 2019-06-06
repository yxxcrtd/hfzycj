<#include "MainTop.ftl" />
	<div id="search">
		<form>
			<span class="condition">
				用户状态：
		        <select name="s1">
		          <option value="">全部</option>
		          <option <#if (0 == status)>selected="selected"</#if> value="0">正常</option>
		          <option <#if (1 == status)>selected="selected"</#if> value="1">关闭</option>
		        </select>
			</span>
			<span class="condition">
				用户名：
				<input type="text" name="k" value="${k}" class="input160" onMouseOver="this.select();" placeholder="请输入用户名">
				<input type="submit" value="查询" class="button">
			</span>
		</form>
	</div>
	
	<table width="100%" border="0" cellspacing="0" cellpadding="0">
		<tr id="thead">
			<td width="5%"><input type="checkbox" class="checkbox" id="checkAll" name="checkAllName" title="全选/全不选" /></td>
			<td width="20%" class="left">用户名称</td>
			<td width="25%" class="left">用户角色</td>
			<td width="20%" class="left">用户状态</td>
			<td width="10%">操作</td>
		</tr>
		<#if (list?? && 0 < list?size)>
			<#list list as l>
			    <tr onMouseOver="changeBgColor(this, '#ECECEC');" onMouseOut="changeBgColor(this, '#FFFFFF');">
					<td><input type="checkbox" class="checkbox" name="checkName" value="${l.user_id}" /></td>
					<td class="left">${l.user_name}</td>
					<td class="left"><#if (1 == l.user_role)>超级管理员<#else>普通管理员</#if></td>
					<td class="left"><#if (0 == l.user_status)>正常<#else>关闭</#if></td>
                    <td>
						<a href="javascript:;" id="${l.user_id}" onClick="updateStatus(${l.user_id});"><#if (0 == l.user_status)>关闭<#else>激活</#if></a>
                        <a href="${request.contextPath}/manage/user/edit/${l.user_id}">修改</a>
					</td>
				</tr>
			</#list>
		<#else>
            <tr bgColor="F9F9F9">
                <td colspan="7">没有数据！</td>
            </tr>
		</#if>
	</table>

	<div id="operation">
        <input type="checkbox" class="checkbox" id="unCheckAll" name="unCheckName" />全选/反选&nbsp;&nbsp;
		<input type="button" id="showAll" value="激活" class="button" />&nbsp;&nbsp;
		<input type="button" id="hideAll" value="关闭" class="button" />
		<div id="pageNav"><span class="condition">共${count}条数据</span><#include "Pager.ftl" /></div>
	</div>
<#include "MainBottom.ftl" />