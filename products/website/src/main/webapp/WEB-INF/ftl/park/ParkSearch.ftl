<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>停车场搜索</title>
    <link rel="stylesheet" type="text/css" href="/css/style.min.css"/>
    <link rel="stylesheet" type="text/css" href="/css/park.css"/>
    <style type="text/css">
        body {
            background-color: #f1f1f1;
        }

        #ParkSearchList {
            width: 1400px;
            margin: 0 auto;
            border: 0px solid #696969;
            min-height: 20px;
            /*border: 7px solid red;*/
            /*border-color: red green yellow black;*/
            /*border-right: 10px solid gray;*/
            /*border-left: 10px solid gray;*/
            -moz-border-bottom- colors: #555 #666 #777 #888 #999 #aaa #bbb #ccc;
            -moz-border-top-colors: #555 #666 #777 #888 #999 #aaa #bbb #ccc;
            -moz-border-left-colors: #555 #666 #777 #888 #999 #aaa #bbb #ccc;
            -moz-border-right- colors: #555 #666 #777 #888 #999 #aaa #bbb #ccc;
        }

        div#parkmain {
            width: 1200px;
            margin: 0 auto;
            border: 0px solid #696969;
            min-height: 20px;
            -moz-border-bottom- colors: #555 #666 #777 #888 #999 #aaa #bbb #ccc;
            -moz-border-top-colors: #555 #666 #777 #888 #999 #aaa #bbb #ccc;
            -moz-border-left-colors: #555 #666 #777 #888 #999 #aaa #bbb #ccc;
            -moz-border-right- colors: #555 #666 #777 #888 #999 #aaa #bbb #ccc;
            background-color: #f1f1f1;
        }
    </style>
</head>
<body>


<div id="content">
    <div id="header">
        <div id="logo"></div>
        <div id="search"></div>
    </div>
    <div class="extend">
        <div id="nav"><#include "site/Nav.ftl" /></div>
    </div>
</div>
<div class="gap_row_40_gray1"></div>
<div id="parkmain">
    <div id="park_left"></div>
    <div id="park_split"></div>
    <div id="park_right">
    <#if (parkList?? && 0 < parkList?size)>
        <#list parkList as data>
            <div id="park_right_main">
                <div id="div_park_name">
                    <ul>
                        <li id="li1"><a href="/upload/park_${data.id}.html" target="_blank">${data.name}</a></li>
                        <li id="li2">${data.address}</li>
                        <li id="li2">收费类型：市场调节价</li>
                    </ul>
                </div>
                <div id="div_park_label">
                    <#assign cid = data.communalList + data.id>
                    <#if (data[data.communalListkey]?? && 0 < data[data.communalListkey]?size)>
                        <#list data[data.communalListkey] as cdata>
                            <label id="communal_label"
                                   onclick="toSearch('${cdata.communal_name}');">
                                <#if cdata.communal_name?length lt 5 >
                                ${cdata.communal_name}
                                <#else>
                                ${cdata.communal_name?substring(0,4)}..
                                </#if>
                            </label>
                        </#list>
                    </#if>
                </div>
                <#--<div id="div_park_surplus">${data.surplus} / ${data.total}<br/>停车位</div>-->
                <div id="div_park_surplus">${data.total}<br/>停车位</div>
            </div>
        </#list>
        <div id="park_right_main" style="border-bottom: none;"><#include "site/SitePager.ftl" /></div>
    <#else>
        <div id="park_right_main" style="border-bottom: none;">
            暂无数据
        </div>
    </#if>
    </div>
</div>

<div id="content">
    <div class="gap_row_40_gray"></div>
    <div class="extend">
        <div id="footer"></div>
    </div>
</div>
<script language="javaScript" src="../js/jquery-1.11.1.min.js"></script>
<script language="javaScript" src="../js/public.min.js"></script>
<script type="text/javascript">
    $(document).ready(function () {
        $("#park_left").load("/upload/mclist.html");
    });

    function downParkCommonalData() {
        $.ajax({
            type: "GET",
            url: "/site/menu",
            success: function (result) {//返回数据根据结果进行相应的处理
                var parkCommunalLable = document.getElementById("parkCommunalLable");
                parkCommunalLable.innerHTML = result;
            },
            error: function (result) {//返回数据根据结果进行相应的处理pswNoRight
                console.info("userLogin()error:" + result);
            }
        });
    }

    function toSearch(labelNames) {
        window.location.href = "/site/parksearch?p=1&keyword=" + labelNames;
    }
</script>
</body>
</html>