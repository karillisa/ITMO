let yy = null;

class Point {
    constructor(x, y, r) {
        this.x = x;
        this.y = y;
        this.r = r;
    }
}

let points;
points = [];


document.addEventListener('DOMContentLoaded', function () {
    const form = document.getElementById('pointForm');
    const resultTd = document.getElementById('result');
    const timeTd = document.getElementById('time');
    const executionTimeTd = document.getElementById('executionTime');
    const historyTable = document.getElementById('historyTable');
    const point = document.getElementById('point');
    const submitBtn = document.getElementById('submit');
    let r = 1;
    let y = null;

    function getY(){
        return y;
    }

    function showNotification(message) {
        const container = document.getElementById('notification-container');
        const notification = document.createElement('div');
        notification.className = 'notification';
        notification.textContent = message;

        // Добавляем уведомление в контейнер
        container.appendChild(notification);

        // Показать уведомление
        setTimeout(() => {
            notification.classList.add('show');
        }, 10);

        // Удалить уведомление через 3 секунды
        setTimeout(() => {
            notification.classList.remove('show');
            setTimeout(() => {
                notification.remove();
            }, 300); // Учитываем время завершения анимации
        }, 3000);
    }


    function getXfromCheck() {
        const checkboxes = document.querySelectorAll('input[name="selectedValues[]"]:checked');
        const selectedValues = Array.from(checkboxes).map(checkbox => checkbox.value);
        if (selectedValues.length > 0) {
            return selectedValues[0];
        } else {
            return null;
        }
    }

    function getR(){
        return r;
    }

    function getRFromText(){
        const yy = document.getElementById('R-input').value;
        const yyy = yy.replace(',', '.');
        let y = parseFloat(yyy);

        if (y > 4 && y < 1){
            y = 1
        }

        return y
    }

    r = getRFromText()
    if (r == null){
        r = 1;
    }
    drawFig(1);

    submitBtn.addEventListener('click', function (e) {

        e.preventDefault();
        const x = getXfromCheck();

        const yy = document.getElementById('Y-input').value;
        const yyy = yy.replace(',', '.');
        const y = parseFloat(yyy);

        r = getRFromText();

        if(isNaN(x) || x == null){
            showNotification('Пожалуйста, выберите значение X.');
            return;
        }
        if (isNaN(y)) {
            showNotification('Пожалуйста, выберите значение Y.');
            return;
        }
        if (isNaN(x)){
            showNotification('Пожалуйста, выведите значение X.');
            return;
        }

        if (isNaN(x) || isNaN(y) || isNaN(r) || r <= 0) {
            showNotification('Пожалуйста, введите корректные значения.');
            return;
        }
        // Проверка значения Y
        if (x < -3 || x > 5) {
            showNotification('Значение X должно быть в пределах от -3 до 5 включительно.');
            return;
        }

        if (y < -3 || y > 5) {
            showNotification('Значение Y должно быть в пределах от -3 до 5 включительно.');
            return;
        }

        if (r < 1 || r > 4) {
            showNotification('Значение R должно быть в пределах от 1 до 4 включительно.');
            return;
        }



        send_intersection_rq(x, y, r);
    });

    function enable_graph() {
        if (graph_click_enabled) {
            elt.removeEventListener('click', handleGraphClick);
            graph_click_enabled = false;
        } else {
            elt.addEventListener('click', handleGraphClick);
            graph_click_enabled = true;
        }
    }

    enable_graph();

    function handleGraphClick (evt) {
        let r_val = getRFromText();

        console.log(r_val);
        if (r_val == null || r_val === "" || isNaN(r_val) ) {
            showNotification("Choose R!");
            return;
        }

        const rect = elt.getBoundingClientRect();
        const x = evt.clientX - rect.left;
        const y = evt.clientY - rect.top;

        const mathCoordinates = calculator.pixelsToMath({x: x, y: y});

        console.log('setting expression...');
        console.log(mathCoordinates);
        send_intersection_rq(mathCoordinates.x - 0.424 - 0.18, mathCoordinates.y + 0.202 + 0.038, r_val);
    }

    const rowsPerPage = 10; // Количество строк на одной странице
    const table = document.getElementById('historyTable'); // Таблица
    const rows = table.querySelectorAll('tbody tr'); // Все строки таблицы
    const pagination = document.getElementById('pagination'); // Контейнер для кнопок

    const totalPages = Math.ceil(rows.length / rowsPerPage); // Общее количество страниц

    function showPage(page) {
        // Скрыть все строки
        rows.forEach((row, index) => {
            row.style.display = 'none';
            if (index >= (page - 1) * rowsPerPage && index < page * rowsPerPage) {
                row.style.display = '';
            }
        });
    }

    function createPagination() {
        pagination.innerHTML = ''; // Очистить контейнер

        for (let i = 1; i <= totalPages; i++) {
            const button = document.createElement('button');
            button.textContent = i;
            button.classList.add('pagination-btn');
            button.addEventListener('click', function () {
                showPage(i);
                updateActiveButton(i);
            });
            pagination.appendChild(button);
        }
        updateActiveButton(1); // Установить первую кнопку активной
    }

    function updateActiveButton(activePage) {
        const buttons = pagination.querySelectorAll('.pagination-btn');
        buttons.forEach((button, index) => {
            button.classList.toggle('active', index + 1 === activePage);
        });
    }

    showPage(1); // Показать первую страницу по умолчанию
    createPagination(); // Создать кнопки пагинаци

});

function send_intersection_rq(x,y,r) {
    drawPoint(x, y, r);
    points.push(new Point(x, y, r));
    const resultsBody = document.getElementById("resultsbody");
    const bo = "?x=" + x + "&y=" + y + "&r=" + r;
    const url = window.location.href + "controller" + bo;

    fetch(url, {
        method: "GET",
        headers: {
            "Content-Type": "application/x-www-form-urlencoded"
        }
    })
        .then((response) => response.text())
        .then((data) => {
            console.log(data);
            window.location.href = 'table.jsp';

        })
        .catch((error) => alert(error));
}

function call() {
    let x = 0;
    const checkboxes = document.querySelectorAll('input[name="selectedValues[]"]:checked');
    const selectedValues = Array.from(checkboxes).map(checkbox => checkbox.value);
    if (selectedValues.length > 0) {
        x = selectedValues[0];
    } else {
        x = 1;
    }


    const yy = document.getElementById('Y-input').value;
    const yyy = yy.replace(',', '.');
    const y = parseFloat(yyy);

    const rr = document.getElementById('R-input').value;
    const rrr = rr.replace(',', '.');
    let r = parseFloat(rrr);

    if (r > 4 && r < 1){
        r = 1
    }

    if (isNaN(x) || isNaN(y)) {

    }
    else {
        drawPointXY(x, y);
    }
}
