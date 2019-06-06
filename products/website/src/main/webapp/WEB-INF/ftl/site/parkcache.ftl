<div id="cacheParkList">


    <ul id="ulTitle">
        <li id="liTitle">
            <table width="100%">
                <tr>
                    <td>空闲</td>
                    <td>紧张</td>
                    <td>离线</td>
                </tr>
            </table>
        </li>
    </ul>
    <table width="100%">
        <tr style="border-bottom:1px solid #FF0000;">
            <td style="width: 50px;">状态</td>
            <td style="text-align: left;">停车场</td>
            <td>剩余车位数</td>
        </tr>
    <#if (listMap?? && 0 < listMap?size)>
        <#list listMap as l>
            <tr>
                <td>${l.countStatus}</td>
                <td style="text-align: left;">${l.name}</td>
                <td>${l.surplus}/${l.total}</td>
            </tr>
        </#list>
    <#else>
        <tr>
            <td colspan="3">没有数据！</td>
        </tr>
    </#if>
    </table>


</div>


<style type="text/css">
    table tr td {
        vertical-align: middle;
        text-align: center;
    }

    #cacheParkList {
        width: 300px;
        height: 600px;
        margin: 0 auto;
        border: 1px solid #696969;
    }

    #ulTitle {
        list-style-type: none;
        width: 300px;
        height: 50px;
        padding-left: 0px;
        margin-left: 0px;
        border: 0px solid #696969;
    }

    #liTitle {
        width: 299px;
        height: 50px;
        padding-left: 4px;
        margin-left: 0px;
        border-bottom: 1px solid #696969;
        text-align: center;
    }

    #mclist {
        width: 260px;
        /*margin-left: 0;*/
        margin-top: 2px;
        border: 1px solid #696969;
        position: relative;
        z-index: 1;
        margin-left: 390px;
    }
</style>