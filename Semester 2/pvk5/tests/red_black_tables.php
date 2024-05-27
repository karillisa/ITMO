<?php
?>
<a href="https://cepia.ru/speedreading/schulte/gorbov">gorbov</a>
<h4>Нажимайте на число в таблице с таким же цветом</h4>
<button id="startTest" class="btn btn-success mb-3 mt-5 ml-3">Нажмите, чтобы начать</button>

<form action="../scripts/save_test_results.php" method="post">

    <div class="test">
        <div class="timer">
            <span id="counter"></span>
        </div>

        <div class="box">
            <div class="question">Найдите: <span id="question"></span></div>
            <table id="red_black_table">

            </table>
        </div>


        <input type="hidden" name="test_id" value="<?= $test_id ?>">
        <input type="hidden" name="complexity" value="<?= $complexity ?>">
        <input id="result" type="hidden" name="result" value="0">

    </div>
    <div class="next_test mt-5">
        <h2>Средняя скорость реакции: <span id="result_span"></span> мс</h2>
        <h2>Количество успешных реакций: <span id="right_answers_span"></span></h2>
        <h2>Количество ошибок: <span id="wrong_answers_span"></span></h2>
        <button type="submit" class="btn btn-success mb-3 mt-5 ml-3">Следующий тест</button>
    </div>
</form>
<script>
    table = document.getElementById("red_black_table");
    startTestButton = document.getElementById("startTest");
    questionElement = document.getElementById("question");
    var questionNumber = 0;
    var questionColor = 0;
    var seconds = <?= $test_time ?>;
    var start_seconds = <?= $test_time ?>;
    var complexity = <?= $complexity ?>;
    var array = [];
    var mask = [];
    var size = complexity+3
    var results = []
    var total_score = 0, right_answers = 0, wrong_answers = 0;

    generateTable()

    function generateTable(){
        mask = new Array(size*size).fill(0);
        for(var i = 1; i <= Math.ceil(size*size/2); i++){
            array.push([i, 1])
            array.push([i, 0])
        }
        shuffle(array)
        shuffle(mask)
        console.log(mask);
        for(var i = 0; i < size; i++){
            var tr = document.createElement("tr")
            for(var j = 0; j < size; j++){
                var td = document.createElement("td")
                td.innerHTML  = array[i*size+j][0]
                if(array[i*size+j][1]){
                    td.style.background = "#ec1616"
                    td.dataset.color = 1
                } else {
                    td.style.background = "#232323"
                    td.dataset.color = 0
                }
                td.style.opacity = 0.85
                td.addEventListener("click",checkAnswer)
                tr.appendChild(td)
            }
            table.appendChild(tr)
        }
    }

    function testStart() {
        test_start = true;
        setQuestion()
        countdown();
    }
    function endTest(){
        const average = arr => arr.reduce((p, c) => p + c, 0) / arr.length;
        const result = Math.round(average(results));
        $("#result").val(right_answers)
        $("#result_span").text(Math.round(start_seconds*100000/right_answers)/100)
        $("#wrong_answers_span").text(wrong_answers)
        $("#right_answers_span").text(right_answers)
        $('.test').hide();
        $('.next_test').show();
        test_end = true;
    }


    function checkAnswer(e){
        if(e.target.innerText == questionNumber &&
            e.target.dataset.color == questionColor){
            right_answers++
            setQuestion()
            console.log("right")
        } else {
            wrong_answers ++
        }
    }


    function setQuestion(){
        rand_int = Math.round(Math.random()*size*size)
        questionNumber = array[rand_int][0]
        questionColor = array[rand_int][1]
        questionElement.innerText = questionNumber
        questionElement.style.color = questionColor?"#ec1616":"#232323"
    }

    startTestButton.addEventListener("click", function (){
        testStart()
    })


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
    #red_black_table {
        width: 350px;
        height: 350px;
        border-collapse: collapse;
    }
    #red_black_table td {
        width: 70px;
        height: 70px;
        border: 1px solid black;
        font-size: 30px;
        padding: 5px;
        text-align: center;
        color: #e1e1e1;
        cursor: pointer;
    }
    #red_black_table td:hover{
        opacity: 1!important;
    }
    #question {
        font-size: 30px;
        font-weight: bold;
    }

    .timer {
        height: 40px;
        font-size: 20px;
    }

    .next_test {
        display: none;
    }
</style>