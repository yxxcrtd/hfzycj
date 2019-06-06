<script type="text/javascript" src="/js/jquery-1.11.1.min.js"></script>
<script language="javascript" type="text/javascript">

</script>

<table width="800px;" border="0" cellspacing="0" cellpadding="0" style="border: 0px solid #696969;">
<#if (listMap?? && 0 < listMap?size)>
    <div>
        <#list listMap as data >
            <td style="padding-left: 30%; font-size: 30px;"><b>${data["name"]}</b>
                <hr style="margin-top: 15%;margin-bottom: 15%" color="gainsboro" width="300%"/>
            </td>

            <td style="padding-left: 3%;margin-bottom: 100%; font-size: 18px;">
            ${data["address"]}
            </td>

            <td style="padding-left: 3%;margin-bottom: 100%; font-size: 18px;">
                <#list listMap2 as data2>
                    ${data2["name"]}
                </#list>
            </td>

            </tr>
        </#list>
    </div>

<#else>
    <tr>
        <td colspan="7">没有数据！</td>
    </tr>
</#if>
</table>

