<#include "MainTop.ftl" />
	<div id="search">
        <form>
			<span class="condition">
				显示状态：
				<span id="status">
					<select name="s1">
					  <option value="">全部</option>
					  <option <#if (0 == status)>selected="selected"</#if> value="0">显示</option>
					  <option <#if (1 == status)>selected="selected"</#if> value="1">隐藏</option>
					</select>
				</span>
			</span>
			<span class="condition">
				分类名称：
				<input type="text" name="k" value="${k}" class="input160" onMouseOver="this.select();" placeholder="请输入分类名称"/>
				<input type="submit" value="查询" class="button" />
			</span>
        </form>
	</div>
	
	<table width="100%" border="0" cellspacing="0" cellpadding="0">
		<tr id="thead">
			<td width="5%"><input type="checkbox" class="checkbox" id="checkAll" name="checkAllName" title="全选/全不选" /></td>
			<td width="10%" class="left">分类名称</td>
			<td width="10%">显示状态</td>
			<td width="10%">操作</td>
		</tr>
		<#if (list?? && 0 < list?size)>
			<#list list as l>
				<tr onMouseOver="changeBgColor(this, '#ECECEC');" onMouseOut="changeBgColor(this, '#FFFFFF');">
					<td><input type="checkbox" class="checkbox" name="checkName" value="${l.category_id}" /></td>
					<td class="left">${l.category_name}</td>
					<td><a href="javascript:;" id="${l.category_id}" onClick="updateStatus(${l.category_id});"><#if (0 == l.category_status)>显示<#else>隐藏</#if></a></td>
					<td><a href="${request.contextPath}/manage/category/edit/${l.category_id}">修改</a></td>
				</tr>
			</#list>
		<#else>
			<tr>
				<td colspan="4">没有数据！</td>
			</tr>
		</#if>
	</table>
	<div id="operation">
		<input type="checkbox" class="checkbox" id="unCheckAll" name="unCheckName" />反选&nbsp;&nbsp;
		<input type="button" id="hideAll" value="设为隐藏" class="button" />&nbsp;&nbsp;
		<input type="button" id="showAll" value="设为显示" class="button" />
		<div id="pageNav"><span class="condition">共${count}条数据</span><#include "Pager.ftl" /></div>
	</div>
<#include "MainBottom.ftl" />