<head>
    <link type="text/css" rel="styleSheet" href="${request.contextPath}/css/style.css" />
</head>
<body>

<table width="1000px;" border="0" cellspacing="0" cellpadding="0" style="border: 0px solid #696969;margin-left: 4%">
    <div style="margin-top: 20px;margin-bottom: 20px;">
        <tr style="font-size: 30px;font-family: 微软雅黑;color: black">
            <td style="font-size: 30px;font-family: 微软雅黑;color: black;"><b>${park.park_name}</b></td>
            <td style="font-size: 18px;font-family: 微软雅黑;color: #A0A0A0;">当前空余车位数：${park.park_surplus}</td>
        </tr>
    </div>
    <div>
        <tr style="font-size: 18px;font-family: 微软雅黑;color: #A0A0A0">
            <td>${park.park_address}</td>
            <td>收费编号：${park.park_charge_code}</td>
            <td>总车位数：${park.park_total}</td>
            <td>联系人：${park.park_contact_name}</td>
            <td>经营企业：${park.park_operating_company}</td>
        </tr>
        <tr>
            <td><hr style="margin-top: 1%;margin-bottom: 15%" color="gainsboro" width="800%"/></td>
        </tr>
    </div>
</table>

    <div id="container" style=" width: 60%;height: 80%;margin-left: 4%"></div>
    <div style="margin-top: 3%;margin-bottom: 3%;font-size: larger;margin-left: 4%"><b>资费说明</b></br>
        <tr>${park.park_describe}</tr>
    </div>
    <div style="margin-top: 3%;margin-bottom: 3%;font-size: larger;margin-left: 4%"><b>发表评论</b></br>
        <tr>
            <textarea style="width: 62.47%;height: 20%;"></textarea>
        </tr></br>
        <tr style="margin-top: 2%; ">
            <td><input id="feedbackName" style="width: 15%;height: 4%" value="您的称呼"/></td>
            <td><input id="feedbackTel" style="margin-left: 5%;width: 15%;height: 4%" value="联系方式"/></td>
            <td><input type="button" style="margin-left: 15%;width: 10%;height: 4%" onclick="submitFeedback();" value="提交"/></td>
        </tr>
    </div>
    <div id="showmsg" style="margin-top: 1%;margin-left: 25%;"></div>


    <div style="margin-top: 74px;margin-bottom: 18px;font-size: larger;margin-left: 4%"><b>所有评论</b></br>
    <#if (feedbackList?? && 0 < feedbackList?size)>
        <#list feedbackList as fl>
                <div>
                    <div>用户${fl.feedback_name}：</div>
                    <div id="prettyTime${fl.feedback_id}" title="${fl.feedback_create_time?string("yyyy-MM-dd HH:mm:ss")}">${fl.feedback_create_time}</div>
                </div>
                <div>
                    ${fl.feedback_content}
                </div>
                <#if fl.feedback_reply_content?? && 0 < fl.feedback_reply_content?length>
                    <div>管理人员回复：</div>
                    <div>${fl.feedback_reply_content}</div>
                </#if>
                <hr>
        </#list>
    </#if>

        <#--<div class="feedback_list_top">-->
            <#--<div class="feedback_list_top_left">用户${l.feedback_name}：</div>-->
            <#--<div class="feedback_list_top_right" id="prettyTime${l.feedback_id}" title="${l.feedback_create_time?string("yyyy-MM-dd HH:mm:ss")}">${l.feedback_create_time}</div>-->
        <#--</div>-->

    </div>

</body>

<script type="text/javascript" src="/js/jquery-1.11.1.min.js"></script>
<script src="http://cache.amap.com/lbs/static/es5.min.js"></script>
<script src="http://webapi.amap.com/maps?v=1.3&key=3c27d24d3757d478d9e8396cdbd5c875&plugin=AMap.PlaceSearch,AMap.AdvancedInfoWindow,AMap.Autocomplete"></script>
<script src="http://cache.amap.com/lbs/static/addToolbar.min.js"></script>

<script>
    var pointx = ${park.park_coordinate_x};
    var pointy = ${park.park_coordinate_y};
    //创建地图,设定地图的中心点和级别
    var map = new AMap.Map("container", {
        resizeEnable: true,
        zoom: 13,
        center: [pointx, pointy]
    });

    map.plugin(["AMap.ToolBar"], function () {
        map.addControl(new AMap.ToolBar());
        //  map.addControl(new AMap.Scale());
        map.addControl(new AMap.OverView({isOpen:true}));
    });

    //定位点标注
    new AMap.Marker({
        map: map,
        position: [pointx, pointy],
        title: "${park.park_name}",
        offset: new AMap.Pixel(-8,1),
        content: '<div class="marker-route amap-marker marker-marker-bus-from">${park.park_surplus}</div>' ,
    });

    //提交评论
    function submitFeedback() {
        var content = $("textarea").val();
        var feedbackName = $("#feedbackName").val();
        var feedbackTel = $("#feedbackTel").val();
        $.ajax({
            type: 'post',
            url: '${request.contextPath}/site/feedbackSave',
            data: {
                "content": content,
                "feedbackName": feedbackName,
                "feedbackTel": feedbackTel,
                "category": 0
            },
            dataType: 'text',
            error: function () {
                console.info("sorry, 服务器异常!");
                $("#showmsg").html("服务器异常,反馈失败！");
            },
            success: function (data) {
                console.info("评论成功！");
                $("#showmsg").html("反馈成功，等待审核！");
            }
        });
    }
</script>




