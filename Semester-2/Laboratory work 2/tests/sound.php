<h4>Нажимайте ПРОБЕЛ сразу, когда звучит сигнал (время теста 1 минута)</h4>
<h4>(Включите звук на компьютере и в браузере)</h4>
<h6>Нажмите пробел, чтобы начать</h6>

<form action="../scripts/save_test_results.php" method="post">

    <div class="test">
        <div class="timer">
            <span id="counter"></span>
        </div>

        <div class="box">
            <img src="../img/dynamic.png" alt="Динамик">
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
        <h2>Средняя скорость реакции: <span id="result_span"></span> мс</h2>
        <button type="submit" class="btn btn-success mb-3 mt-5 ml-3">Следующий тест</button>
    </div>
</form>
<script>
    var test_start = false;
    var test_end = false;
    var total_score = 0;
    var results = [];
    var last_light_time = 0;
    var signal = null;

    function testStart() {
        test_start = true;
        interval = 300 + Math.random() * 5000
        setTimeout(light, interval);
        countdown();
    }

    function light() {
        if(!test_end) {
            last_light_time = Date.now()
            signal = beep()
        }
    }

    function reaction() {
        signal.stop()
        score = Date.now() - last_light_time;
        total_score += score;
        results.push(score);
        interval = 300 + Math.random() * 5000
        setTimeout(light, interval);
        $("#light_box").hide();
    }

    function endTest(){
        const average = arr => arr.reduce((p, c) => p + c, 0) / arr.length;
        const result = Math.round(average(results));
        $("#result").val(result)
        $("#result_span").text(result)
        $('.test').hide();
        $('.next_test').show();
        signal.stop()
        test_end = true;
    }

    $('body').keydown(function (e) {
        if(!test_end)
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
        var seconds = <?= $test_time ?>;

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



    const audioCtx = new (window.AudioContext || window.webkitAudioContext || window.audioContext);

    /**
     * @param {number} duration Длительность воспроизведения звука
     * @param {number} frequency Частота звука
     * @param {number} volume Громкость воспроизведения звука
     * @param {string} type Тип осцилятора
     * @param {function} callback Функция асинхронного возврата
     */
    function beep(duration, frequency, volume, type, callback) {
        frequency = 200
        var oscillator = audioCtx.createOscillator();
        var gainNode = audioCtx.createGain();

        oscillator.connect(gainNode);
        gainNode.connect(audioCtx.destination);

        if (volume){gainNode.gain.value = volume;}
        if (frequency){oscillator.frequency.value = frequency;}
        if (type){oscillator.type = type;}
        if (callback){oscillator.onended = callback;}

        oscillator.start(audioCtx.currentTime);
        return oscillator;
        //oscillator.stop(audioCtx.currentTime + ((duration || 500) / 1000));
    };

</script>

<style>
    .box {
        width: 700px;
        height: 300px;
        padding: 50px;
        border-radius: 10px;
        border: 3px solid #262626;
    }

    .box img {
        width: 200px;
        margin: 0 auto;
        display: block;
    }


    .space {
        margin-top: 25px;
        margin-left: 200px;
        width: 300px;
        height: 70px;
        border: 4px solid #262626;
        border-radius: 15px;
        transition: background-color 0.2s ease;
        margin-bottom: 20px;
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