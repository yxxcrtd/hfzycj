<div id="park_detail_title">
    <#if (30 < park.park_name?length)>${park.park_name[0..29]}<#else>${park.park_name}</#if>
</div>
<div id="park_detail_address">
    <span id="park_detail_address_location_img"><img src="../images/position.png" width="18" height="22" alt=""></span>
    <span id="park_detail_address_location">${park.park_address}</span>
    <span class="park_detail_address_circle">&nbsp;P&nbsp;</span>
    <span class="park_detail_address_circle_content">${park.park_total}个</span>
    <span id="park_detail_address_right">
        <span class="park_detail_address_circle">&nbsp;#&nbsp;</span>
        <span class="park_detail_address_circle_content">${park.park_code}</span>
    </span>
</div>
<div id="park_detail_map"></div>
<div>
    <span class="park_detail_other_title">经营企业：</span>
    <span class="park_detail_other_content">${park.park_operating_company}</span>
</div>
<div>
    <span class="park_detail_other_title">收费标准：</span>
    <span class="park_detail_other_content"></span>
</div>
<div id="park_detail_describe">${park.park_describe}</div>