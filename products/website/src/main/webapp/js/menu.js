// $("#file").on("change", function() {
// 	var file = this.files[0];
// 	$("#link_logo").attr("value", file.name);
// });

// 批量显示
$("#showAll").on("click", function () {
    if (confirm("确定要修改状态吗？")) {
        updateArr(0);
    }
});

// 批量隐藏
$("#hideAll").on("click", function () {
    if (confirm("确定要修改状态吗？")) {
        updateArr(1);
    }
});

/* 修改单个状态(显示/隐藏) */
function updateStatus(id) {
    var status = 0;
    var nowStatus = $("#" + id + "").text();
    if ("显示" == nowStatus) {
        status = 1;
    }
    var idArray = [id];
    updateStatusByIds(idArray, status, updateStatusUrl());
}

// 批量修改友情链接的状态
function updateArr(status) {
    var ids = getArray();
    if (null != ids && 0 < ids.length) {
        updateStatusByIds(ids, status, updateStatusUrl());
    } else {
        $("#tips").html("没有选择！");
        return false;
    }
}

/* Ajax异步修改显示状态 */
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
            });
        }
    });
}

/* 友情链接更新状态地址 */
function updateStatusUrl() {
    return $("#contextPath").val() + '/manage/menu/updateStatus';
}
