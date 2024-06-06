<h4>Нажимайте клавишу, соответствующего цвета, в тот момент, когда шарик пересекает самую веррхнюю точку окружности (время теста 1 минута)</h4>
<h6 class="text-info">Нажмите любую из предложенных клавиш, чтобы начать</h6>

<form action="../scripts/save_test_results.php" method="post">

    <div class="test">
        <div class="timer">
            <span id="counter"></span>
        </div>

        <div class="box">
                <div class="light_box red">
                    <div id="top"></div>
                    <div class="ball" id="ball"></div>
                </div>
                <div class="light_box blue">
                    <div id="top"></div>
                    <div class="ball" id="ball2"></div>
                </div>
                <div class="light_box green">
                    <div id="top"></div>
                    <div class="ball" id="ball3"></div>
                </div>
        </div>

        <div class="keys">
            <div class="key red z я">Z</div>
            <div class="key blue x ч">X</div>
            <div class="key green с c">C</div>
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
    var keys_colors = {
        z: 'red',
        x: 'blue',
        c: 'green',
        я: 'red',
        ч: 'blue',
        с: 'green',
    }
    var colors = ['red', 'blue', 'green']
    var ball_red = document.getElementById("ball");
    var ball_blue = document.getElementById("ball2");
    var ball_green = document.getElementById("ball3");
    var round_time = 1000;
    var round_time_red = 0;
    var round_time_blue = 0;
    var round_time_green = 0;
    var curr_ang_red = 0;
    var curr_ang_blue = 0;
    var curr_ang_green = 0;

    function animation() {
        var radius = 100
        var speed = round_time / 360
        var arr = [360, 480, 720];
        arr = shuffle(arr);
        var s_red = Math.PI / arr[0];
        var s_blue = Math.PI / arr[1];
        var s_green = Math.PI / arr[2];
        round_time_red = round_time/360*arr[0];
        round_time_blue = round_time/360*arr[1];
        round_time_green = round_time/360*arr[2];
        console.log("round_time_red",round_time_red)
        console.log("round_time_blue",round_time_blue)
        console.log("round_time_green",round_time_green)
        setInterval(function() {
            curr_ang_red = (curr_ang_red - s_red) % (Math.PI*2);
            ball_red.style.marginLeft =  93 + radius * Math.sin(curr_ang_red)  + 'px';
            ball_red.style.marginTop =   93 + radius * Math.cos(curr_ang_red) + 'px';
        }, speed)
        setInterval(function() {
            curr_ang_blue = (curr_ang_blue - s_blue) % (Math.PI*2);
            ball_blue.style.marginLeft =  93 + radius * Math.sin(curr_ang_blue)  + 'px';
            ball_blue.style.marginTop =   93 + radius * Math.cos(curr_ang_blue) + 'px';
        }, speed)
        setInterval(function() {
            curr_ang_green = (curr_ang_green - s_green) % (Math.PI*2);
            ball_green.style.marginLeft =  93 + radius * Math.sin(curr_ang_green)  + 'px';
            ball_green.style.marginTop =   93 + radius * Math.cos(curr_ang_green) + 'px';
        }, speed)
    }

    var seconds = <?= $test_time ?>;
    var seconds = 40;

    function testStart() {
        test_start = true;
        interval = 400 + Math.random() * 3000
        animation();
        countdown();
    }


    function reaction(key) {
        if(keys_colors[key] === "red"){
            var deviation = +curr_ang_red+Math.PI;
            var deviation_time = deviation/Math.PI*round_time_red;
            results.push(deviation_time)
            console.log("red: ",deviation, deviation_time)
        } else if(keys_colors[key] === "blue") {
            var deviation = +curr_ang_blue+Math.PI;
            var deviation_time = deviation/Math.PI*round_time_blue;
            results.push(deviation_time)
            console.log("blue: ",deviation, deviation_time)
        }else if(keys_colors[key] === "green") {
            var deviation = +curr_ang_green+Math.PI;
            var deviation_time = deviation/Math.PI*round_time_green;
            results.push(deviation_time)
            console.log("green: ",deviation, deviation_time)
        }

    }

    function endTest(){
        const average = arr => arr.reduce((p, c) => p + c, 0) / arr.length;
        const mean = Math.round(average(results));
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
        if(!test_end)
            if (e.key == 'z' || e.key == 'x' || e.key == 'c' ||
                e.key == 'я' || e.key == 'ч' || e.key == 'с') {
                e.preventDefault();
                if (test_start)
                    reaction(e.key);
                else
                    testStart();
                $(".keys .key."+e.key).css("background", "#ffffff")
            }
    });

    $('body').keyup(function (e) {
        $(".keys .key").removeAttr("style")
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
    function shuffle(array) {
        var currentIndex = array.length, temporaryValue, randomIndex;

        // Пока не дошли до конца массива - тасуем...
        while (0 !== currentIndex) {

            // берем оставшийся элемент
            randomIndex = Math.floor(Math.random() * currentIndex);
            currentIndex -= 1;

            // Меняем местами его с текущим элементом
            temporaryValue = array[currentIndex];
            array[currentIndex] = array[randomIndex];
            array[randomIndex] = temporaryValue;
        }

        return array;
    }

</script>

<style>
    .box {
        background-color: #ffffff;
        width: 1000px;
        height: 400px;
        padding: 50px;
        border-radius: 10px;
        border: 3px solid black;
    }

    .box .light_box {
        width: 200px;
        height: 200px;
        border-radius: 50%;
        margin: 45px;
        display: inline-block;
        border: 1px solid black;
    }

    .box #top {
        width: 6px;
        height: 6px;
        background-color: #000000;
        border-radius: 50%;
        margin: -3px auto auto 97px;
        display: block;
        position: absolute;
    }
    .box .ball {
        width: 14px;
        height: 14px;
        background-color: #a80000;
        border-radius: 50%;
        margin: 193px auto auto 93px;
        display: block;
        position: absolute;
    }




    .keys {
        margin-top: 25px;
        margin-left: 150px;
        width: 400px;
        height: 70px;
        margin-bottom: 20px;
    }

    .keys .key {
        width: 70px;
        height: 70px;
        border: 4px solid #262626;
        border-radius: 15px;
        transition: background-color 0.2s ease;
        display: inline-block;
        margin: 10px;
        color: white;
        font-size: 30px;
        padding: 10px 20px;
    }

    .red {
        background-color: #f6ae6e;
    }
    .blue {
        background-color: #7773fa;
    }
    .green {
        background-color: #a6e184;
    }

    .timer {
        height: 40px;
        font-size: 20px;
    }
    .next_test {
        display: none;
    }
</style>