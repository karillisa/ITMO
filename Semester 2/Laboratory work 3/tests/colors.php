<h4>Нажимайте клавишу, соответствующую цвету сразу, когда загорится цвет (время теста 1 минута)</h4>
<h6 class="text-info">Нажмите любую из предложенных клавиш, чтобы начать</h6>

<form action="../scripts/save_test_results.php" method="post">

    <div class="test">
        <div class="timer">
            <span id="counter"></span>
        </div>

        <div class="box">
            <div class="color red"></div>
            <div class="color blue"></div>
            <div class="color yellow"></div>
            <div class="color green"></div>
        </div>

        <div class="keys">
            <div class="key red z я">Z</div>
            <div class="key blue x ч">X</div>
            <div class="key yellow c с">C</div>
            <div class="key green v м">V</div>
        </div>

        <input type="hidden" name="test_id" value="<?= $test_id ?>">
        <input id="result" type="hidden" name="result" value="0">

    </div>
    <div class="next_test mt-5">
        <h2>Средняя скорость реакции: <span id="result_span"></span> мс</h2>
        <h2>Количество успешных реакций: <span id="right_answers_span"></span></h2>
        <h2>Количество ошибок (пропуск засчитывается как ошибка): <span id="wrong_answers_span"></span></h2>
        <h2>Количество пропусков: <span id="pass_span"></span></h2>
        <button type="submit" class="btn btn-success mb-3 mt-5 ml-3">Следующий тест</button>
    </div>
</form>
<script>
    var test_start = false;
    var test_end = false;
    var total_score = 0, right_answers = 0, wrong_answers = 0, pass = 0;
    var results = [];
    var last_light_time = 0;
    var current_color = null;
    var keys_colors = {
        z: 'red',
        x: 'blue',
        c: 'yellow',
        v: 'green',
        я: 'red',
        ч: 'blue',
        с: 'yellow',
        м: 'green',
    }
    var colors = ['red', 'blue', 'yellow', 'green']

    var seconds = <?= $test_time ?>;

    function testStart() {
        test_start = true;
        interval = 400 + Math.random() * 3000
        setTimeout(light, interval);
        countdown();
    }

    function nextlight(){
        if(Date.now() - last_light_time >= 4000) {
            current_color = ""
            pass++;
            wrong_answers++;
            $('.color').hide()
            score = Date.now() - last_light_time;
            total_score += score;
            results.push(score);
            interval = 400 + Math.random() * 1000
            setTimeout(light, interval);
        }
    }

    function light() {
        if(!test_end && seconds > 1) {
            last_light_time = Date.now()
            num = Math.round((Math.random()*3))
            random_color = colors[num]
            console.log(num, random_color)
            $('.color').hide()
            $('.color.'+random_color).show()
            current_color = random_color;
            interval = 4000
            setTimeout(nextlight, interval);
        }
    }

    function reaction(key) {
        if(keys_colors[key] === current_color){
            score = Date.now() - last_light_time;
            total_score += score;
            right_answers++;
            results.push(score);
            interval = 400 + Math.random() * 3000
            setTimeout(light, interval);
            $('.color').hide()
            current_color = ""
        } else {
            wrong_answers++;
        }

    }

    function endTest(){
        const average = arr => arr.reduce((p, c) => p + c, 0) / arr.length;
        const result = Math.round(average(results));
        $("#result").val(result)
        $("#result_span").text(result)
        $("#wrong_answers_span").text(wrong_answers)
        $("#right_answers_span").text(right_answers)
        $("#pass_span").text(pass)
        $('.test').hide();
        $('.next_test').show();
        test_end = true;
    }

    $('body').keydown(function (e) {
        if(!test_end)
            if (e.key == 'z' || e.key == 'x' || e.key == 'c' || e.key == 'v' ||
                e.key == 'я' || e.key == 'ч' || e.key == 'с' || e.key == 'м') {
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


</script>

<style>
    .box {
        width: 700px;
        height: 350px;
        padding: 25px;
        border-radius: 10px;
        border: 3px solid #262626;
    }

    .box .color {
        width: 300px;
        height: 300px;
        position: absolute;
        margin-left: 175px;
        display: none;
        border-radius: 50%;
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
        background-color: #cb2e2e;
    }
    .blue {
        background-color: #332ecb;
    }
    .green {
        background-color: #68cb2e;
    }
    .yellow {
        background-color: #cbb12e;
    }



    .timer {
        height: 40px;
        font-size: 20px;
    }
    .next_test {
        display: none;
    }
</style>