<h4>Постарайтесь запомнить как можно больше из списка слов, которые вы видите ниже.
    Через <?= $test_time ?> откроется страница ввода слов, если считаете,
    что времени прошло достаточно, нажмите кнопку "Готово"</h4>
<button type="button"  id="startTest" class="btn btn-success mb-3 mt-5 ml-3">Нажмите, чтобы начать</button>

<form action="../scripts/save_test_results.php" method="post">

    <div class="test">
        <div class="timer">
            <span id="counter"></span>
        </div>

        <div class="box">
            <table id="words_table">

            </table>
            <table id="answers_table">

            </table>
        </div>
        <button id="readyButton" type="button" class="btn btn-success mb-3 mt-5 ml-3">Запомнил(а)</button>
        <button id="endTest" type="button" class="btn btn-success mb-3 mt-5 ml-3">Готово</button>


        <input type="hidden" name="test_id" value="<?= $test_id ?>">
        <input type="hidden" name="complexity" value="<?= $complexity ?>">
        <input id="result" type="hidden" name="result" value="0">

    </div>
    <div class="next_test mt-5">
        <h2>Время запоминания: <span id="total_time"></span> с</h2>
        <h2>Общее количество слов, которые нужно было запомнить: <span id="total_words"></span></h2>
        <h2>Результат: <span id="result_span"></span> слов</h2>
        <h2>Пропущено: <span id="pass_span"></span> слов</h2>
        <h2>Количество ошибок: <span id="wrong_answers_span"></span></h2>
        <button type="submit" class="btn btn-success mb-3 mt-5 ml-3">Следующий тест</button>
    </div>
</form>
<script>
    words = [
        "стол", "книга", "дом", "компьютер", "телефон", "машина", "дерево", "цветок", "облако", "солнце", "луна", "звезда", "река", "озеро", "море", "океан", "рыба", "птица", "зверь", "человек", "город", "страна", "мир", "вселенная", "жизнь", "смерть", "любовь", "ненависть", "счастье", "горе", "радость", "печаль", "вера", "надежда", "мечта", "цель", "мысль", "чувство", "желание", "страх", "сомнение", "правда", "ложь", "добро", "зло", "красота", "уродство", "богатство", "бедность", "сила", "слабость", "ум", "глупость", "мудрость", "безумие", "смелость", "трусость", "щедрость", "жадность", "честность", "обман", "верность", "измена", "дружба", "вражда", "память", "забытье", "прошлое", "настоящее", "будущее", "вечность", "бесконечность", "судьба", "случай", "загадка", "тайна", "мечта", "фантазия", "иллюзия", "реальность", "событие", "случай", "причина", "следствие", "результат",
    ];
    var seconds = <?= $test_time ?>;
    var start_seconds = <?= $test_time ?>;
    var complexity = <?= $complexity ?>;

    table = document.getElementById("words_table");
    answers_table = document.getElementById("answers_table");
    startTestButton = document.getElementById("startTest");
    readyButton = document.getElementById("readyButton");
    endTestButton = document.getElementById("endTest");
    questionElement = document.getElementById("question");
    var array = []
    var answers = []
    var testWords = []
    var size = complexity+2
    var question = 0
    var total_rings = 0, right_answers = 0, wrong_answers = 0, pass = 0
    var test_start = false
    var test_end = false

    function getTestWords(){
        words_array_size = 84
        console.log(words.length)
        for (var i = 0; i < size*size; i++){
            index = Math.round(Math.random()*(words_array_size--))
            testWords.push(words[index])
            words.splice(index, 1)
        }
    }

    function generateTable(){
        getTestWords()
        console.log(testWords)
        array = []
        for(var i = 0; i < size; i++){
            var tr = document.createElement("tr")
            var tr2 = document.createElement("tr")
            for(var j = 0; j < size; j++) {
                var td = document.createElement("td")
                td.innerText = testWords[i*size+j]
                tr.appendChild(td)
                var td2 = document.createElement("td")
                var input = document.createElement("input")
                input.classList.add("answer_input")
                td2.appendChild(input)
                tr2.appendChild(td2)
           }
            table.appendChild(tr)
            answers_table.appendChild(tr2)
        }
        console.log(array);
    }

    readyButton.addEventListener("click", showInputs)

    endTestButton.addEventListener("click", endTest)

    function showInputs(){
        $(table).hide()
        $(answers_table).show()
        $("#endTest").show()
        $("#readyButton").hide()
        $('.timer').hide()
    }

    function endTest(){
        if(test_end)
            return

        $('.answer_input').each(function (index, el){

            if(testWords.find((element) => element == el.value)) {
                console.log(el.value)
                right_answers++;
            }
            else if(el.value == ""){
                pass++
            } else {
                wrong_answers++
            }
        })



        $("#total_time").text(start_seconds-seconds)
        $("#result").val(right_answers)
        $("#total_words").text(size*size)
        $("#result_span").text(right_answers)
        $("#wrong_answers_span").text(wrong_answers)
        $("#pass_span").text(pass)
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
        generateTable()
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
                showInputs()
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
    #words_table {

        border-collapse: collapse;
    }
    #words_table td {
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
    #words_table td:hover{
        opacity: 1!important;
    }

    .timer {
        height: 40px;
        font-size: 20px;
    }

    .next_test {
        display: none;
    }

    #answers_table {
        display: none;
    }
    #endTest {
        display: none;
    }
</style>