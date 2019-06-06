<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8"/>
    <div id="seo"></div>
    <link rel="stylesheet" type="text/css" href="../css/style.css"/>
</head>

<body>
<div id="content">
    <div id="header">
        <div id="logo"></div>
        <div id="search"></div>
    </div>
    <div class="extend">
        <div id="nav"><#include "/Nav.ftl" /></div>
    </div>
    <div class="gap_row_20"></div>
    <div class="gap_row_16"></div>


    <div id="feedback_detail">
    <#--公众监督列表-->
        <div id="feedback_detail_left" >
        <#if (feedbackList?? && 0 < feedbackList?size)>
            <#list feedbackList as fl>
                <div style="border: 1px solid #c1c1c1;width: 701px;height: 200px;margin-left: 200px;margin-top: 40px;margin-bottom: 30px;">

                <#--反馈内容-->
                    <div style="margin-top: 10px;font-size: 15px;color: #209cf3;font-family: 微软雅黑;">
                        <td><b>用户${fl.feedback_name}：</b></td>
                    </div>
                    <div style="margin-top: 16px;font-size: 15px;color: #424242;font-family: 微软雅黑;margin-top: 16px;margin-bottom: 18px;">
                        <td>${fl.feedback_content}</td>
                    </div>

                <#--管理员回复反馈-->
                    <div style="margin-top: 10px;font-size: 15px;color: #f3b227;font-family: 微软雅黑;">
                        <td><b>管理员回复：</b></td>
                    </div>
                    <div style="margin-top: 16px;font-size: 15px;color: #424242;font-family: 微软雅黑;margin-top: 16px;margin-bottom: 18px;">
                        <td>${fl.feedback_reply_content}</td>
                    </div>
                    <div style="text-align: right;font-size: 15px;color: #a2a2a2;font-family: 微软雅黑;">
                        <td>反馈时间：${fl.feedback_create_time}</td>
                    </div>


                </div>
            </#list>
        <#else>
            <div style="border: 1px solid #c1c1c1;width: 701px;height: 116px;margin-left: 200px;margin-top: 40px;margin-bottom: 130px;">
                数据为空
            </div>
        </#if>
        </div>

    <#--右边固定数据-->
        <div id="feedback_detail_right"></div>
    </div>


    <div class="extend">
        <div id="footer"></div>
    </div>
</div>
<script language="javaScript" src="../js/jquery-1.11.1.min.js"></script>
<script language="javaScript" src="../js/jquery.jPages.min.js"></script>
<script language="javaScript" src="../js/public.min.js"></script>
<script type="text/javascript">$(function() { $("div.holder").jPages({ }); });</script>
</body>
</html>