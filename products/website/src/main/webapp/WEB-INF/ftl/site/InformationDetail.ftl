<div id="detail_title"><#if (30 < information.information_title?length)>${information.information_title[0..29]}<#else>${information.information_title}</#if></div>
<div class="hit_time">
    <img src="../images/hit.png">
    <span id="hit">${information.information_hit}</span>
    <span>${information.information_create_time?string("yyyy-MM-dd")}</span>
</div>
<hr>
<#if (information.information_attachment_url?? && 0 < information.information_attachment_url?length)>
    <div id="img">
        <img src="/upload/${information.information_attachment_url}" width="700" />
    </div>
</#if>
<div id="summary">${information.information_summary}</div>
<div id="content">${information.information_content}</div>