
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
    const r_val = document.getElementById('ker:rValue').value;

    if (r_val <= 0.000001) {
        alert("Choose R!");
        return;
    }

    const rect = elt.getBoundingClientRect();
    const x = evt.clientX - rect.left;
    const y = evt.clientY - rect.top;

    // Note, pixelsToMath expects x and y to be referenced to the top left of
    // the calculator's parent container.
    const mathCoordinates = calculator.pixelsToMath({x: x, y: y});

    send_intersection_rq(mathCoordinates.x - 0.608, mathCoordinates.y + 0.245, r_val);
}

function send_intersection_rq(xValue, yValue, rValue){
    if(xValue.toFixed(3) >= -4 && xValue.toFixed(3) <= 4){
        if(yValue.toFixed(3) >= -5 && yValue.toFixed(3) <= 3){
            document.getElementById('ker:y').value = yValue.toFixed(3);

            const slider = PF('sliderWidget');
            slider.setValue(yValue.toFixed(3));

            document.getElementById('ker:outputt').textContent = 'X: ' +  xValue.toFixed(3);
            document.getElementById('ker:txtInput').value = xValue.toFixed(3);

            calculator.removeExpressions(calculator.getExpressions({type: 'point'}));

            pointsList.forEach(function(point) {
                drawPointBasedOnResult(point.x, point.y, point.r, point.result);
            });

            drawPointXY(xValue.toFixed(3), yValue.toFixed(3));

            drawFig(document.getElementById('ker:rValue').value)

            // Устанавливаем значения в скрытую форму
            /*document.getElementById('ker:js-form:js-x').value = xValue.toFixed(3);
            document.getElementById('ker:js-form:js-y').value = yValue.toFixed(3);
            document.getElementById('ker:js-form:js-r').value = rValue;*/

            // Вызываем отправку формы через JS
            document.getElementById('ker:send-button').click();
        }else{
            alert("Y should be about -3...5");
        }
    }else{
        alert("X should be about -4...4");
    }
}

