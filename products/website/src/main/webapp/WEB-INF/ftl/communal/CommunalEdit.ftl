<#include "MainTop.ftl" />
<link type="text/css" rel="styleSheet" href="${request.contextPath}/css/reveal.css"/>
<style type="text/css">
    .big-link {
        text-align: left;
        font-size: 13px;
        color: red;
    }
</style>
<script type="text/javascript" src="${request.contextPath}/js/jquery-1.4.4.min.js"></script>
<script type="text/javascript" src="${request.contextPath}/js/jquery.reveal.min.js"></script>
<form action="${request.contextPath}/manage/communal/save" method="post">
    <div class="label">菜单类别：<span class="star">*</span></div>
    <div>
        <select name="communal_menuid" id="communal.communal_menuid" class='input500'>
        <#list menuList as menu>
            <option value="${menu.menu_id}"
                    <#if ((('${communal.communal_menuid}')!'') == '${menu.menu_id}')>selected="selected"</#if>>${menu.menu_title}</option>
        </#list>
        </select>
    </div>
    <div class="label">名称：<span class="star">*</span></div>
    <div><@s.formInput "communal.communal_name" "class='input500'" /><@s.showErrors classOrStyle="red" /></div>

    <div class="label">地址：<span class="star">*</span></div>
    <div><@s.formInput "communal.communal_address" "class='input500'" /><@s.showErrors classOrStyle="red" /></div>

    <div class="label">选择坐标：<span class="star">*</span>
    </div>
    <div>
        x:<@s.formInput "communal.communal_coordinate_x"  /><@s.showErrors classOrStyle="red" />
        <br />
        y:<@s.formInput "communal.communal_coordinate_y"  /><@s.showErrors classOrStyle="red" />
        <br />
        <a href="#" class="big-link" data-reveal-id="myModal">【打开地图选择坐标】</a>
    </div>
    <div class="label">显示状态：<span class="star">*</span></div>
    <div>
    <@s.formRadioButtons "communal.communal_status" statusMap  />
    </div>

    <div class="label">排序：<span class="star">*</span></div>
    <div><@s.formInput "communal.communal_orderby" "class='input500'" /><@s.showErrors classOrStyle="red" /></div>
    <div id="operation">
    <@s.formHiddenInput "communal.communal_id" />
        <input type="submit" value="保 存" class="button icon_save"/>&nbsp;&nbsp;
        <input type="button" value="返 回" class="button icon_return" onClick="javascript:history.back();"/>
    </div>
</form>
<#include "MainBottom.ftl" />
<div id="myModal" class="reveal-modal" style="width: 600px; height: 400px;">
    <div id="iControlbox" style="float:left ;margin-top:-25px; margin-left: -30px; width: 100%; ">
        坐标X:<input type="text" id="X" value=""/>
        Y:<input type="text" id="Y" value=""/><input type="text" id="keyword" value=""
                                                     style="float:left ;border: 1px solid #D1D1D1; background-color: #f2f2f2;"/>
    </div>
    <div id="container" style="float: left;margin-top:30px;display:block;"></div>
    <a class="close-reveal-modal">&#215;</a>
</div>
<link rel="stylesheet" href="http://cache.amap.com/lbs/static/main1119.css"/>
<script src="http://cache.amap.com/lbs/static/es5.min.js"></script>
<script language="javascript"
        src="http://webapi.amap.com/maps?v=1.3&key=3c27d24d3757d478d9e8396cdbd5c875&plugin=AMap.PlaceSearch,AMap.AdvancedInfoWindow,AMap.Autocomplete"></script>
<script type="text/javascript" src="${request.contextPath}/js/map.min.js"></script>
<script type="text/javascript">
    $(document).ready(function () {
        //初始化地图
        fltname = "CommunalEdit.ftl";
        initMap();
        //定位
        getgprs();
    });
</script>