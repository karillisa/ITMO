<h4>Проведите подсчёт чисел в каждом ряду и введите результаты в окна ввода, расположенные возле каждого ряда.
     Время на прохождение теста ограничено. Если справились раньше, чем пройдёт время, нажмите кнопку "Готово"</h4>
<button type="button"  id="startTest" class="btn btn-success mb-3 mt-5 ml-3">Нажмите, чтобы начать</button>

<form action="../scripts/save_test_results.php" method="post">

    <div class="test">
        <div class="timer">
            <span id="counter"></span>
        </div>

        <div class="box">
            <table id="test_table">

            </table>

        </div>
        <button id="endTest" type="button" class="btn btn-success mb-3 mt-5 ml-3">Готово</button>


        <input type="hidden" name="test_id" value="<?= $test_id ?>">
        <input type="hidden" name="complexity" value="<?= $complexity ?>">
        <input id="result" type="hidden" name="result" value="0">

    </div>
    <div class="next_test mt-5">
        <h2>Время запоминания: <span id="total_time"></span> с</h2>
        <h2>Результат: <span id="result_span"></span> верных</h2>
        <h2>Пропущено: <span id="pass_span"></span> строки</h2>
        <h2>Количество ошибок: <span id="wrong_answers_span"></span></h2>
        <button type="submit" class="btn btn-success mb-3 mt-5 ml-3">Следующий тест</button>
    </div>
</form>
<script>
    var seconds = <?= $test_time ?>;
    var start_seconds = <?= $test_time ?>;
    var complexity = <?= $complexity ?>;

    table = document.getElementById("test_table");
    startTestButton = document.getElementById("startTest");
    endTestButton = document.getElementById("endTest");
    questionElement = document.getElementById("question");
    var array = []
    var answers = []
    var testWords = []
    var size = complexity*3+2
    var question = 0
    var total_rings = 0, right_answers = 0, wrong_answers = 0, pass = 0
    var test_start = false
    var test_end = false


    function generateTable(){
        array = []
        for(var i = 0; i < size; i++){
            var tr = document.createElement("tr")
            var td = document.createElement("td")
            td.innerText = ""
            for(var j = 0; j < 15+complexity*2; j++){
                td.innerText+= Math.round(Math.random()*9)
            }
            td.classList.add("question_td")
            tr.appendChild(td)
            var td2 = document.createElement("td")
            var input = document.createElement("input")
            input.classList.add("answer_input")
            input.setAttribute("type", "number")
            td2.appendChild(input)
            tr.appendChild(td)
            tr.appendChild(td2)
            table.appendChild(tr)
        }
        console.log(array);
    }


    endTestButton.addEventListener("click", endTest)


    function endTest(){
        if(test_end)
            return

        $('.answer_input').each(function (index, el){

            var quest = $(".question_td").eq(index).text()
            var summ = 0
            for(var i = 0; i < quest.length; i++){
                summ += parseInt(quest[i])
            }
            console.log(summ, el.value)
            if(summ == el.value){
                right_answers++;
            } else if(el.value == ""){
                pass++
            } else {
                wrong_answers++
            }
        })

        $("#total_time").text(start_seconds-seconds)
        $("#result").val(right_answers)
        $("#result_span").text(right_answers)
        $("#wrong_answers_span").text(wrong_answers)
        $("#pass_span").text(pass)
        $('.test').hide();
        $('.next_test').show();
        test_end = true;
    }



    function testStart() {
        test_start = true;
        generateTable()
        $("#endTest").show()
        countdown();
    }
    startTestButton.addEventListener("click", function (){
        testStart()
        startTestButton.style.display = "none"
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

</script>

<style>
    .test {
        padding: 30px 0;
    }
    #test_table {

        border-collapse: collapse;
    }
    #test_table td {
        width: 80px;
        height: 28px;
        border: 1px solid #dadada;
        font-size: 30px;
        text-align: center;
        padding: 15px;
        color: #2a2a2a;
        line-height: 0.2;
        cursor: pointer;
    }
    #test_table td:hover{
        opacity: 1!important;
    }

    .timer {
        height: 40px;
        font-size: 20px;
    }

    .next_test {
        display: none;
    }

    #endTest {
        display: none;
    }
    .answer_input {
        width: 80px;
    }
</style>