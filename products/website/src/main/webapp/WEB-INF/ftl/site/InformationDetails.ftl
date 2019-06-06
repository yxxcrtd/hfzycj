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

            <div id="information_detail">
                <div id="information_detail_left"><#include "InformationDetail.ftl" /></div>
                <div id="page_right"><#include "PageRight.ftl" /></div>
            </div>

            <div class="extend">
                <div id="footer"></div>
            </div>
        </div>
        <script language="javaScript" src="../js/jquery-1.11.1.min.js"></script>
        <script language="javaScript" src="../js/public.min.js"></script>
        <script type="text/javascript">
            $.get("/site/view", { 'id' : ${information.information_id}, 'opt' : 1 }).done(function (data) {
                $("#hit").html(data);
            });
        </script>
    </body>
</html>