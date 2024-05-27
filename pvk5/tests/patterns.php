<h4>Слева перед каждой строчкой изображены значки : +?* и т п. Этими условными знаками обозначены или зашифрованы одно
    или несколько слов из тех, что имеются в данной строчке. Ваша задача — отыскать и подчеркнуть эти слова.
    Чтобы это сделать, вы должны уловить закономерность расположения знаков.
    Так, если все значки разные, значит, и слово, которое надо найти, состоит из различных, неповторяющихся букв.
    Например, +*:= пуля няня тара дядя (правильный ответ — пуля). Если справились раньше, чем пройдёт время, нажмите кнопку "Готово"</h4>
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
        <h2>Время выполнения: <span id="total_time"></span> с</h2>
        <h2>Результат: <span id="result_span"></span> верных</h2>
        <h2>Пропущено: <span id="pass_span"></span></h2>
        <h2>Количество ошибок: <span id="wrong_answers_span"></span></h2>
        <button type="submit" class="btn btn-success mb-3 mt-5 ml-3">Следующий тест</button>
    </div>
</form>
<script>
    questions = [
        ["РАНА","КРОТ","ВОСК","ТОРТ","ЖАРА","КИЛЬ","СОУС","ОВОД","ВЕРА","СОЛО","КОНЬ","РОСТ","СЕНО","КОРТ","ЛАВА","ОБОИ","ПИЛА","ГОРЕ","ИГЛА","МОРЕ","ЛАПА","ОКНО","КОКС","МРАК","ВРАГ","ОБОД","ЛОСЬ","ЧАСЫ","СОУС","МОСТ","ГРАД","ЛИПА","БОРТ","КАША","ЖЕЛЕ","РОЖЬ","ЛУНА","РОЗА","СОРТ","ВРАГ"],
        ["АВТОР","РТУТЬ","ВАХТА","РОБОТ","НОРКА","ССОРА","КОРКА","МЕССА","СЛОВО","СПОРТ","ОБРОК","ОСОБА","КАССА","ЛАССО","МАССА","СУММА","ГАЙКА","СЛОВО","ПЕПЕЛ","ОЛОВО","ЗОЛОТО","МНЕНИЕ","БЕРЕЗА","КОРОВА","КРАСКА","ЗАНУДА","ПОЛЕНО","ШАХТЕР","ЗАНОЗА","ТЮРБАН","ЦВЕТОК","КОЛЕСО","СТАКАН","ТАЛАНТ","МОЛОКО","АНАНАС","ПОЧЕРК","СПИСОК","КАПКАН","СУНДУК"],
        ["НОВИЧЕК","ВИРТУОЗ","РАЗЪЕЗД","ПОЛНОЧЬ","КАЛИТКА","НАДЕЖДА","ТЕЛЕФОН","ПРЕСТИЖ","СКАНДАЛ","ЗАЩЕЛКА","ВПАДИНА","ХОРОВОД","ЛАУРЕАТ","СИНОНИМ","МАЛАХИТ","НАКИДКА","СЕЛЕНИЕ","РЕФЕРАТ","СИНОНИМ","РЕНТГЕН","КУЛЬТУРА","ТРАМПЛИН","РАСТЕНИЕ","ПАССАЖИР","КОМИССИЯ","БИОЛОГИЯ","ПРОПАСТЬ","МОЛЕКУЛА","РАЗВЕДКА","ПЕРЕМЕНА","ПРЕГРАДА","АРГУМЕНТ","ПРОКУРОР","МЕХАНИЗМ","АНТИЛОПА","ХИТРОСТЬ","ОБОЛОЧКА","ВОЛНЕНИЕ","ПРАКТИКА","ВНИМАНИЕ"],
    ]
    questions_patterns = []
    pattern_set = ["!", "-", "+", "=", "?", "*", ":", "Х"]
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
    var question = 0
    var total_rings = 0, right_answers = 0, wrong_answers = 0, pass = 0
    var test_start = false
    var test_end = false



    function generateTable(){
        array = []
        for(var i = 0; i < 10; i++){
            answers[i] = []
            var tr = document.createElement("tr")
            var td = document.createElement("td")
            td.innerText = ""
            rand_index = Math.round(Math.random()*3)
            question_word = ""
            for(var j = 0; j < 4; j++){
                word = questions[complexity-1][i*4+j]
                if(j == rand_index)
                    question_word = word
                var span = document.createElement("span")
                span.innerHTML = word
                span.classList.add("word")
                span.classList.add("word"+i)
                span.dataset.row_number = i
                span.addEventListener("click", selectWord)
                td.appendChild(span)
            }
            var span = document.createElement("span")
            span.classList.add("pattern")
            encrypted = generatePattern(question_word)
            questions_patterns.push(encrypted)
            span.innerHTML = encrypted
            td.insertBefore(span, td.firstChild)
            td.classList.add("question_td")
            tr.appendChild(td)
            tr.appendChild(td)
            table.appendChild(tr)
        }
        console.log(array);
    }

    function selectWord(e){
        index = e.target.dataset.row_number
        word = e.target.innerText
        if(e.target.classList.contains("selected")){
            e.target.classList.remove("selected")
            const i = answers[index].indexOf(word)
            if (i > -1) {
                answers[index].splice(i, 1)
            }
        } else {
            e.target.classList.add("selected")
            answers[index].push(word)
        }
        console.log(answers);
    }

    endTestButton.addEventListener("click", endTest)


    function endTest(){
        if(test_end)
            return
        var words = document.getElementsByClassName("word")
        var total_count = 0
        for(var i = 0; i < words.length; i++){
            word = words[i].innerText
            row_number = words[i].dataset.row_number
            if(checkPattern(questions_patterns[row_number], word)){
                console.log(word, questions_patterns[row_number], "true")
                total_count++
                if(answers[row_number].indexOf(word) > -1){
                    right_answers++
                } else {
                    pass++
                }

            } else {
                console.log(word, questions_patterns[row_number], "false")
                if(answers[row_number].indexOf(word) > -1){
                    wrong_answers++
                }
            }
        }

        $("#total_time").text(start_seconds-seconds)
        $("#result").val(Math.round(right_answers/total_count*10000)/100)
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

    function generatePattern(word){
        shuffle(pattern_set)
        let result = []
        var pattern_count = 0
        for(var i = 0; i < word.length; i++){
            for(var j = i; j < word.length; j++){
                if (word[i] == word[j] && !result[j])
                    result[j] = pattern_set[pattern_count]
            }
            pattern_count++
        }
        console.log(word);
        console.log(result);
        return result.join("")
    }

    function checkPattern(pattern, word){
        match = true
        for(var i = 0; i < word.length; i++){
            for(var j = i; j < word.length; j++){
                if(
                    word[i] == word[j] &&
                    pattern[i] != pattern[j] ||
                    word[i] != word[j] &&
                    pattern[i] == pattern[j]
                ) {
                    match = false
                    break
                }
            }
        }
        return match
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
        width: 600px;
        height: 50px;
        border: 1px solid #dadada;
        font-size: 20px;
        padding: 10px;
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
    .word {
        margin: 0 4px;
        padding: 2px;
        border-radius: 3px;
    }
    .word.selected{
        background: #1cc88a;
    }
    .pattern{
        margin-right: 10px;
        font-weight: bold;
        letter-spacing: 10px;
        border: 1px solid blue;
        border-radius: 3px;
        padding: 3px;
    }

</style>