// main.js

$(document).ready(function() {
    // 表单提交事件
    $('addItemForm').submit(function(event) {
        event.preventDefault();

        // 收集表单数据
        const formData = {
            movieName: $('#movieName').val(),
            genre: $('#genre').val(),
            productionPlace: $('#productionPlace').val(),
            date: $('#date').val(),
            rating: $('#rating').val(),
            weekHits: $('#weekHits').val(),
            monthHits: $('#monthHits').val(),
            allHits: $('#allHits').val(),
            picture: $('#picture').val(),
            introduction: $('#introduction').val(),
            videoUrl: $('#videoUrl').val(),
            videoVip: $('#videoVip').val()
        };

        // 发送GET请求到后端
        $.get("/manage/add", formData, function(result) {
            // 处理后端返回的结果
            alert(result);

            // 清空表单
            $('#addItemForm')[0].reset();
            $('addMovieModal').modal('hide');
            window.location.href="/index/manage";//
        });
    });


    $('#updateItemForm').submit(function(event) {
        event.preventDefault();

        // 收集表单数据
        const formData = {
            movieName: $('#movieid3').val(),
            genre: $('#genre3').val(),
            productionPlace: $('#productionPlace3').val(),
            date: $('#date3').val(),
            rating: $('#rating3').val(),
            weekHits: $('#weekHits3').val(),
            monthHits: $('#monthHits3').val(),
            allHits: $('#allHits3').val(),
            picture: $('#picture3').val(),
            introduction: $('#introduction3').val(),
            videoUrl: $('#videoUrl3').val(),
            videoVip: $('#videoVip3').val()
            // 收集表单数据的代码，类似添加功能的代码
        };

        // 发送GET请求到后端
        $.get("/manage/update", formData, function(result) {
            // 处理后端返回的结果
            alert(result);

            // 清空表单
            $('#updateItemForm')[0].reset();

            // 关闭模态框
            $('#updateMovieModal').modal('hide');
            window.location.href="/index/manage";
        });
    });


    $('#deleteItemForm').submit(function(event) {
        event.preventDefault();

        // 收集表单数据
        const formData = {
            movieName: $('#movieNameToDelete').val()
        };

        // 发送GET请求到后端
        $.get("/manage/delete", formData, function(result) {
            // 处理后端返回的结果
            alert(result);

            // 清空表单
            $('#deleteItemForm')[0].reset();

            // 关闭模态框
            $('#deleteMovieModal').modal('hide');
            window.location.href="/index/manage";
        });
    });


});
