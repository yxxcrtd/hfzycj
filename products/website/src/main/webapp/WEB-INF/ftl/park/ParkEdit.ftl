<#include "MainTop.ftl" />
<form action="${request.contextPath}/manage/park/save" method="post" enctype="multipart/form-data">

    <div class="label">停车场编码：<span class="star">*</span></div>
    <div>
    <@s.formInput "park.park_code" "class='input500'" /><@s.showErrors classOrStyle="red" />
    </div>

    <div class="label">停车场名称：<span class="star">*</span></div>
    <div>
    <@s.formInput "park.park_name" "class='input500'" /><@s.showErrors classOrStyle="red" />
    </div>

    <div class="label">总车位数：<span class="star">*</span></div>
    <div>
    <@s.formInput "park.park_total" "class='input200'" /><@s.showErrors classOrStyle="red" />
    </div>

    <div class="label">获取停车场的X、Y坐标：<span class="star">*</span></div>
    <div>
        x:<@s.formInput "park.park_coordinate_x" "class='input160'" /><@s.showErrors classOrStyle="red" />&nbsp;&nbsp;
        y:<@s.formInput "park.park_coordinate_y" "class='input160'" /><@s.showErrors classOrStyle="red" />
        <a href="#" class="big-link" data-reveal-id="myModal">【打开地图】</a>
    </div>

    <div class="label2"></div>
    <div>
        停车场类型：
        <td>
            <input name="park_type" type="radio" value="0" checked="checked"/> 地下停车场
            <input name="park_type" type="radio" value="1" <#if (park.park_type == 1 )>checked="checked"</#if>/> 地面停车场
        </td>
    </div>

    <div class="label2"></div>
    <div>
        停车场来源：
        <td>
            <input name="park_source" type="radio" value="1" checked="checked" /> 外部停车场
            <input name="park_source" type="radio" value="2" <#if (park.park_source == 2)>checked="checked"</#if>/> 内部停车场
        </td>
    </div>

    <div class="label2">停车场地址：<span class="star">*</span></div>
    <div>
    <@s.formInput "park.park_address" "class='input500'" /><@s.showErrors classOrStyle="red" />
    </div>

    <div class="label">停车场收费编码：<span class="star">*</span></div>
    <div>
    <@s.formInput "park.park_charge_code" "class='input500'" /><@s.showErrors classOrStyle="red" />
    </div>

    <div class="label">停车场收费说明：<span class="star">*</span></div>
    <div>
    <@s.formTextarea "park.park_describe" "class='input' style='width: 800px; height: 100px;'" /><@s.showErrors classOrStyle="red" />
    </div>

    <div class="label">停车场经营公司名称：<span class="star">*</span></div>
    <div>
    <@s.formInput "park.park_operating_company" "class='input500'" /><@s.showErrors classOrStyle="red" />
    </div>
    <div class="label">停车场联系人：<span class="star">*</span></div>
    <div>
    <@s.formInput "park.park_contact_name" "class='input500'" /><@s.showErrors classOrStyle="red" />
    </div>
    <div class="label">停车场联系电话：<span class="star">*</span></div>
    <div>
    <@s.formInput "park.park_tel" "class='input500'" /><@s.showErrors classOrStyle="red" />
    </div>

    <div id="objPicture">
        <div class="label">LOGO 图标：（图片的尺寸：宽20像素 * 高20像素）<span class="star">*</span></div>
        <div>
            <input type="file" id="file" class="input" style="width: 100px; padding: 3px!important;" name="file"/>
            <img src="/upload/${park.park_logo}" style="width: 30px; height: 30px;"/>
        </div>
    </div>
    <div class="label">停车场标签：<span class="star">*</span> <a href="#" class="big-link" data-reveal-id="myModal_lable"
                                                            style="color: #0000FF">【选择标签】</a></div>
    <div>

    </div>
    <div>
        <input class="input500" id="communal_ids" name="communal_ids" value="${communal_ids}" readonly="readonly"
               type="text">
    </div>
    <div>

    </div>
    <div id="operation">
        <input type="submit" value="<#if (0 == park.park_id)>保 存<#else>修 改</#if>" class="button icon_save"/>&nbsp;&nbsp;
        <input type="button" value="返 回" class="button icon_return" onClick="javascript:history.back();"/>
    </div>
<@s.formHiddenInput "park.park_id" />
<@s.formHiddenInput "park.park_status" />
<@s.formHiddenInput "park.park_create_time" />
<@s.formHiddenInput "park.park_logo" />
</form>
<#include "MainBottom.ftl" />
<div id="myModal_lable" class="reveal-modal" style="width: 800px; height: 400px;margin-right: 200px; text-align: left;">
    <table width="800px;" border="0" cellspacing="0" cellpadding="0" style="border: 0px solid #696969;">
    <#--<tr>-->
    <#--<td content="8">-->
    <#--<input id="chkAll" type="checkbox" />全选/反选-->
    <#--</td>-->

    <#--</tr>-->
    <#if (listMap?? && 0 < listMap?size)>
        <#list listMap as data >
            <#if ('1' == '${data["pid"]}')>
                <tr style="background-color: #FFFFFF;" onMouseOver="changeBgColor(this, '#ECECEC');"
                    onMouseOut="changeBgColor(this, '#FFFFFF');">
                    <td style="padding-left:5px;text-align: left;font-weight:bold;border-bottom: 0px solid #00FF00;">${data["name"]}</td>
                </tr>
            <#else>
                <#if ('1' == '${data["order"]}')>
                <tr>
                <td style="border-bottom: 0px solid #00FF00;text-align: left;padding-left: 1px; ">
                <table border="0" cellspacing="0" cellpadding="0" style="border: 0px solid #696969;margin-left: 0px;">
                <tr>
                    <td style="padding-left: 10px; border: 0px solid #00FF00; width: 80px;text-align: left;">
                        <input type="checkbox" name="commType" id="commType_${data["id"]}"
                            <#if (pcList?? && 0 < pcList?size)>
                                <#list pcList as pclistdata >
                                    <#if  '${pclistdata.parkcommunal_communalid}'='${data["id"]}'>  checked </#if>
                                </#list>
                            </#if>

                               value="${data["id"]}"/> ${data["name"]}
                    </td>
                <#else>
                    <td style="padding-left: 10px; border: 0px solid #337FE5;width: 80px;text-align: left;">
                        <input type="checkbox" name="commType" id="commType_${data["id"]}"

                            <#if (pcList?? && 0 < pcList?size)>
                                <#list pcList as pclistdata >
                                    <#if  '${pclistdata.parkcommunal_communalid}'='${data["id"]}'>  checked </#if>
                                </#list>
                            </#if>

                               value="${data["id"]}"/> ${data["name"]}
                    </td>
                </#if>
                <#if ('10' == '${data["order"]}')>
                </tr>
                <tr>
                </#if>
                <#if ('${data["count"]}' == '${data["order"]}')>
                </tr>
                </table>
                </td>
                </tr>
                </#if>
            </#if>
        </#list>
    <#else>
        <tr>
            <td colspan="7">没有数据！</td>
        </tr>
    </#if>
    </table>
    <a class="close-reveal-modal">&#215;</a>
</div>
<script type="text/javascript">
    $(function () {
        // chkAll全选事件
        $("#chkAll").bind("click", function () {
            $("[name = commType]:checkbox").attr("checked", this.checked);
        });

        // chkItem事件
        $("[name = commType]:checkbox").bind("click", function () {


            var all_checked = true;
            var sj = "";
            $(":checkbox").each(function () {
                if (this.checked == true) {
                    all_checked = false;
                    if (sj == "") {
                        sj = this.value;
                    }
                    else {
                        sj = sj + "," + this.value;
                    }
                }
            });

            $("#communal_ids").attr("value", sj);
//            alert(sj);
//            console.info("dddddddddddddddddddddddddddddd");
//            var $chk = $("[name = commType]:checkbox");
//            alert("dddddddddddd"+$chk.attr('value'));
//            alert("dddddddddddd"+$chk.is(':checked'));
////            $("input[type='checkbox']").is(':checked')
//            console.info($chk);
////            $("#chkAll").attr("checked", $chk.length == $chk.filter(":checked").length);
        })
    });
</script>

<div id="myModal" class="reveal-modal" style="width: 800px; height: 400px;">
    <div id="iControlbox" style="float:left ;margin-top:-25px; margin-left: -30px; width: 100%; ">
        搜索：<input type="text" id="keyword" value="" style="border: 1px solid #8b8b8b; background-color: #ffffff;"/>&nbsp;&nbsp;&nbsp;
        坐标X:<input type="text" id="X" value=""/>&nbsp;&nbsp;
        坐标Y:<input type="text" id="Y" value=""/>
    </div>
    <div id="container" style="float: left;margin-top:30px;display:block;"></div>
    <a class="close-reveal-modal">&#215;</a>
</div>
<link rel="stylesheet" href="http://cache.amap.com/lbs/static/main1119.css"/>
<link type="text/css" rel="styleSheet" href="${request.contextPath}/css/reveal.css"/>
<style type="text/css">
    .big-link {
        text-align: left;
        font-size: 13px;
        color: #00FF00;
    }
</style>
<script type="text/javascript" src="${request.contextPath}/js/jquery-1.4.4.min.js"></script>
<script type="text/javascript" src="${request.contextPath}/js/jquery.reveal.min.js"></script>
<script src="http://cache.amap.com/lbs/static/es5.min.js"></script>
<script language="javascript"
        src="http://webapi.amap.com/maps?v=1.3&key=3c27d24d3757d478d9e8396cdbd5c875&plugin=AMap.PlaceSearch,AMap.AdvancedInfoWindow,AMap.Autocomplete"></script>
<script type="text/javascript" src="${request.contextPath}/js/map.min.js"></script>
<script language="javascript" src="${request.contextPath}/js/kindeditor.min.js"></script>
<script type="text/javascript">
    $(document).ready(function () {
        //初始化地图
        initMap();
        //定位
        getgprs();
    });

    //编辑器
    var editor;
    KindEditor.ready(function(K) {
        editor = K.create("#park_describe", {
            newlineTag : "br"
        });
    });

</script>
