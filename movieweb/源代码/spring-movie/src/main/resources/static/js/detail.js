$(function(){
    $(".play-media").mouseover(function (e){
        $(".playerBottom").attr("class", "playerBottom"); // 设置新颜色
    });

    $(".play-media").mouseleave(function (e){
        $(".playerBottom").attr("class", "playerBottom down"); // 设置新颜色
    });
    //设置总时长
    var video = $("#videoPlay");
    var pTime = $("#pTime");
    var intervalIdTime = 1;
    var intervalIdSlider = 1;
    var temp = "100%";  //音量
    if (video.attr("src") !== undefined){
        video[0].onloadedmetadata = function (e){
            if (video[0].duration !== 0){
                var date = new Date(video[0].duration * 1000);
                var str = getTime(date);
                $("#dTime").text(str);
                intervalIdTime = setInterval(showVideoTime, 100);
                intervalIdSlider = setInterval(changeSlider, 100);
                video[0].play();

                $(".videoPlay").click(function (e){
                    var icon = $(".btnControlPlay");
                    if(video[0].paused === true){
                        icon.attr("class", "iconfont btnControlPlay icon-pause");
                        video[0].play();
                        intervalIdTime = setInterval(showVideoTime, 100);
                        intervalIdSlider = setInterval(changeSlider, 100);
                    }
                    else {
                        icon.attr("class", "iconfont btnControlPlay icon-play");
                        video[0].pause();
                        clearInterval(intervalIdTime);
                        clearInterval(intervalIdSlider);
                    }
                });

                $(".playbackRateBox li").click(function (e){
                    $(".playbackRateBox li").attr("class", "");
                    $(this).attr("class", "selected");
                    var rateX = $(this).text();
                    var rate = 1.0;
                    switch (rateX){
                        case "1.0x":rate = 1.0;break;
                        case "1.5x":rate = 1.5;break;
                        case "1.75x":rate = 1.75;break;
                        case "2.0x":rate = 2.0;break;
                        case "0.75x":rate = 0.75;break;
                        // case "0.5x":rate = 0.5;break;
                    }
                    video[0].playbackRate = rate;
                });

                $(".qualityBox li").click(function (e){
                    $(".qualityBox li").attr("class", "");
                    $(this).attr("class", "selected");
                    //切换片源
                });

                //音量
                $(".voiceWrap").click(function (e){
                    var y = e.pageY
                    var temp = $(".voiceSlider");
                    var voiceY = temp.offset().top;
                    var len = y - voiceY;
                    var a = len / temp.height();
                    a = 1 - a;
                    if(a < 0){
                        a = 0;
                    }
                    else if(a > 1){
                        a = 1;
                    }
                    $(".voiceSliderBg").css("height", a * 100 + "%");
                    $(".voiceSliderBlock").css("bottom", a * 100 - 2 + "%");
                    $(".voiceSliderPercent").text(a * 100 + "%");
                });

                $(".voiceSound").click(function (e){
                    var sound = $(".voiceSliderPercent");
                    if(sound.text() !== "0%"){
                        temp = sound.text();
                        $(".voiceSliderBg").css("height", 0 + "%");
                        $(".voiceSliderBlock").css("bottom",  -2 + "%");
                        $(this).attr("src", "/img/icon/sound-down.png");
                        sound.text("0%");
                    }
                    else {
                        $(this).attr("src", "/img/icon/sound.png");
                        $(".voiceSliderBg").css("height", temp);
                        var t = parseInt(temp.substring(0,temp.length-1));
                        $(".voiceSliderBlock").css("bottom",  t-2 + "%");
                    }
                });

                //进度条
                var sliderBox = $(".sliderBox");
                sliderBox.mousemove(function (e){
                    var x = e.pageX
                    var sliderX = $(this).offset().left;
                    var a = (sliderX + $(this).width() - x) /  $(this).width();
                    a = 1-a;
                    var sliderTime = $(".sliderTime");
                    sliderTime.css("left",a * 100 - 2 + "%");
                    $(".sliderTimeBlock").css("left",a * 100 + "%");
                    var date = new Date(video[0].currentTime * 1000);
                    var s = getTime(date);
                    sliderTime.text(s);
                });


                sliderBox.click(function (e){
                    var sliderLoaded = $(".sliderLoaded");
                    var sliderBg = $(".sliderBg");
                    var sliderBlock = $(".sliderBlock");
                    var x = e.pageX
                    var sliderX = $(this).offset().left;
                    var a = (sliderX + $(this).width() - x) /  $(this).width();
                    a = 1-a;
                    var loaded = sliderLoaded.width() / $(".sliderBox").width();
                    if(loaded < a){
                        sliderLoaded.css("width", a * 100 + "%");
                    }
                    sliderBg.css("width", a * 100 + "%");
                    sliderBlock.css("left", "calc(" + a * 100+ "% - 8px)");
                    update(a);
                });
            }
        };
    }
    else {
        openPopup();
        $("#openMembershipBtn").click(function (e){
            openMembership();
        });
        $("#closeBtn").click(function (e){
            $("#overlay").css("display","none");
        });
    }

    function openPopup() {
        $("#overlay").css("display","flex");
    }

    function openMembership() {
        window.location.href = "/user/information";
    }

    function update(a){
        video[0].currentTime = video[0].duration * a;
        showVideoTime();
    }
    function showVideoTime() {
        var date = new Date(video[0].currentTime * 1000);
        var str = getTime(date);
        pTime.text(str);

    }

    function getTime(date){
        var h = date.getHours()-8;
        var m = date.getMinutes();
        var s = date.getSeconds();
        h = h < 10 ? "0" + h : "" + h;
        m = m < 10 ? "0" + m : "" + m;
        s = s < 10 ? "0" + s : "" + s;
        return h + ":" + m + ":" + s;
    }

    function changeSlider(){
        var sliderLoaded = $(".sliderLoaded");
        var sliderBg = $(".sliderBg");
        var sliderBlock = $(".sliderBlock");
        var len = video[0].currentTime / video[0].duration;
        var loaded = sliderLoaded.width() / $(".sliderBox").width();
        if(loaded < len){
            sliderLoaded.css("width", len * 100 + "%");
        }
        sliderBg.css("width", len * 100 + "%");
        sliderBlock.css("left", "calc(" + len * 100+ "% - 8px)");
        showVideoTime();
    }

});