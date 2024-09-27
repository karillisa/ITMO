/*
function notify() {
    $.notify("Появилась новая профессия! Зайдите в приложение, чтобы пройти экспертизу!",
        {
            autoHideDelay: 20000,
            className: 'success',
            clickToHide: true,
            autoHide: true,
            arrowSize: 10,
            elementPosition: 'bottom right',
            globalPosition: 'bottom right',

        });
}

var res = 0;
var new_res = 0;
function getProfessionCount(){
    $.ajax({
        url: "/scripts/profession_count.php",
        method: "get"
    }).done(function(data) {
        if(res == 0) {
            res = data
            new_res = data
        }
        else
            new_res = data;
    });
}
getProfessionCount();


setInterval(function (){
    if(new_res > res){
        notify();
    }
    res = new_res
    getProfessionCount();
}, 6000)*/
