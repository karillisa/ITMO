<h4>Постарайтесь запомнить как можно больше изображений, которые вы видите ниже.
    Через <?= $test_time ?> откроется страница выбора изображений по памяти, если считаете,
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
        <h2>Общее количество изображений, которые нужно было запомнить: <span id="total_words"></span></h2>
        <h2>Результат: <span id="result_span"></span> изображений</h2>
        <h2>Пропущено: <span id="pass_span"></span> изображений</h2>
        <h2>Количество ошибок: <span id="wrong_answers_span"></span></h2>
        <button type="submit" class="btn btn-success mb-3 mt-5 ml-3">Следующий тест</button>
    </div>
</form>
<script>
    const images = [ "000551.JPEG", "003882.JPEG", "005449.JPEG", "009655.JPEG", "014772.JPEG", "016837.JPEG", "019474.JPEG", "024696.JPEG", "027618.JPEG", "030063.JPEG", "032635.JPEG", "035181.JPEG", "038657.JPEG", "042392.JPEG", "045149.JPEG",
            "000971.JPEG", "004000.JPEG", "006255.JPEG", "010079.JPEG", "015118.JPEG", "017402.JPEG", "019920.JPEG", "025118.JPEG", "027749.JPEG", "030324.JPEG", "032650.JPEG", "035329.JPEG", "038871.JPEG", "043087.JPEG", "047413.JPEG",
            "001034.JPEG", "004328.JPEG", "006358.JPEG", "010387.JPEG", "015477.JPEG", "017627.JPEG", "020424.JPEG", "026625.JPEG", "028267.JPEG", "031109.JPEG", "032928.JPEG", "035509.JPEG", "039255.JPEG", "043314.JPEG", "048677.JPEG",
            "001551.JPEG", "004472.JPEG", "006622.JPEG", "010392.JPEG", "015509.JPEG", "017818.JPEG", "021098.JPEG", "026654.JPEG", "028612.JPEG", "031440.JPEG", "033540.JPEG", "035513.JPEG", "039615.JPEG", "043696.JPEG", "049095.JPEG",
            "001572.JPEG", "004979.JPEG", "007249.JPEG", "011327.JPEG", "015991.JPEG", "018560.JPEG", "022188.JPEG", "026720.JPEG", "029110.JPEG", "031495.JPEG", "034028.JPEG", "037040.JPEG", "041160.JPEG", "043992.JPEG", "049454.JPEG",
            "002445.JPEG", "005140.JPEG", "008851.JPEG", "012719.JPEG", "016256.JPEG", "018708.JPEG", "023028.JPEG", "027520.JPEG", "029134.JPEG", "031968.JPEG", "034371.JPEG", "037476.JPEG", "041327.JPEG", "044880.JPEG", "049964.JPEG",
            "003084.JPEG", "005196.JPEG", "009521.JPEG", "013770.JPEG", "016786.JPEG", "019111.JPEG", "024644.JPEG", "027594.JPEG", "029358.JPEG", "032634.JPEG", "034446.JPEG", "038351.JPEG", "041584.JPEG", "044995.JPEG" ];
    var seconds = <?= $test_time ?>;
    var start_seconds = <?= $test_time ?>;
    var complexity = <?= $complexity ?>;
    var images_limit = 10+complexity*30

    table = document.getElementById("words_table");
    answers_table = document.getElementById("answers_table");
    startTestButton = document.getElementById("startTest");
    readyButton = document.getElementById("readyButton");
    endTestButton = document.getElementById("endTest");
    questionElement = document.getElementById("question");
    var array = []
    var answers = []
    var testImages = []
    var size = complexity+2
    var question = 0
    var total_rings = 0, right_answers = 0, wrong_answers = 0, pass = 0
    var test_start = false
    var test_end = false

    function getTestWords(){
        var words_array_size = images_limit
        var images2 = images.slice()
        for (var i = 0; i < size*size; i++){
            index = Math.round(Math.random()*(words_array_size--))
            testImages.push(images2[index])
            images2.splice(index, 1)
        }

    }

    function generateTable(){
        getTestWords()
        array = []
        for(let i = 0; i < size; i++){
            var tr = document.createElement("tr")
            for(let j = 0; j < size; j++) {
                var td = document.createElement("td")
                var img = document.createElement("img")
                img.src = "../img/images_for_test/"+testImages[i*size+j];
                img.style.width = "120px"
                td.appendChild(img)
                tr.appendChild(td)
            }
            table.appendChild(tr)
        }

        for(let i = 0; i < images_limit/10; i++){
            var tr = document.createElement("tr")
            for(let j = 0; j < 10; j++){
                var td = document.createElement("td")
                var img = document.createElement("img")
                img.src = "../img/images_for_test/"+images[i*10+j];
                img.style.width = "120px"
                var input = document.createElement("input")
                input.setAttribute("type", "checkbox")
                input.dataset.filename = images[i*10+j]
                input.classList.add("image_checkbox")
                td.appendChild(img)
                td.appendChild(input)
                tr.appendChild(td)
            }
            answers_table.appendChild(tr)

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
        $('.image_checkbox').each(function(index, el){
            filename = el.dataset.filename
            if(el.checked){
                if(testImages.find((element) => element == filename)) {
                    console.log(filename)
                    right_answers++
                }
                else {
                    wrong_answers++
                }
            } else if(testImages.find((element) => element == filename)){
                pass++
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

</script>

<style>
    #words_table {

        border-collapse: collapse;
    }
    #words_table td {
        width: 150px;
        height: 28px;
        border: 1px solid #dadada;
        font-size: 30px;
        text-align: center;
        padding: 5px;
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
    .image_checkbox {
        position: absolute;
        margin-left: -30px;
        margin-top: 5px;
        width: 25px;
        height: 25px;
    }
</style>