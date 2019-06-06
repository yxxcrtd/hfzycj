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
				投票模式：
					<select name="s2">
					  <option value="">全部</option>
					  <option <#if (0 == type)>selected="selected"</#if> value="0">单选</option>
					  <option <#if (1 == type)>selected="selected"</#if> value="1">多选</option>
					</select>&nbsp;&nbsp;&nbsp;
			</span>

            <span class="condition">
				投票的主题：
				<input type="text" name="k" value="${k}" class="input160" onMouseOver="this.select();" placeholder="请输入主题内容" />
				<input type="submit" value="查询" class="button" />
			</span>
		</form>
	</div>

	<table width="100%" border="0" cellspacing="0" cellpadding="0">
		<tr id="thead">
			<td width="5%"><input type="checkbox" style="margin-top: 5px;" /></td>
            <td width="15%" class="center">投票主题</td>
			<td width="25%" class="center">投票说明</td>
			<td width="10%" class="center">投票模式</td>
			<td width="10%" class="center">显示状态</td>
			<td width="10%" class="center">投票人数</td>
			<td width="10%" class="center">发起投票时间</td>
			<td width="10%" class="center">投票截止时间</td>
            <td width="10%" class="center">操作</td>
		</tr>
		<#if (list?? && 0 < list?size)>
			<#list list as l>
				<tr onMouseOver="changeBgColor(this, '#ECECEC');" onMouseOut="changeBgColor(this, '#FFFFFF');">
                    <td><input type="checkbox" class="checkbox" name="checkName" value="${l.vote_id}" /></td>
					<td class="left">${l.vote_title}</td>
					<td class="left">${l.vote_description}</td>
					<td class="center"><#if (0 == l.vote_type)>单选模式<#else>多选模式</#if></td>
                    <td class="center"><a href="javascript:;" id="${l.vote_id}" onClick="updateStatus(${l.vote_id});"><#if (0 == l.vote_status)>显示<#else>隐藏</#if></a></td>
                    <td class="center">${l.vote_user_counts}</td>
					<td class="center">${l.vote_create_time}</td>
					<td class="center">${l.vote_end_time}</td>
                    <td><a href="${request.contextPath}/manage/vote/edit/${l.vote_id}">修改</a></td>
				</tr>
			</#list>
		<#else>
			<tr>
				<td colspan="9">没有数据！</td>
			</tr>
		</#if>
	</table>

	<div id="operation">
		<input type="checkbox" class="checkbox" id="unCheckAll" name="unCheckName"/>全选/反选&nbsp;&nbsp;
		<input type="button" id="hideAll" value="设为隐藏" class="button" />&nbsp;&nbsp;
		<input type="button" id="showAll" value="设为显示" class="button" />
		<div id="pageNav"><span class="condition">共${count}条数据</span><#include "Pager.ftl" /></div>
	</div>
<#include "MainBottom.ftl" />