<#include "MainTop.ftl" />
<form id="voteForm">
    <div class="label">投票主题：<span class="star">*</span></div>
    <div><@s.formInput "vote.vote_title" "class='input500'" /><@s.showErrors classOrStyle="red" /></div>

    <div class="label">投票说明：<span class="star">*</span></div>
    <div><@s.formInput "vote.vote_description" "class='input500'" /><@s.showErrors classOrStyle="red" /></div>

    <div class="label">截止时间：<span class="star">*</span></div>
    <div><@s.formInput "vote.vote_end_time" "class='input200'" /><@s.showErrors classOrStyle="red" /></div>

    <div class="label2">选择模式：</div>
    <div>
        <input name="vote_type" type="radio" value="0" checked="checked" /> 单选 &nbsp;&nbsp;&nbsp;&nbsp;
        <input name="vote_type" type="radio" value="1" <#if (vote.vote_type == 1)>checked="checked"</#if> /> 多选
    </div>

    <div id="item" class="label">投票选项：
        <#if (itemList?? && 0 < itemList?size)>
            <#list itemList as item>
                <p class="clr"><span class="fl lNum">${item.item_index + 1}</span>
                    <input type="text" id="tp_${item.item_id}" class="input250" value="${item.item_name}" onkeyup="changerHiddenValue(${item.item_id});" />
                    <input id="hidden_${item.item_id}" type="hidden" class="ipt iptW435" value="${item.item_name}_${item.item_id}" name="contentEdit" />
                </p>
            </#list>
            <a href="javascript:;" class="addItemEdit" onclick="return addItemsEdit();">添加选项</a>
        <#else>
            <p class="clr">
                <span class="fl lNum">1</span><input type="text" class="input250" placeholder="最长20个汉字" maxlength="20" name="content" />
            </p>
            <p class="clr">
                <span class="fl lNum">2</span><input type="text" class="input250" placeholder="最长20个汉字" maxlength="20" name="content" />
                <a href="javascript:;" class="del delItem"><img src="/images/delete.png"></a>
            </p>
            <a href="javascript:;" class="addItem" onclick="return addItems('item');">添加选项</a>
        </#if>
    </div>

    <div id="operation">
        <input id="vote_submit" type="button" value="<#if (0 == vote.vote_id)>保 存<#else>修 改</#if>" class="button icon_save" />&nbsp;&nbsp;
        <input type="button" value="返 回" class="button icon_return" onClick="javascript:history.back();" />
    </div>
    <@s.formHiddenInput "vote.vote_id" />
    <@s.formHiddenInput "vote.vote_status" />
    <@s.formHiddenInput "vote.vote_create_time" />
    <@s.formHiddenInput "vote.vote_user_counts" />
</form>
<#include "MainBottom.ftl" />
