<#include "MainTop.ftl" />
	<div id="search">
        <form>
			<span class="condition">
				资讯类型：
                <select name="s1">
					<option value="">全部</option>
					<#list informationTypeMap?keys as k>
						<option <#if (k == type)>selected="selected"</#if> value="${k}">${informationTypeMap[k]}</option>
					</#list>
				</select>&nbsp;&nbsp;&nbsp;&nbsp;
				显示状态：
				<select name="s2">
					<option value="">全部</option>
                    <option <#if ("0" == status)>selected="selected"</#if> value="0">上线</option>
					<option <#if ("1" == status)>selected="selected"</#if> value="1">下线</option>
				</select>
			</span>
            <span class="condition">
				标题：
				<input type="text" name="k" value="${k}" class="input160" onMouseOver="this.select();" placeholder="请输入标题名称"/>
				<input type="submit" value="查询" class="button" />
			</span>
		</form>
	</div>

	<table width="100%" border="0" cellspacing="0" cellpadding="0">
		<tr id="thead">
			<td width="5%"><input type="checkbox" class="checkbox" id="checkAll" name="checkAllName" title="全选/全不选" /></td>
			<td width="10%">资讯类型</td>
			<td width="40%" class="left">标题</td>
			<td width="5%">点击量</td>
			<td width="10%">发布时间</td>
			<td width="5%">排序</td>
			<td width="5%">状态</td>
			<td width="5%">操作</td>
		</tr>
		<#if (list?? && 0 < list?size)>
			<#list list as l>
				<tr onMouseOver="changeBgColor(this, '#ECECEC');" onMouseOut="changeBgColor(this, '#FFFFFF');">
					<td><input type="checkbox" class="checkbox" name="checkName" value="${l.information_id}" /></td>
					<td><#list informationTypeMap?keys as k><#if (l.information_type == k)>${informationTypeMap[k]}</#if></#list></td>
					<td class="left">${filterHTML(l.information_title)}</td>
					<td>${l.information_hit}</td>
					<td>${l.information_create_time}</td>
					<td>${l.information_order_by}</td>
					<td><a href="javascript:;" id="${l.information_id}" onClick="updateStatus(${l.information_id});"><#if (0 == l.information_status)><span class="green">上线</span><#else><span class="red">下线</span></#if></a></td>
					<td><a href="${request.contextPath}/manage/information/edit/${l.information_id}">修改</a></td>
				</tr>
			</#list>
		<#else>
			<tr>
				<td colspan="8">没有数据！</td>
			</tr>
		</#if>
	</table>

	<div id="operation">
		<input type="checkbox" class="checkbox" id="unCheckAll" name="unCheckName" />反选&nbsp;&nbsp;
        <input type="button" id="showAll" value="上线" class="button" />&nbsp;&nbsp;
        <input type="button" id="hideAll" value="下线" class="button" />
		<div id="pageNav"><span class="condition">共${count}条数据</span><#include "Pager.ftl" /></div>
	</div>
<#include "MainBottom.ftl" />