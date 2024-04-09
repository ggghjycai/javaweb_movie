$(function(){

    $(".list-box").click(function (e){
        var movieId = $(this).attr("id");
        window.location.href = "index/detail?movieId=" + movieId;
    });

    $(".type-a").click(function(e) {
        op(this, "type-a", "type-a-new");
    });
    $(".area-a").click(function(e) {
        op(this, "area-a", "area-a-new");
    });
    $(".g-a").click(function(e) {
        op(this, "g-a", "g-a-new");
    });
    $(".p-a").click(function(e) {
        op(this, "p-a", "p-a-new");
    });

    function op(e, class1, class2){
        var temp = $("." + class2);
        temp.attr("class", class1);
        $(e).attr("class", class2);
        submit();
        $(e).attr("class", class1);
        temp.attr("class", class2);
    }

    function submit(){
        const type = $(".type-a-new").text();
        const area = $(".area-a-new").text();
        const g = $(".g-a-new").text();
        const p = $(".p-a-new").text();

        var url = window.location.search;
        var search = decodeURIComponent(url);
        search = search.substring(1);
        var tag = 0;
        var text = "";
        if (search[1] === 'a'){
            var split = search.split("&");
                tag = split[0].split("=")[1];     /*tag是区分和主演*/
                text = split[1].split("=")[1];    /*这里是电影的名字   主演的名字*/
        }
        window.location.href = "/index?tag=" +tag + "&text=" + text + "&type=" + type + "&area=" + area + "&g=" + g + "&p=" + p;
    }
});