<script type="text/javascript" src="/js/jquery-1.11.1.min.js"></script>
<script language="javascript" type="text/javascript">
    //    $(document).ready(function () {
    //        getData(1);
    //    });
    function getData(pid) {
        var updatesurplus = $("#updatesurplus").val();
        var totalsurplus = $("#totalsurplus").val();
        $.ajax({
            type: "GET",  //提交方式POST
            url: "/site/menu",
            data: {
                "updatesurplus": updatesurplus,
                "totalsurplus": totalsurplus
            },
            success: function (result) {//返回数据根据结果进行相应的处理
                console.info(result);
                var jsondata = jQuery.parseJSON(result);
                console.info(jsondata);
                if (jsondata.code == "200") {
                    //登录成功
                    $("#showmsg").addClass("good");
                    $("#showmsg").html("车位校准成功");
                    console.info("校准成功" + result);
                    document.getElementById("updatesurplus").value = "";
                    document.getElementById("currentsurplus").value = jsondata.surPlus;
                }
                else {
                    $("#showmsg").addClass("error");
                    $("#showmsg").html(jsondata["code"] + "，" + jsondata["msg"]);
//                        $("#showmsg").innerHTML="登录失败，请重试" + result;
                    console.info("车位校准失败" + result);
                }
            },
            error: function (result) {//返回数据根据结果进行相应的处理
                $("#showmsg").addClass("error");
                $("#showmsg").html("" + result);
                console.info("车位校准失败" + result);
            }
        });
    }
</script>

<style type="text/css">
    body {
        background-color: #f1f1f1;
    }

    td#td1, td#td2 {
        width: 111px;
        border: 0px solid #FF0000;
        padding-left: 10px;
        vertical-align: middle;
        color: #787878;
        height: 30px;
        text-align: left;
    }

    td#td1 a, td#td2 a {
        color: #787878;
        /*padding-right: 70px;*/
    }

    td#td_all {
        padding-left: 5px;
        font-weight: bold;
        width: 225px;
        border-bottom: 1px solid #dadada;
        padding-top: 10px;
        padding-bottom: 10px;
        color: #787878;
        /*padding-right: 70px;*/
        text-align: left;
    }

    td#td_all a {
        padding-left: 5px;
        font-weight: bold;
        width: 225px;
        padding-top: 10px;
        padding-bottom: 10px;
        color: #787878;
        /*padding-right: 70px;*/
        text-align: left;
    }
</style>

<table border="0" cellspacing="0" cellpadding="0" style="width: 225px;">
    <tr>
        <td id="td_all">
            <a href="/site/parksearch?p=1">全部</a>
        </td>
    </tr>
<#if (listMap?? && 0 < listMap?size)>
    <#list listMap as data >
        <#if ('1' == '${data["pid"]}')>
            <tr>
                <td style="padding-left:5px; font-weight: bold;width: 225px;padding-top: 10px;text-align: left;">
                    <#if data.name?length lt 5 >
                    ${data.name}
                    <#else>
                    ${data.name?substring(0,4)}..
                    </#if>
                </td>
            </tr>
        <#else>
            <#if ('1' == '${data["order"]}')>
            <tr>
            <td>
            <table border="0" cellspacing="0" cellpadding="0" style="border: 0px solid #FF0000;width: 223px;">
            <tr>
                <td id="td1" style="text-align: left;">
                    <a href="/site/parksearch?p=1&keyword=${data.name}">
                        <#if data.name?length lt 5 >
                        ${data.name}
                        <#else>
                        ${data.name?substring(0,4)}..
                        </#if>
                    </a>
                </td>
            <#else>
                <td id="td2" style="text-align: left;">
                    <a href="/site/parksearch?p=1&keyword=${data.name}">
                        <#if data.name?length lt 5 >
                        ${data.name}
                        <#else>
                        ${data.name?substring(0,4)}..
                        </#if>
                    </a>
                </td>
            </#if>
            <#if ('1' == '${data.isbr}')>
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
        <td colspan="2">没有数据！</td>
    </tr>
</#if>
</table>