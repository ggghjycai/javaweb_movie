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

const Area = {
    data() {
        return {
            area:[],
            searchList:[],
            pageNo:1,
            pageSize:10
        };
    },
    mounted() {
        // 在组件加载完成后发送 Axios 请求
        this.fetchData();
    },
    methods: {
        fetchData() {
            // 使用 Axios 发送 GET 请求
            axios.get('https://api.example.com/data')
                .then(response => {
                    // 成功获取数据后更新组件的 data
                    this.data = response.data;
                })
                .catch(error => {
                    // 处理错误
                    console.error('Error fetching data:', error);
                });
        }
    }
};


const app = Vue.createApp(Area);
// 挂载应用到 #app 元素
app.mount('#area');
