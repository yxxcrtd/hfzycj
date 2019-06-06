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
                <div id="information_detail_left"><#include "ParkDetailPageLeft.ftl" /></div>
                <div id="page_right"><#include "PageRight.ftl" /></div>
            </div>

            <div class="extend">
                <div id="footer"></div>
            </div>
        </div>
        <script language="javaScript" src="../js/jquery-1.11.1.min.js"></script>
        <script language="javaScript" src="../js/public.min.js"></script>
        <script src="es5.min.js"></script>
        <script src="http://webapi.amap.com/maps?v=1.3&key=3c27d24d3757d478d9e8396cdbd5c875&plugin=AMap.PlaceSearch,AMap.AdvancedInfoWindow,AMap.Autocomplete"></script>
        <script src="addToolbar.min.js"></script>
        <script type="text/javascript">
            var map = new AMap.Map("park_detail_map", {
                resizeEnable: true,
                zoom: 16,
                center: [${park.park_coordinate_x}, ${park.park_coordinate_y}],
                scrollWheel: false
            });
        </script>
    </body>
</html>

