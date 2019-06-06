function updateStatus(id) {
    if (confirm("确定要修改状态吗？")) {
        var status = 0;
        var nowStatus = $("#" + id + "").text();
        if ("上线" == nowStatus) {
            status = 1;
        }
        var idArray = [id];
        updateStatusByIds(idArray, status, updateStatusUrl());
    }
}

function updateStatusByIds(idArray, status, url) {
    $.ajax({
        url: url,
        data: {
            "idList": idArray,
            "status": status
        },
        type: 'post',
        async: false,
        dataType: 'text',
        error: function () {
            alert("sorry, 服务器异常!");
        },
        success: function (data) {
            $.each(idArray, function (n, value) {
                $("#" + value + "").text(data);
                if("上线" == data){
                    $("#" + value + "").text(data).css("color", "#00FF00");
                }else{
                    $("#" + value + "").text(data).css("color", "#FF0000");
                }

            });
        }
    });
}

/* 资讯更新状态地址 */
function updateStatusUrl() {
    return $("#contextPath").val() + '/manage/information/updateStatus';
}

