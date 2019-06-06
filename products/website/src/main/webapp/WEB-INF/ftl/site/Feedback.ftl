<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8"/>
        <div id="seo"></div>
        <link rel="stylesheet" type="text/css" href="../css/style.min.css"/>
    </head>

    <body>
        <div id="content">
            <div id="header">
                <div id="logo"></div>
                <div id="search"></div>
            </div>
            <div class="extend">
                <div id="nav"><#include "Nav.ftl" /></div>
            </div>
            <div class="gap_row_20"></div>
            <div class="gap_row_16"></div>

            <div id="feedback_detail">
                <div id="feedback_detail_left"><#include "FeedbackListLeft.ftl" /></div>
                <div id="page_right"><#include "PageRight.ftl" /></div>
            </div>

            <div class="extend">
                <div id="footer"></div>
            </div>
        </div>
        <script language="javaScript" src="../js/jquery-1.11.1.min.js"></script>
        <script language="javaScript" src="../js/jquery.jPages.min.js"></script>
        <script language="javaScript" src="../js/public.min.js"></script>
        <script type="text/javascript">
            $(function() {
                $(".holder").jPages({});
                <#if (list?? && 0 < list?size)>
                    <#list list as l>
                        $.get("/site/pretty", { 'time' : $("#prettyTime${l.feedback_id}").html() }).done(function (data) {
                            $("#prettyTime${l.feedback_id}").html(data);
                        });
                    </#list>
                </#if>
            });
        </script>
    </body>
</html>
