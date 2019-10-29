//记录点击汉字次数
var checkClickNum = 0;

$(document).ready(function () {
    refreshImg();
    //触发鼠标点击图片事件
    document.getElementById('dv').onclick = function (e) {
        checkClickNum++;
        //当点击次数小于等于3时，进行创建标记以及保存坐标位置
        if (checkClickNum <= 3) {
            e = e || window.event;
            var x = e.offsetX || e.layerX;
            var y = e.offsetY || e.layerY;
            createMarker(x, y);
            saveCheckDataInfo(x, y);
        }
        //如果到第三次点击时，进行发送数据到后台进行处理
        if (checkClickNum === 3) {
            var imgCheckInfo = '[' + $("#imgCheckInfo")[0].innerText.substring(1) + ']';
            $.ajax({
                url: "/ImgCheck/checkImg",
                type: "POST",
                data: {"imgCheckInfo": imgCheckInfo},
                success: function (data) {
                    if (data.code == "200") {
                        //将提示信息进行返回
                        var a = $("#imgCheckText");
                        a.html(data.msg);
                        a[0].style.display = null;
                    }
                }
            });
        }
    }
});

//刷新图片
function refreshImg() {
    checkClickNum = 0;
    $("#imgCheckText")[0].style.display = "none";
    $("#imgCheckInfo")[0].innerText = "";
    $("i").remove(".marker");
    $("#showCheckImg").attr("src", "/ImgCheck/getImg?r=" + Math.random());
}

//点击时创建一个图标标记
function createMarker(x, y) {
    var i = document.createElement('i');
    i.className = 'marker fa fa-check-circle ';
    i.style.left = x - 10 + 'px';
    i.style.top = y - 10 + 'px';
    i.style.fontSize = "1.5em";
    i.style.color = "#FFFFFF";
    document.getElementById('dv').appendChild(i);
}

//保存点击图片的数据
function saveCheckDataInfo(x, y) {
    var imgCheckInfo = $("#imgCheckInfo");
    var text = imgCheckInfo[0].innerText;
    text += ',[' + x + ',' + y + ']'
    imgCheckInfo[0].innerText = text;
}