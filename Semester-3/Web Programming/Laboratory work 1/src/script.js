document.addEventListener('DOMContentLoaded', function () {
  const form = document.getElementById('pointForm');
  const resultTd = document.getElementById('result');
  const timeTd = document.getElementById('time');
  const executionTimeTd = document.getElementById('executionTime');
  const historyTable = document.getElementById('historyTable');
  const clearHistoryButton = document.getElementById('clearHistoryButton');
  const point = document.getElementById('point');

  // Обработчик нажатия на кнопку "Стереть историю"
  clearHistoryButton.addEventListener('click', function () {
    // Очистить Local Storage
    localStorage.removeItem('history');

    // Очистить таблицу истории (кроме шапки)
    while (historyTable.rows.length > 1) {
        historyTable.deleteRow(1);
    }
  });

  // Функция для сохранения результатов в Local Storage
  function saveResultToLocalStorage(result) {
      const history = JSON.parse(localStorage.getItem('history')) || [];
      history.push(result); 
      localStorage.setItem('history', JSON.stringify(history));
  }

  // Функция для отображения истории из Local Storage
  function displayHistoryFromLocalStorage() {
      const history = JSON.parse(localStorage.getItem('history')) || [];

      // Очистить таблицу, оставив первую строку (шапку)
      while (historyTable.rows.length > 1) {
          historyTable.deleteRow(1);
      }

      history.forEach((entry, index) => {
          const row = historyTable.insertRow();
          row.insertCell(0).textContent = index + 1;
          row.insertCell(1).textContent = entry.x;
          row.insertCell(2).textContent = entry.y;
          row.insertCell(3).textContent = entry.r;
          row.insertCell(4).textContent = entry.result ? 'Попадание' : 'Промах';
          row.insertCell(5).textContent = entry.time;
          row.insertCell(6).textContent = entry.execution_time;
      });
  }

  /*function setOnClick(element) {
    element.onclick = function () {
      r = this.value;
      buttons.forEach(function (element) {
        element.style.boxShadow = "";
        element.style.transform = "";
      });
      this.style.boxShadow = "0 0 40px 5px #f41c52";
      this.style.transform = "scale(1.05)";
    }
  }

  let buttons = document.querySelectorAll("input[name=R-button]");
  buttons.forEach(setOnClick);*/


  // Отобразить историю при загрузке страницы
  displayHistoryFromLocalStorage();

  function showNotification(textInput, message) {
    const parentDiv = textInput.parentElement;
    const warningSpan = document.createElement('span');
    warningSpan.classList.add('warning');
    warningSpan.innerText = message;
    parentDiv.appendChild(warningSpan);
    textInput.classList.add('error');
  
    // Убираем уведомление через 3 секунды 
    setTimeout(() => {
      warningSpan.remove();
      textInput.classList.remove('error');
    }, 3000);
  }  

  form.addEventListener('submit', function (e) {

      e.preventDefault();
      const xr = document.querySelectorAll('input[type=radio]:checked');
      const yy = document.getElementById('Y-input').value;
      let yyy = yy.replace(',', '.');
      let y = parseFloat(yyy);
      let x = null;
      let rr = document.getElementById('R-input').value;
      let rrr = rr.replace(',', '.');
      let r = parseFloat(rrr);


      if (xr.length > 0) {
        x = parseFloat(xr[0].value); // Берем значение из выбранной радиокнопки
    } else {
      alert('Пожалуйста, выберите значение X.');
        return;
    }
    if (isNaN(y)){
      showNotification(document.getElementById('Y-input'), 'Пожалуйста, введите корректные значение Y.');
      return;
    }

    if (isNaN(x) || isNaN(y) || isNaN(r) || r <= 0) {
      showNotification(document.getElementById('R-input'), 'Пожалуйста, введите корректные значения.');
      return;
    }

    // Проверка значения Y
    if (y < -5 || y > 3) {
      showNotification(document.getElementById('Y-input'), 'Значение Y должно быть в пределах от -5 до 3 включительно.');
      return;
    }

    if (r < 2 || r > 5) {
      showNotification(document.getElementById('R-input'), 'Значение R должно быть в пределах от 2 до 5 включительно.');
      return;
    }
     
    const data1 = JSON.stringify({x: x, y: y, r: r});

    fetch('/fcgi-bin/server.jar', {
        method: 'POST',
        headers: {'Content-Type': 'application/json'},
        body: data1
    }).then(response => response.json()).then(data => {
            resultTd.textContent = data.result ? 'Попадание' : 'Промах';
              timeTd.textContent = data.currentTime;
              executionTimeTd.textContent = data.executionTime + ' микросек';
              point.setAttribute("cx", 150 + ((120/r) * x));
              point.setAttribute("cy", 150 - ((120/r) * y));
              if(!data.result){ 
                point.setAttribute("fill", "red");
              }else{
                point.setAttribute("fill", "green");
              }

              let res = data.result
              let timee = data.currentTime
              let ex = data.executionTime

              let dataa = {x: x, y: y, r: r, result: res, time: timee, execution_time: ex};

              saveResultToLocalStorage(dataa);
              // Отобразить обновленную историю
              displayHistoryFromLocalStorage();

        })
        .catch(error => console.error('Error:', error));
    });

      /*fetch(`script.php?x=${x}&y=${y}&r=${r}`)
          .then(response => response.json().then(data => {
              
              resultTd.textContent = data.result ? 'Попадание' : 'Промах';
              timeTd.textContent = data.time;
              executionTimeTd.textContent = data.execution_time.toFixed(Int8Array) + ' микросек';
              point.setAttribute("cx", 150 + ((120/r) * x));
              point.setAttribute("cy", 150 - ((120/r) * y));
              if(!data.result){ 
                point.setAttribute("fill", "red");
              }else{
                point.setAttribute("fill", "green");
              }

              // Сохранить результат в Local Storage
              saveResultToLocalStorage(data);
              // Отобразить обновленную историю
              displayHistoryFromLocalStorage();
          }))
          .catch(error => console.error(error));*/
  });