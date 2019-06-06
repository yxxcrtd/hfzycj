function updateStatus(id, approveStatus) {
    var status = 0;
    var nowStatus = $("#" + id + "").text();
    if ("显示" == nowStatus) {
        status = 1;
    }
    var idArray = [id];
    updateStatusByIds(idArray, status, updateStatusUrl());
}

/* Ajax异步修改显示状态 */
function updateStatusByIds(idArray, status, url) {
    // debugger;
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
            });
        }
    });
}

/* 反馈信息更新状态地址 */
function updateStatusUrl() {
    return $("#contextPath").val() + '/manage/park/updateStatus';
}
