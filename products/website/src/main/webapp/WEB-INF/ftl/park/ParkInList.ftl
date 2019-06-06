<script type="text/javascript" src="/js/jquery-1.11.1.min.js"></script>
<script language="javascript" type="text/javascript">
     function getParkDetailPage() {
         debugger;s
         $.ajax({
             type: "GET",  //提交方式POST
             url: "/site/parkDetailPage",
             data: {
                 "name": ${data["name"]},
             },
             success: function (result) {//返回数据根据结果进行相应的处理
                     console.info("获取停车场详情页面成功！");
                 }
                 else {

                     console.info("获取停车场详情页面失败！");
                 }
             },
             error: function (result) {//返回数据根据结果进行相应的处理
                 console.info("服务器出现故障！");
             }
         });
     }
</script>

<table width="1000px;" border="0" cellspacing="0" cellpadding="0" style="border: 0px solid #696969;">
<#if (listMap?? && 0 < listMap?size)>
    <div>
        <#list listMap as data >

            <td style="padding-left: 30%; font-size: 30px;"><b>${data["name"]}</b>
                <hr style="margin-top: 15%;margin-bottom: 15%" color="gainsboro" width="300%"/>
            </td>

            <td><a href="/site/parkDetailPage?name='紫芳园三区'&id=2">详情页</a></td>
            <#--<td><a href="" onclick="getParkDetailPage();">详情页</a></td>-->

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

