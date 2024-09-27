<h4>Нажимайте "влево" или "вправо", чтобы шарик как можно дольше находился в центре (время теста 1 минута)</h4>
<h4>Важна скорость реакции, а также общее время удержания шарика в центре</h4>
<h6 class="text-info">Нажмите любую из предложенных клавиш, чтобы начать</h6>

<form action="../scripts/save_test_results.php" method="post">

    <div class="test">
        <div class="timer">
            <span id="counter"></span>
        </div>

        <div class="box">
            <div id="target">
            </div>
            <div class="middle"></div>
        </div>

        <div class="keys">
            <div class="key z я">Z <span>&#8592;</span></div>
            <div class="key x ч">X <span>&#8594;</span></div>
        </div>

        <input type="hidden" name="test_id" value="<?= $test_id ?>">
        <input id="result" type="hidden" name="result" value="0">

    </div>
    <div class="next_test mt-5">
        <h2>Отклонение от мгновенной реакции: <span id="result_span"></span> мс</h2>
        <h2>Общее время удержания цели в центре: <span id="total_time_center_span"></span> мс</h2>
        <h2>Количество пропусков: <span id="pass_span"></span></h2>
        <button type="submit" class="btn btn-success mb-3 mt-5 ml-3">Следующий тест</button>
    </div>
</form>
<script>
    var test_start = false;
    var test_end = false;
    var total_score = 0;
    var position_count = 0;
    var results = [];
    var pass_count = 0;
    var center_coord_x = 373
    var left_coord_x = 23
    var right_coord_x = 727
    var curr_x = 373
    var target = document.getElementById("target")
    var curr_pressed_left = false
    var last_time_pressed = 0
    var last_time_change_position = 0
    var curr_pressed_rigth = false
    var stepLeft = 5
    var stepRight = 5
    var time_go_inside_center = 0;

    function animation() {

    }

    var seconds = <?= $test_time ?>;


    function randomPosition(){
        position_count++;
        var rand_x = Math.random()*(right_coord_x-left_coord_x)+left_coord_x;
        curr_x = rand_x;
        updateTarget()
        var next_time = Math.random()*3000+1000
        last_time_change_position = Date.now()
        setTimeout(randomPosition, next_time)
    }

    function checkCenter(){
        if(curr_x >= center_coord_x-50 && curr_x <= center_coord_x){
            if(!time_go_inside_center) {
                time_go_inside_center = Date.now()
            }
        } else {
            if(time_go_inside_center){
                total_score += Date.now() - time_go_inside_center
                time_go_inside_center = 0

            }
        }
        setTimeout(checkCenter, 25)
    }

    function testStart() {
        test_start = true
        randomPosition()
        checkCenter()
        countdown()
    }

    function updateTarget() {
        target.style.marginLeft = curr_x + "px"
    }


    function leftMove() {
        if (curr_pressed_left)
            stepLeft *= 1.04
        if(!(curr_x - stepLeft < left_coord_x))
            curr_x -= stepLeft
        updateTarget()
    }

    function rightMove() {
        if (curr_pressed_rigth)
            stepRight *= 1.04
        if(!(curr_x + stepRight > right_coord_x))
            curr_x += stepRight
        updateTarget()
    }

    function resetPressed() {
        curr_pressed_left = false;
        curr_pressed_rigth = false;
        last_time_pressed = 0
        stepLeft = 5
        stepRight = 5
    }

    function reaction(key) {
        if (key == 'z' || key == 'я') {
            if (!curr_pressed_left) {
                curr_pressed_left = true
                if(last_time_change_position !== 0 && curr_x > center_coord_x){
                    results.push(Date.now()-last_time_change_position)
                    last_time_change_position = 0;
                }
            }
            last_time_pressed = Date.now()
            leftMove()
        } else if (key == 'x' || key == 'ч') {
            if (!curr_pressed_rigth) {
                curr_pressed_rigth = true
                if(last_time_change_position !== 0 && curr_x < center_coord_x){
                    results.push(Date.now()-last_time_change_position)
                    last_time_change_position = 0;
                }
            }
            last_time_pressed = Date.now()
            rightMove()
        }
    }

    function endTest() {
        var pass_count = position_count - results.length;
        const average = arr => arr.reduce((p, c) => p + c, 0) / arr.length;
        const result = Math.round(average(results));
        $("#result").val(result)
        $("#result_span").text(result)
        $("#pass_span").text(pass_count)
        $("#total_time_center_span").text(total_score)
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
        resetPressed();
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
        height: 150px;
        border-radius: 10px;
        border: 3px solid black;
    }

    .box #target {
        width: 50px;
        height: 50px;
        background-color: #ffd82c;
        border-radius: 50%;
        margin-top: 50px;
        margin-left: 373px;
        display: block;
        border: 1px solid black;

        transition: margin-left 0.2s ease;

    }

    .box .middle {
        width: 2px;
        height: 100%;
        margin-top: -100px;
        margin-left: calc(50% - 1px);
        border: 1.5px solid red;
        position: relative;
    }

    .keys {
        margin-top: 25px;
        margin-left: 280px;
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

    .keys .key span {
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