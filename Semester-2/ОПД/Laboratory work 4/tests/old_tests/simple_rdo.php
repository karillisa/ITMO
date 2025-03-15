<h4>Нажимайте пробел, в тот момент, когда шарик пересекает самую веррхнюю точку окружности (время теста 1 минута)</h4>
<h6 class="text-info">Нажмите любую из предложенных клавиш, чтобы начать</h6>

<form action="../scripts/save_test_results.php" method="post">

    <div class="test">
        <div class="timer">
            <span id="counter"></span>
        </div>

        <div class="box">
            <div id="light_box">
                <div id="top"></div>
                <div id="ball"></div>
            </div>
        </div>

        <div class="space" id="space">
            <svg fill="#000000" width="200px" height="70px" viewBox="0 0 24 24" xmlns="http://www.w3.org/2000/svg"
                 enable-background="new 0 0 24 24">
                <path d="M21,9c-0.6,0-1,0.4-1,1v3H4v-3c0-0.6-0.4-1-1-1s-1,0.4-1,1v4c0,0.6,0.4,1,1,1h18c0.6,0,1-0.4,1-1v-4C22,9.4,21.6,9,21,9z"/>
            </svg>
        </div>

        <input type="hidden" name="test_id" value="<?= $test_id ?>">
        <input id="result" type="hidden" name="result" value="0">

    </div>
    <div class="next_test mt-5">
        <h2>Отклонение от мгновенной реакции: <span id="result_span"></span> мс</h2>
        <h2>Отклонение от мгновенной реакции с учетом знака: <span id="right_answers_span"></span> мс</h2>
        <button type="submit" class="btn btn-success mb-3 mt-5 ml-3">Следующий тест</button>
    </div>
</form>
<script>
    var test_start = false;
    var test_end = false;
    var total_score = 0;
    var results = [];
    var last_light_time = 0;
    var current_color = null;
    var ball = document.getElementById("ball");
    var round_time = 1000;
    var curr_ang = 0;

    function animation() {
        var radius = 150
        var speed = round_time / 360
        var s = Math.PI / 360;
        setInterval(function() {
            curr_ang = (curr_ang - s) % (Math.PI*2);
            ball.style.marginLeft =  143 + radius * Math.sin(curr_ang)  + 'px';
            ball.style.marginTop =   143 + radius * Math.cos(curr_ang) + 'px';
        }, speed)
    }

    var seconds = <?= $test_time ?>;

    function testStart() {
        test_start = true;
        interval = 400 + Math.random() * 3000
        animation();
        countdown();
    }


    function reaction(key) {
        var deviation = +curr_ang+Math.PI;
        var deviation_time = deviation/Math.PI*round_time;
        results.push(deviation_time)
        console.log(deviation, deviation_time)
    }

    function endTest(){
        const average = arr => arr.reduce((p, c) => p + c, 0) / arr.length;
        const mean = Math.round(average(results));
        console.log("Mean: ", mean)
        var S = 0;
        var diff_summ = 0;
        for(let i = 0; i < results.length; i++){
            diff_summ += Math.pow(results[i],2)
        }
        diff_summ /= results.length;
        S = Math.sqrt(diff_summ)*mean/Math.abs(mean)
        S = Math.round(S*100)/100
        console.log("S: ", S)
        $("#result").val(Math.abs(S))
        $("#result_span").text(S)
        $("#right_answers_span").text(Math.abs(S))
        $('.test').hide();
        $('.next_test').show();
        test_end = true;
    }

    $('body').keydown(function (e) {
        if (e.keyCode == 32) {
            e.preventDefault();
            if (test_start)
                reaction();
            else
                testStart();
            $("#space").css("background", "#7bde65")
        }
    });

    $('body').keyup(function (e) {
        if (e.keyCode == 32) {
            $("#space").css("background", "#ffffff")
        }
    });

    function countdown() {


        function tick() {
            var counter = document.getElementById("counter");
            seconds--;
            counter.innerHTML =
                "0:" + (seconds < 10 ? "0" : "") + String(seconds);
            if (seconds > 0) {
                setTimeout(tick, 1000);
            } else {
                document.getElementById("counter").innerHTML = "";
                endTest()
            }
        }

        tick();
    }


</script>

<style>
    .box {
        background-color: #ffffff;
        width: 800px;
        height: 400px;
        padding: 50px;
        border-radius: 10px;
        border: 3px solid black;
    }
    .box #top {
        width: 6px;
        height: 6px;
        background-color: #000000;
        border-radius: 50%;
        margin: -3px auto auto 147px;
        display: block;
        position: absolute;
    }
    .box #ball {
        width: 14px;
        height: 14px;
        background-color: #a80000;
        border-radius: 50%;
        margin: 293px auto auto 147px;
        display: block;
        position: absolute;
    }

    .box #light_box {
        width: 300px;
        height: 300px;
        background-color: #ffd82c;
        border-radius: 50%;
        margin: 0 auto;
        display: block;
        border: 1px solid black;

    }


    .space {
        margin-top: 25px;
        margin-left: 220px;
        width: 300px;
        height: 70px;
        border: 4px solid #262626;
        border-radius: 15px;
        transition: background-color 0.2s ease;
    }

    .space svg {
        width: 150px;
        height: 50px;
        display: block;
        margin: 7px auto;
    }

    .space svg {
        width: 150px;
        height: 50px;
        display: block;
        margin: 7px auto;
    }

    .timer {
        height: 40px;
        font-size: 20px;
    }
    .next_test {
        display: none;
    }
</style>