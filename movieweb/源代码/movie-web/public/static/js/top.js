$(function(){

    var url = window.location.search;
    var search = decodeURIComponent(url);
    search = search.substring(1);
    if (search != null && search !== ""){
        var split = search.split("&");
        for(var i = 0; i < split.length; i++){
            var s = split[i].split("=");
            if (s[0] === "tag"){
                if(s[1] === "0"){
                    $("#searchSelect").text("电影");
                }
                else if(s[1] === "1"){
                    $("#searchSelect").text("主演");
                }
            }
            else if(s[0] === "text"){
                $("#searchInput").val(s[1]);
            }
        }
    }

    $(".option").click(function(e) {
        $text = $(this).text();
        $(".search-select-text").text($text);
        e.stopPropagation(); // 阻止事件冒泡，避免触发一级菜单的点击事件
        $(this).parent().slideUp();
    });

    $(".search-button").click(function (e){
        searchMovie();
    });

    $('#searchInput').keypress(function(e){
        if(e.which === 13){
            searchMovie();
        }
    });

    function searchMovie(){
        var tagText = $("#searchSelect").text().trim();
        var text = $("#searchInput").val().trim();
        var tag = 0;
        if (tagText === "主演"){
            tag = 1;
        }
        window.location.href = "/index?tag=" + tag + "&text=" + text + "&type=全部&area=全部&g=全部&p=最热";
    }

    $("#userCenter").click(function (e){
        window.location.href = "/user/information";
    });
    $("#userCenter2").click(function (e){
        window.location.href = "/user/information";
    });

    $("#logOut").click(function (e){
        window.location.href = "/user/delete";
    });
    $("#logOut2").click(function (e){
        window.location.href = "/user/delete";
    });

    $("#toLogin").click(function (e){
        $.get("/index/isLogin", {
        },function(result) {
            if(result === "未登录"){
                window.location.href="/index/toLogin";
            }
            else{
                e.preventDefault();
                // 切换显示/隐藏
                $("#userOptions").slideToggle();
            }
        });
        return false;
    });$("#toLogin2").click(function (e){
        $.get("/index/isLogin", {
        },function(result) {
            if(result === "未登录"){
                window.location.href="/index/toLogin";
            }
            else{
                e.preventDefault();
                // 切换显示/隐藏
                $("#userOptions").slideToggle();
            }
        });
        return false;
    });
});