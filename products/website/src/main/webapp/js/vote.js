/* 修改单个状态(显示/隐藏) */
function updateStatus(id, approveStatus) {
    var status = 0;
    var nowStatus = $("#" + id + "").text();
    if ("显示" == nowStatus) {
        status = 1;
    }
    var idArray = [id];
    updateStatusByIds(idArray, status, updateStatusUrl(), approveStatus);
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
            alert("对不起，服务器异常！");
        },
        success: function (data) {
            $.each(idArray, function (n, value) {
                $("#" + value + "").text(data);
            });
        }
    });
}

/* 投票管理更新状态地址 */
function updateStatusUrl() {
    return $("#contextPath").val() + '/manage/vote/updateStatus';
}

/* 投票选项的添加与删除*/
//删除当前选项

$(".delItem").bind("click", function () {
    $(this).parents("p").remove();
    voteQ();
});

//添加投票选项
function voteQ() {
    $(".location").find(".lNum").each(function (i) {
        $(this).text(i + 1);
    });
}



/* 表单提交保存*/
var lock = true; //为了防止表单重复提交，锁住表单  1.这里定义一把锁
$(function () {
    $("#vote_submit").click(function () {
        if (!lock) {    // 2.判断该锁是否打开，如果是关闭的，则直接返回
            return false;
        }
        lock = false  //3.进来后，立马把锁锁住
        var contentEdit = $("input[name='contentEdit']");
        $.ajax({
            type: "POST",
            // url: "${request.contextPath}/manage/vote/save",
            url: "/manage/vote/save",
            data: $("#voteForm").serialize(),
            error: function () {
                alert("sorry, 服务器异常!");
            },
            success: function (data) {
                console.info("保存成功！");
                lock = true;
                if ("saveSuccess" == data) {
                    window.location.href = "/manage/vote";
                }
            }
        });
    });
});

// 添加投票项
function addItems(obj) {
    var c = $("input[name='content']").length;
    if (15 == c) {
        alert("您只能添加15个投票项！");
    } else {
        // 当文本框中的值不为空的是时候再去验证重复
        // if ("" != $("input[name='content']").val()) {
        //     // verifySame(function(obj) {
        //     //     if(obj.parent().find(".redTxt").length == 0){
        //     //         obj.parent().append('<span class="redTxt">不能创建相同的投票选项</span>');
        //     //     }
        //     // });
        // }
        c = c + 1;
        var html = '<p class="clr"><span class="fl lNum">' + c + '</span><input type="text" class="input250" placeholder="最长20个汉字" maxlength="20" name="content" value="" /><a href="javascript:;" class="del delItem"><img src="/images/delete.png"></a></p>';
        $(".addItem").before(html);
    }
    return false;
}

function addItemsEdit(id) {
    var c = $("input[name='content']").length + $("input[name='contentEdit']").length;
    if (15 == c) {
        alert("您只能添加15个投票项！");
    } else {
        c = c + 1;
        var html = '<p class="clr"><span class="fl lNum">' + c + '</span><input type="text" class="input250" placeholder="最长20个汉字" maxlength="20" name="content" value="" /><a href="javascript:;" class="del delItem"><img src="/images/delete.png"></a></p>';
        $(".addItemEdit").before(html);
    }
}

/*修改选项内容*/
function changerHiddenValue(id) {
    // var contentEdit = $("#hidden_" + id).val();
    // contentEdit = $("#tp_" + id).val() + "_" +id;
    document.getElementById("hidden_" + id).value = document.getElementById("tp_" + id).value + "_" + id;
}



