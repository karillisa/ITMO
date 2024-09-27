<?php
?>
<a href="https://metodorf.ru/tests/korrekt/korrektlandolt.php">rings</a>
<?php
?>
<a href="https://cepia.ru/speedreading/schulte/gorbov">gorbov</a>
<h4>Выделите в каждом ряду кольца с конкретным направлением зазора. Если справитесь с заданием быстрее, чем пройдёт время, нажмите кнопку "Готово".</h4>
<button type="button"  id="startTest" class="btn btn-success mb-3 mt-5 ml-3">Нажмите, чтобы начать</button>

<form action="../scripts/save_test_results.php" method="post">

    <div class="test">
        <div class="timer">
            <span id="counter"></span>
        </div>

        <div class="box">
            <div class="question">Найдите: <span id="question"></span></div>
            <br>
            <table id="rings_table">

            </table>
        </div>
        <button id="endTest" type="button" class="btn btn-success mb-3 mt-5 ml-3">Готово</button>


        <input type="hidden" name="test_id" value="<?= $test_id ?>">
        <input type="hidden" name="complexity" value="<?= $complexity ?>">
        <input id="result" type="hidden" name="result" value="0">

    </div>
    <div class="next_test mt-5">
        <h2>Всего времени: <span id="total_time"></span> с</h2>
        <h2>Общее количество колец, которые нужно было отметить: <span id="total_rings"></span></h2>
        <h2>Результат: <span id="result_span"></span> колец</h2>
        <h2>Не отмечено: <span id="pass_span"></span> колец</h2>
        <h2>Количество ошибок: <span id="wrong_answers_span"></span></h2>
        <button type="submit" class="btn btn-success mb-3 mt-5 ml-3">Следующий тест</button>
    </div>
</form>
<script>
    var seconds = <?= $test_time ?>;
    var start_seconds = <?= $test_time ?>;
    var complexity = <?= $complexity ?>;

    table = document.getElementById("rings_table");
    startTestButton = document.getElementById("startTest");
    endTestButton = document.getElementById("endTest");
    questionElement = document.getElementById("question");
    var array = []
    var answers = []
    var size = complexity*10
    var countRingsInStroke = 15
    var question = 0
    var total_rings = 0, right_answers = 0, wrong_answers = 0, pass = 0
    var test_start = false
    var test_end = false
    generateTable()

    function generateTable(){
        array = []
        for(var i = 0; i < size; i++){
            var tr = document.createElement("tr")
            for(var j = 0; j < countRingsInStroke; j++) {
                var angle = Math.round(Math.random()*6)+1
                array[i*countRingsInStroke+j] = angle
                var td = document.createElement("td")
                var img = document.createElement("img")
                img.src = "../img/ring.png"
                img.style.transform = "rotate("+45*(angle-1)+"deg)"
                td.appendChild(img)
                td.dataset.angle = angle
                td.dataset.index = i*countRingsInStroke+j
                td.addEventListener("click",addAnswer)
                tr.appendChild(td)
            }
            table.appendChild(tr)
        }
        console.log(array);
    }

    endTestButton.addEventListener("click", endTest)

    function endTest(){
        if(test_end)
            return
        console.log(array);
        console.log(answers);
        console.log("question: "+question);
        for(var i = 0; i < array.length; i++){
            if(array[i] == question)
                total_rings++;
        }
        for(var i = 0; i < answers.length; i++){
            console.log(answers[i], array[answers[i]])
            if(array[answers[i]] == question){
                right_answers++;
            } else {
                wrong_answers++;
            }
        }
        $("#total_time").text(start_seconds-seconds)
        $("#result").val(right_answers)
        $("#result_span").text(right_answers)
        $("#total_rings").text(total_rings)
        $("#pass_span").text(total_rings-right_answers)
        $("#right_answers_span").text(right_answers)
        $("#wrong_answers_span").text(wrong_answers)
        $('.test').hide();
        $('.next_test').show();
        test_end = true;
    }

    function addAnswer(e){
        if(e.target.tagName == "IMG")
            elem = e.target.parentElement
        else
            elem = e.target
        elem.style.background = "#52a815"
        console.log("quest: "+question+"  index"+elem.dataset.index+"  angle"+elem.dataset.angle+" array: "+array[parseInt(elem.dataset.index)]);
        answers.push(elem.dataset.index)
    }


    function testStart() {
        test_start = true;
        setQuestion()
        countdown();
    }
    startTestButton.addEventListener("click", function (){
        testStart()
        startTestButton.style.display = "none"
    })
    function setQuestion(){
        rand_int = Math.round(Math.random()*6+1)
        var img = document.createElement("img")
        img.src = "../img/ring.png"
        img.style.transform = "rotate("+45*(rand_int-1)+"deg)"
        questionElement.innerHTML = ""
        questionElement.appendChild(img)
        question = rand_int
    }

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
        let currentIndex = array.length;
        while (currentIndex != 0) {
            let randomIndex = Math.floor(Math.random() * currentIndex);
            currentIndex--;
            [array[currentIndex], array[randomIndex]] = [
                array[randomIndex], array[currentIndex]];
        }
    }
</script>

<style>
    #rings_table {

        border-collapse: collapse;
    }
    #rings_table td {
        width: 30px;
        height: 28px;
        border: 1px solid #dadada;
        font-size: 38px;
        text-align: center;
        padding: 0;
        color: #2a2a2a;
        line-height: 0.2;
        cursor: pointer;
    }
    #rings_table td:hover{
        opacity: 1!important;
    }

    .timer {
        height: 40px;
        font-size: 20px;
    }

    .next_test {
        display: none;
    }
</style>