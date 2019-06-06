<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8"/>
        <title>${information.information_title}</title>
        <link rel="stylesheet" type="text/css" href="../css/style.css"/>
        <meta name="format-detection" content="telephone=no">
        <meta name="apple-mobile-web-app-capable" content="yes">
        <meta name="apple-mobile-web-app-status-bar-style" content="black">
        <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=0"/>
    </head>

    <body>
        <#if (informationList?? && 0 < informationList?size)>
            <#list informationList as il>
                <div class="h5_news">
                    <a href="/upload/info_h5_${il.information_id}.html">
                        <div class="h5_title">${il.information_title}</div>
                        <div class="h5_time">${il.information_create_time?string("yyyy-MM-dd HH:mm:ss")}</div>
                        <div class="h5_summary">${il.information_summary}</div>
                        <div class="h5_detail">查看详情</div>
                    </a>
                </div>
            </#list>
        <#else>
            没有数据！
        </#if>
    </body>
</html>