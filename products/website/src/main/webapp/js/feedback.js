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
function updateStatusByIds(idArray, status, url, approveStatus) {
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
               // $("#" + value + "").removeAttr('a');
            });
        }
    });
}

/* 反馈信息更新状态地址 */
function updateStatusUrl() {
    return $("#contextPath").val() + '/manage/feedback/updateStatus';
}

/* 对显示的反馈信息进行审核 */
var approveStatus = 0;
function updateApproveStatus(id) {
    if (confirm("请问该条反馈信息通过吗？")) {
        approveStatus = 1;
        updateApproveStatusById(id, approveStatus, updateApproveStatusUrl());
    }
}

/* Ajax异步修改审核状态 */
function updateApproveStatusById(id, approveStatus, url) {
    $.ajax({
        url: url,
        data: {
            "id": id,
            "approveStatus": approveStatus
        },
        type: 'post',
        async: false,
        dataType: 'text',
        error: function () {
            alert("sorry, 服务器异常!");
        },
        success: function (data) {
            var idArray = [id];
            $.each(idArray, function (n, value) {
                $("#" + value + "approveStatus").text(data).css("color", "#16b553");
                // $("#" + value + "approve").attr("href", "#");
                // $("#" + value + "approve").attr("onclick", "return false;");
                $("#" + value + "content").html($('#' + value + 'content').text()+"<span><a href='javascript:void(0);' onclick='openReplyDialog()'><img src='../images/reply.png' border='0' title='回复信息'/></a></span>");

            });
        }
    });
}

/* 反馈信息更新审核状态地址 */
function updateApproveStatusUrl() {
    return $("#contextPath").val() + '/manage/feedback/updateApproveStatus';
}

/* 打开回复输入框*/
// function openReply(id,replyContent) {
//     var imgs = $('img');
//     debugger;
//     for(var i=0;i<imgs.length;i++){
//         var titleVal = imgs[i].title;
//         if("回复信息" == titleVal){
//             var textareaNode = "<br /><textarea onblur='colseReply(" + id + ")' rows='2' cols='35' />";
//             $("#" + id + "replySpan").append(textareaNode);
//         }else{
//             var textareaNode = "<br /><textarea onblur='colseReply(" + id + ")' rows='2' cols='35' >"+replyContent+"</textarea>";
//             $("#" + id + "replySpan").append(textareaNode);
//         }
//     }

/* 打开回复输入框*/
function openReply(id,replyContent) {
    var textareaNode = "<br /><textarea onblur='colseReply(" + id + ")' rows='2' cols='35' >"+replyContent+"</textarea>";
    $("#" + id + "replySpan").append(textareaNode);
    $("#" + id + "reply").attr("onclick", "return false;");
}

/* 鼠标移出输入框后点击关闭*/
function colseReply(id) {
    var replyContent = $('textarea').val();
    $.ajax({
        url: $("#contextPath").val() + '/manage/feedback/saveRelayContent',
        data: {
            "id":id,
            "replyContent": replyContent
        },
        type: 'post',
        async: false,
        dataType: 'text',
        error: function () {
            alert("sorry, 服务器异常!");
        },
        success: function (data) {
            if(data == "回执成功"){
                $('textarea').hide();
                var content = $("#" + id + "replySpan").html();
                //var replyInput = "<br /><textarea onblur='colseReply(" + id + ")' value='"+replyContent+"' rows='2' cols='35' />";
                //$("#" + id + "replySpan").append(replyInput);
                $("#" + id + "replySpan").html(content+"<br />回复："+replyContent);
                //$("#" + id + "reply").attr("onclick", "return true;");
                $("#" + id + "reply").onclick = "return true";
            }
        }
    });
        return true;
}



