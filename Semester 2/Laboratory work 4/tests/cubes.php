<h4>Необходимо сложить кубики таким образом,
    чтобы полученный рисунок (Ответ) в точности соответствовал узору образца</h4>
<h6 class="text-info">Сначала нужно выбрать место, а потом кубик, который Вы хотите поставить на это место</h6>
<h6 class="text-info">Важно! Сторона без рисунка тоже должна быть выбрана, нельзя оставить поле пустым</h6>
<button type="button"  id="startTest" class="btn btn-success mb-3 mt-5 ml-3">Нажмите, чтобы начать</button>

<form action="../scripts/save_test_results.php" method="post">

    <div class="test">
        <div class="timer">
            <span id="counter"></span>
        </div>

        <div class="box">
            <button id="endTest" type="button" class="btn btn-success mb-3 mt-5 ml-3">Готово</button>
            <div class="row">
                <div class="col-6"> <h4>Образец: </h4></div>
                <div class="col-6"> <h4>Ответ: </h4></div>
            </div>

            <table id="question_table">

            </table>
            <table id="answers_table">

            </table>

            <h4>Исходные кубики</h4>
            <div class="cubes">
                <div class="cube cube1" data-index="1"><div></div></div>
                <div class="cube cube2" data-index="2"><div></div></div>
                <div class="cube cube3" data-index="3"><div></div></div>
                <div class="cube cube4" data-index="4"><div></div></div>
                <div class="cube cube5" data-index="5"><div></div></div>
                <div class="cube cube6" data-index="6"><div></div></div>
            </div>
        </div>


        <input type="hidden" name="test_id" value="<?= $test_id ?>">
        <input type="hidden" name="complexity" value="<?= $complexity ?>">
        <input id="result" type="hidden" name="result" value="0">

    </div>
    <div class="next_test mt-5">
        <h2>Среднее время выполнения: <span id="total_time"></span> с</h2>
        <h2>Общее количество рисунков, которые нужно было повторить: <span id="total_words"></span></h2>
        <h2>Результат: <span id="result_span"></span> рисунков</h2>
        <h2>Количество ошибок: <span id="wrong_answers_span"></span></h2>
        <button type="submit" class="btn btn-success mb-3 mt-5 ml-3">Следующий тест</button>
    </div>
</form>
<script>
    var questions = [
        [
            [3, 3, 6, 6],
            [3, 6, 3, 6],
            [6, 6, 5, 6]
        ],
        [
            [4, 2, 6, 6],
            [6, 1, 2, 6],
            [4, 2, 1, 5],
            [2, 4, 5, 1]
        ],
        [
            [5, 6, 1, 3, 6, 3, 2, 6, 4],
            [2, 1, 3, 5, 4, 3, 3, 5, 4],
            [2, 1, 5, 1, 3, 2, 4, 2, 1]
        ],
    ]

    var complexity = <?= $complexity ?>;
    var seconds = <?= $test_time ?>*complexity;
    var start_seconds = <?= $test_time ?>*complexity;

    var table = document.getElementById("question_table");
    answers_table = document.getElementById("answers_table");
    startTestButton = document.getElementById("startTest");
    endTestButton = document.getElementById("endTest");
    questionElement = document.getElementById("question");
    cubes = document.getElementsByClassName("cubes")[0]
    var answers = []
    var size = Math.sqrt(questions[complexity-1][0].length)
    var question = 0
    var total_answers = [], right_answers = 0, wrong_answers = 0
    var right_answer_times = []
    var test_start = false
    var test_end = false
    var iter = 0;
    var selected_place = 0;


    function generateTable(){
        table.innerHTML = ''
        answers = []
        for(let i = 0; i < size; i++){
            var tr = document.createElement("tr")

            for(let j = 0; j < size; j++) {
                var td = document.createElement("td")
                answers.push(0)
                index = questions[complexity-1][iter][i * size + j]
                var cube = document.getElementsByClassName("cube"+index)[0].cloneNode(true)
                td.appendChild(cube)
                tr.appendChild(td)
            }
            table.appendChild(tr)
        }
        answers_table.innerHTML = ''
        for(let i = 0; i < size; i++) {
            var tr = document.createElement("tr")
            for (let j = 0; j < size; j++) {
                var td = document.createElement("td")
                td.dataset.place_number = i * size + j
                td.setAttribute("id", "answer_id_"+(i * size + j+1))
                td.addEventListener("click", selectPlace)
                tr.appendChild(td)
            }
            answers_table.appendChild(tr)
        }
    }


    endTestButton.addEventListener("click", endTest)


    for(var i = 0; i < cubes.children.length; i++){
        cubes.children[i].addEventListener("click", selectCube)
    }

    function selectCube(e){
        num = 0
        selected = null
        if(e.target.parentElement.dataset.index) {
            num = e.target.parentElement.dataset.index
            selected = e.target.parentElement
        }
        else {
            num = e.target.dataset.index
            selected = e.target
        }
        i = 1*selected_place+1
        target = document.getElementById("answer_id_"+i)
        console.log("answer_id_"+i)
        console.log(target);
        target_index = target.dataset.place_number
        console.log(selected);
        cloned = selected.cloneNode(true)
        cloned.onclick = null
        target.innerHTML = ''
        answers[target_index] = num
        console.log(answers);
        target.appendChild(selected.cloneNode(true))
    }

    function selectPlace(e){
        console.log(e.target);
        target = e.target
        if (!e.target.dataset.place_number)
            target = e.target.parentElement.parentElement
        for(var i = 0; i < answers_table.children.length; i++){
            for(var j = 0; j < answers_table.children[i].children.length; j ++){
                answers_table.children[i].children[j].classList.remove("selected")
            }
        }
        $(".answers_table td").removeClass('selected')
        target.classList.add("selected")
        selected_place = target.dataset.place_number
        console.log(selected_place)
    }

    function checkAnswer(){
        all_right = true
        console.log(questions[complexity - 1][iter]);
        console.log(answers);
        for(var i = 0; i < answers.length; i++){
            if(answers[i] != questions[complexity-1][iter][i]) {
                all_right = false
                break
            }
        }
        if(all_right){
            right_answers++
            total_answers.push(true)
            console.log("right")
        } else {
            wrong_answers++
            console.log("wrong")
        }
        right_answer_times.push(start_seconds-seconds)
        seconds = start_seconds
    }

    function endTest(){
        checkAnswer()
        iter++;
        if(iter < 3){
            generateTable()
            countdown()
            return;
        }

        if(test_end)
            return

        const getAverage = (numbers) => numbers.reduce((acc, number) => acc + number, 0) / numbers.length

        $("#total_time").text(Math.round(getAverage(right_answer_times)*100)/100)
        $("#result").val(right_answers)
        $("#total_words").text(iter)
        $("#result_span").text(right_answers)
        $("#wrong_answers_span").text(wrong_answers)
        $('.test').hide();
        $('.next_test').show();
        test_end = true;
    }


    function testStart() {
        test_start = true;
        $('.box').show()
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
                endTest()
            }
        }
        tick();
    }

</script>

<style>
    .timer {
        height: 40px;
        font-size: 20px;
    }

    .next_test {
        display: none;
    }

    .box {
        display: none;
    }
    #question_table {
        display: inline-block;
        vertical-align: top;
    }
    #answers_table{
        width: fit-content;
        min-height: 310px;
        display: inline-block;
        vertical-align: top;
        margin-left: 50px;
    }
    #answers_table td{
        width: 150px;
        height: 150px;
        border: 2px solid black;
        cursor: pointer;
    }

    #answers_table td.selected, #answers_table td:hover{
        border: 2px solid red;
        background: #e89898;
    }
    .cubes {
        width: 460px;
        height: 310px;
        display: flex;
        flex-wrap: wrap;
        justify-content: space-between;
    }
    .cube {
        width: 150px;
        height: 150px;
        border: 2px solid black;
        background-color: #eafdf5;
        cursor: pointer;
    }
    .cube div {
        width: 146px;
        height: 146px;
    }
    .cubes .cube:hover{
        filter: invert(100%);

    }
    .cube.cube1 div{

        border: 73px solid transparent;
        border-top: 73px solid #8d0b0b;
        border-left: 73px solid #8d0b0b;
    }
    .cube.cube2 div{

        border: 73px solid transparent;
        border-bottom: 73px solid #8d0b0b;
        border-right: 73px solid #8d0b0b;
    }
    .cube.cube4 div{

        border: 73px solid transparent;
        border-bottom: 73px solid #8d0b0b;
        border-left: 73px solid #8d0b0b;
    }
    .cube.cube5 div{

        border: 73px solid transparent;
        border-top: 73px solid #8d0b0b;
        border-right: 73px solid #8d0b0b;
    }
    .cube.cube6 div{
        background: #8d0b0b;
    }
</style>