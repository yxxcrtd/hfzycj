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
				显示审核状态：
				    <select name="s2">
					  <option value="">全部</option>
					  <option <#if (0 == approveStatus)>selected="selected"</#if> value="0">待审核</option>
					  <option <#if (1 == approveStatus)>selected="selected"</#if> value="1">审核已通过</option>
					</select>
			</span>
            <span class="condition">
				反馈的内容：
				<input type="text" name="k" value="${k}" class="input160" onMouseOver="this.select();" placeholder="请输入内容" />
				<input type="submit" value="查询" class="button" />
			</span>
		</form>
	</div>

	<table width="100%" border="0" cellspacing="0" cellpadding="0">
		<tr id="thead">
			<td width="5%"><input type="checkbox" style="margin-top: 5px;" /></td>
            <td width="10%" class="center">反馈者称呼</td>
			<td width="30%" class="center">反馈内容</td>
			<td width="10%" class="center">反馈者联系方式</td>
			<td width="10%" class="center">反馈时间</td>
			<td width="10%" class="center">显示状态</td>
            <td width="10%" class="center">审核状态</td>
		</tr>
		<#if (list?? && 0 < list?size)>
			<#list list as l>
				<tr onMouseOver="changeBgColor(this, '#ECECEC');" onMouseOut="changeBgColor(this, '#FFFFFF');">
					<td><input type="checkbox" class="checkbox" name="checkName" value="${l.feedback_id}" /></td>
                    <td class="center">${l.feedback_name}</td>
					<td id="${l.feedback_id}content" class="left"><pre>${l.feedback_content}</pre>
						<#if (1 == l.feedback_approve_status)>
                            <span id="${l.feedback_id}replySpan">
								<a id="${l.feedback_id}reply" href="javascript:void(0);" onclick="openReply(${l.feedback_id},'${l.feedback_reply_content}');">
									<img src="../images/reply.png" border="0" <#if ("" !=l.feedback_reply_content)>title="修改回复信息"<#else>title="回复信息"</#if> />
								</a>
							</span>
						    <#if ("" !=l.feedback_reply_content)>
								<br/>
								回复：<pre>${l.feedback_reply_content}</pre>
							</#if>
						</#if>
					</td>
                    <td class="center">${l.feedback_tel}</td>
					<td class="center">${l.feedback_create_time}</td>
					<#--<td id="${l.feedback_id}approveStatus" class="left"><@compress single_line=true><@getFeedbackStatus l.feedback_approve_status /></@compress></td>-->
					<td><a href="javascript:;" id="${l.feedback_id}" onClick="updateStatus(${l.feedback_id});"><#if (0 == l.feedback_status)>显示<#else>隐藏</#if></a></td>
					<#--<td><#if (0 == l.feedback_approve_status && 0 == l.feedback_status)><a href="javascript:;" id="${l.feedback_id}approve"  onClick="updateApproveStatus(${l.feedback_id});">审核</a><#else><text style="color: #000;">已审核<text></#if></td>-->
                    <td id="${l.feedback_id}approveStatus" class="center">
						<#if (0 == l.feedback_approve_status)>
                            <a href="javascript:;" id="${l.feedback_id}approve"  onClick="updateApproveStatus(${l.feedback_id});">待审核</a>
						<#else><text style="color: #16b553;">审核通过<text>
						</#if>
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
		<input type="checkbox" class="checkbox" id="unCheckAll" name="unCheckName"/>全选/反选&nbsp;&nbsp;
		<input type="button" id="hideAll" value="设为隐藏" class="button" />&nbsp;&nbsp;
		<input type="button" id="showAll" value="设为显示" class="button" />
		<div id="pageNav"><span class="condition">共${count}条数据</span><#include "Pager.ftl" /></div>
	</div>
<#include "MainBottom.ftl" />