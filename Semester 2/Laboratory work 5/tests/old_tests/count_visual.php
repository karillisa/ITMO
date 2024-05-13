<h4>Нажимайте z (четное) или x(нечетное), на экране появляется выражение (время теста 1 минута)</h4>
<h6 class="text-info">Нажмите z или x, чтобы начать</h6>

<form action="../scripts/save_test_results.php" method="post">

    <div class="test">
        <div class="timer">
            <span id="counter"></span>
        </div>

        <div class="box">
            <div class="exp">
                <div class="operand first">3</div>
                <div class="operand operator">+</div>
                <div class="operand second">4</div>
            </div>
        </div>

        <div class="keys">
            <div class="key z я">Z <span>(четное)</span></div>
            <div class="key x ч">X <span>(нечетное)</span></div>
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
    var last_answer_was_wrong = 0;
    var current_summ = null;
    var keys_meaning = {
        z: 0,
        x: 1,
        я: 0,
        ч: 1,
    }

    var seconds = <?= $test_time ?>;

    function testStart() {
        test_start = true;
        interval = 400 + Math.random() * 3000
        setTimeout(light, interval);
        countdown();
    }

    function nextlight() {
        if (Date.now() - last_light_time >= 4000 && current_summ) {
            current_summ = ""
            if(!last_answer_was_wrong){
                pass++;
                wrong_answers++;
            }
            $('.operand').hide()
            score = Date.now() - last_light_time
            total_score += score;
            results.push(score)
            interval = 400 + Math.random() * 1000
            setTimeout(light, interval)
        }
    }

    function light() {
        if (!test_end && seconds > 2) {
            last_light_time = Date.now()
            num = Math.round((Math.random() * 10))
            $('.operand.first').text(num)
            num2 = Math.round((Math.random() * 10))
            $('.operand.second').text(num2)
            $('.operand.operator').text("+")
            $('.operand').show()
            current_summ = num + num2
            console.log(num, num2, current_summ)
            interval = 4000
            setTimeout(nextlight, interval)
            last_answer_was_wrong = false
        }
    }

    function reaction(key) {
        if (keys_meaning[key] === current_summ % 2) {
            current_summ = null
            console.log("rigth")
            score = Date.now() - last_light_time
            total_score += score
            right_answers++
            results.push(score)
            interval = 400 + Math.random() * 3000
            setTimeout(light, interval)
            $('.operand').hide()
            last_answer_was_wrong = false
        } else {
            console.log("wrong")
            wrong_answers++;
            last_answer_was_wrong = true
        }
    }

    function endTest() {
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
        if (!test_end)
            if (e.key == 'z' || e.key == 'x' ||
                e.key == 'я' || e.key == 'ч') {
                e.preventDefault();
                if (test_start)
                    reaction(e.key);
                else
                    testStart();
                $(".keys .key." + e.key).css("background", "#3db43f")
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
    .box .exp {
        max-width: 80%;
        margin: 0 auto;
    }

    .box .operand {
        width: 150px;
        height: 150px;
        display: inline-block;
        margin-top: 40px;
        margin-left: 10px;
        font-size: 120px;
        color: #252525;
    }


    .keys {
        margin-top: 25px;
        margin-left: 150px;
        width: 400px;
        height: 70px;
        margin-bottom: 80px;
    }

    .keys .key {
        width: 90px;
        height: 90px;
        border: 4px solid #262626;
        border-radius: 15px;
        transition: background-color 0.2s ease;
        display: inline-block;
        margin: 10px;
        color: #252525;
        font-size: 30px;
        padding: 10px;
        text-align: center;
    }
    .keys .key span{
        margin-top: 50px;
        margin-left: -55px;
        width: 90px;
        text-align: center;
        position: absolute;
        font-size: 12px;
    }


    .timer {
        height: 40px;
        font-size: 20px;
    }

    .next_test {
        display: none;
    }
</style>