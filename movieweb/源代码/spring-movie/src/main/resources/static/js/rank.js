$(function(){

    $(".nav-select").click(function(e) {
        op(this, "nav-select", "nowBA");
    });

    $(".rank-type-a").click(function(e) {
        op(this,"rank-type-a", "nowBA");
    });

    $(".rank-area-a").click(function(e) {
        op(this, "rank-area-a", "nowBA");
    });

    function op(e, class1, class2){
        var temp = $("." + class1 + "." + class2);
        temp.attr("class", class1);
        $(e).attr("class", class1 + " " + class2);
        submit();
        $(e).attr("class", class1);
        temp.attr("class", class1 + " " + class2);
    }

    function submit() {
        var rank = $(".nav-select.nowBA").text();
        var type = $(".rank-type-a.nowBA").text();
        var area = $(".rank-area-a.nowBA").text();

        switch (rank){
            case "总": rank = "1";break;
            case "月": rank = "2";break;
            case "周": rank = "3";break;
            case "好评": rank = "4";break;
            default:rank = "1";break;
        }
        window.location.href = "/index/rank?pageNo=1&type=" + type + "&area=" + area + "&rank=" + rank;
    }

    $('.cl').mouseenter(function() {
        $(this).attr("class", "cl now"); // 设置新颜色
    });

    $('.cl').mouseleave(function() {
        $(this).attr("class", "cl"); // 恢复原色
    });
});