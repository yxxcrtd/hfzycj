$(function() {
	$("#seo").load("/upload/seo.html");
	$("#logo").load("/upload/logo.html");
	$("#search").load("/search.html");
	$("#link").load("/upload/link.html");
	$("#footer").load("/upload/copyright.html");
    $("#index_information_left").load("/upload/index_news.html");
    $("#index_information_right_top_left").load("/upload/index_rules.html");
    $("#index_information_right_top_right").load("/upload/index_notices.html");
    $("#index_information_right_bottom").load("/upload/index_pic.html");
    $("#park").load("/upload/index_park.html");
    $("#feedback").load("feedback.html");
    // $("#vote").load("/upload/index_vote.html");
    $("#feedback_box").load("/upload/index_feedback.html");
    $("#real_time").load("/parkcache.html");
    $("#right_notice").load("/upload/index_notices.html");
    $("#right_rule").load("/upload/index_rules.html");
    $("#mclist").load("/upload/mclist.html");
	$("#tips").fadeOut(10000);

    $("#fixedLayer").load("/upload/mclist.html");
    $("#parkcacheLayer").load("/parkcache.html");

	// 后台菜单
    $("#menu li ul").hide();
    $("#firstMenu").css("background-image", "url('/images/menu_minus.gif')");
    $.each($("#menu"), function() {
        $("#" + this.id + ".expandfirst ul:first").show();
    });
    $("#menu .subMenu").click(function() {
        $(this).next().slideToggle("normal");
        changeIcon($(this));
    });
});

function changeIcon(node) {
    if (node) {
        if (node.css("background-image").indexOf("/images/menu_plus.gif") >= 0) {
            node.css("background-image", "url('/images/menu_minus.gif')");
        } else {
            node.css("background-image", "url('/images/menu_plus.gif')");
        }
    }
}
