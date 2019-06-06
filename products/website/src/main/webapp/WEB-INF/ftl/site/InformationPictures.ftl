<div class="title"><a href="javascript:;">实景图集</a></div>
<div class="custom-container slower">
    <a href="#" class="prev" title="上一张"><img src="./images/left.png"></a>
    <div class="carousel">
        <ul>
        <#if (list?? && 0 < list?size)>
            <#list list as l>
                <#if (l.information_attachment_url?? && 0 < l.information_attachment_url?length)>
                    <li>
                        <img src="/upload/${l.information_attachment_url}" />
                    </li>
                </#if>
            </#list>
        </#if>
        </ul>
    </div>
    <a href="#" class="next" title="下一张"><img src="./images/right.png" /></a>
    <div class="clear"></div>
</div>
<script type="text/javascript">$(function () { $(".slower .carousel").jCarouselLite({ }); });</script>
