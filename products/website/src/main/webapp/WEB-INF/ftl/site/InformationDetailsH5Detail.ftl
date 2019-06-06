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
        <div class="h5_news">
            <div class="h5_title">${information.information_title}</div>
            <div class="h5_time">${information.information_create_time?string("yyyy-MM-dd HH:mm:ss")}</div>
            <div class="h5_summary">${information.information_summary}</div>
            <#if (information.information_attachment_url?? && 0 < information.information_attachment_url?length)>
                <img src="/upload/${information.information_attachment_url}" width="100%" />
            </#if>
            <div>${information.information_content}</div>
        </div>
    </body>
</html>