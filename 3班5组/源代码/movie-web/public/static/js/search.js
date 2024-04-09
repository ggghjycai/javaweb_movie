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
                tag = split[0].split("=")[1];
                text = split[1].split("=")[1];
        }
        window.location.href = "/index?tag=" +tag + "&text=" + text + "&type=" + type + "&area=" + area + "&g=" + g + "&p=" + p;
    }
});



const MyPage = {
    data() {
        return {
            searchList:[],
            pageNo:1,
            pageSize:10
        };
    },
    computed:{
        visiblePages: function() {
            const pages = [];
            // 如果总页数小于等于6，则直接显示所有页数
            if (this.pageSize <= 6) {
                for (let i = 1; i <= this.pageSize; i++) {
                    pages.push(i);
                }
            } else {
                // 否则，显示前三个和后三个页数，中间显示省略号
                for (let i = 1; i <= 3; i++) {
                    pages.push(i);
                }
                pages.push('...');

                for (let i = this.pageSize - 2; i <= this.pageSize; i++) {
                    pages.push(i);
                }
            }

            return pages;
        }
    }
};


const app = Vue.createApp(MyPage);
// 挂载应用到 #app 元素
app.mount('#page');

