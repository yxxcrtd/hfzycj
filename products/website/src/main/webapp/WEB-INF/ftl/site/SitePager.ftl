<#if pager??>
    <#assign pageCount = pager.totalPageCount>
    <#assign pageSite = pager.pageSize>
    <#if (1 < pageCount)>
        <#assign offset = 5>
        <#assign current = pager.pageNo>
        <#assign maxStart = (pageCount - offset * 2)>
        <#if (1 > maxStart)>
            <#assign maxStart = 1>
        </#if>
        <#assign minEnd = (offset * 2 + 1)>
        <#assign start = (current - offset)>
        <#assign end = (current + offset)>
        <#if (1 > start)>
            <#assign start = 1>
            <#assign end = minEnd>
        </#if>
        <#if (end > pageCount)>
            <#assign end = pageCount>
            <#assign start = maxStart>
        </#if>
    <div id="sitePages">
    <form method="post" action="" name="qPagerForm">
        <span class="condition">总计 <span class="red">${count}</span> 条数据，每页 <span class="red">${pageSite}</span> 条，总计 <span class="red">${pageCount}</span> 页，当前第 <span class="red">${current}</span> 页</span>
        <#if (1 < current)>
            <a href="javascript:go(${current - 1});" class="arrow">上一页</a>
        </#if>
        <#if (1 < start)>
            <a href="javascript:go(1);">1</a>
        </#if>
        <#if (2 < start)>
            <span>...</span>
        </#if>
        <#list start..end - 1 as i>
            <#if (i == current)>
                <a href="javascript:go(${i});" class="active">${i}</a>
            <#else>
                <a href="javascript:go(${i});" class="active1">${i}</a>
            </#if>
        </#list>
        <#if (start < maxStart)>
            <span>...</span>
        </#if>
        <#if current == pageCount>
            <a href="javascript:go(${current});" class="active">${current}</a>
        <#else>
            <a href="javascript:go(${pageCount});" class="active1">${pageCount}</a>
            <a href="javascript:go(${current + 1});" class="arrow">下一页</a>
        </#if>


    </#if>
</#if>
<#if (mapSearchForm?? && 0 < mapSearchForm?size)>
    <#list mapSearchForm?keys as key>
        <input type="hidden" name="${key}" id="${key}" value="${mapSearchForm["${key}"]}">
    </#list>
</#if>
</form>
    <script type="text/javascript">
        function go(i) {
            var params = "";
            <#if (mapSearchForm?? && 0 < mapSearchForm?size)>
                <#list mapSearchForm?keys as key>
                    params = params + "&" + "${key}" + "=" + "${mapSearchForm["${key}"]}";
                </#list>
            </#if>
            var pageUr = "${pager.pageUrl}".replace("{k}", "${k}").replace("{p}", i) + params;
            window.location.href = pageUr;
        }
        function turnOverPage(no) {
            var qForm = document.qPagerForm;
            if (no >${pageCount}) {
                no =${pageCount};
            }
            if (no < 1) {
                no = 1;
            }
            qForm.action = "${pager.pageUrl}".replace("{p}", no);
            qForm.submit();
        }
    </script>
</div>
<style type="text/css">
    .arrow {
        color: #666666;
    }
    .active {
        color: #FF0000;
    }
    .active1 {
        color: #666666;
    }
    .condition {
        color: #666666;
        padding-left: 30px;
        padding-right: 30px;
    }
    div#sitePages {
        text-align: right;
        vertical-align: top;
        color: #666666;
    }
</style>