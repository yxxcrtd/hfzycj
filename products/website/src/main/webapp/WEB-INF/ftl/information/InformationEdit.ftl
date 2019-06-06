<#include "MainTop.ftl" />
    <form action="${request.contextPath}/manage/information/save" method="post" enctype="multipart/form-data">
        <div class="label">分类：<span class="star">*</span></div>
        <div id="informationType">
            <@s.formRadioButtons "information.information_type" informationTypeMap /><@s.showErrors classOrStyle="red" />
        </div>

        <div id="informationRule">
            <div class="label">法规分类：</div>
            <div>
                <@s.formRadioButtons "information.information_rule" informationRuleMap />
            </div>
        </div>

        <div class="label">标题：<span class="star">*</span></div>
        <div>
            <@s.formInput "information.information_title" "class='input500'" /><@s.showErrors classOrStyle="red" />
        </div>

        <div class="label">摘要：<span class="star">*</span></div>
        <div>
            <@s.formInput "information.information_summary" "class='input1000'" /><@s.showErrors classOrStyle="red" />
        </div>

        <div class="label">内容：<span class="star">*</span></div>
        <div>
            <@s.formTextarea "information.information_content" "class='input' style='width: 1200px; height: 500px;'" /><@s.showErrors classOrStyle="red" />
        </div>

        <div id="attachment">
            <div class="label">附件：</div>
            <div>
                <input type="file" id="img" onchange="document.getElementById('img').src=this.value;" class="input" style="width: 505px; padding: 3px!important;" name="file" />
                <#if (information.information_attachment_url?? && 0 < information.information_attachment_url?length)><img width="200" src="/upload/${information.information_attachment_url}" /></#if>
                <@s.formHiddenInput "information.information_attachment_name" />
                <@s.formHiddenInput "information.information_attachment_url" />
            </div>
        </div>

        <div class="label">排序：<span class="star">*</span></div>
        <div>
            <@s.formInput "information.information_order_by" "class='input'" /><@s.showErrors classOrStyle="red" />
        </div>

        <div id="operation">
            <input type="submit" value="<#if (0 == information.information_id)>保 存<#else>修 改</#if>" class="button icon_save" />&nbsp;&nbsp;
            <input type="button" value="返 回" class="button icon_return" onClick="javascript:history.back();" />
        </div>

        <@s.formHiddenInput "information.information_id" />
        <@s.formHiddenInput "information.information_hit" />
        <@s.formHiddenInput "information.information_status" />
        <@s.formHiddenInput "information.information_create_time" />
    </form>

    <script language="javascript" src="${request.contextPath}/js/kindeditor.min.js"></script>
    <script>
    <!--
        KindEditor.ready(function(K) {
            var editor = K.create("#information_content", { newlineTag : "br", pasteType : 1 });
        });
        $(function () {
            $("#informationRule").hide();
            if ("rules" == "${information.information_type}") {
                $("#informationRule").fadeIn();
            }
        });

        $("input[name='information_type']").on("click", function () {
            var val = $(this).val();
            if ("rules" == val) {
                $("#informationRule").fadeIn();
            } else {
                $("#informationRule").fadeOut();
            }
        })
    //-->
    </script>
<#include "MainBottom.ftl" />
