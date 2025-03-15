let graph_click_enabled = false;




const elt = document.getElementById('calculator');
const calculator = Desmos.GraphingCalculator(elt, {
    keypad: false,
    expressions: false,
    settingsMenu: false,
    invertedColors: true,
    zoomButtons: false,
    lockViewport: true,
    xAxisLabel: 'x',
    yAxisLabel: 'y',
    xAxisStep: 1,
    yAxisStep: 1,
    xAxisArrowMode: Desmos.AxisArrowModes.POSITIVE,
    yAxisArrowMode: Desmos.AxisArrowModes.POSITIVE
});

calculator.setMathBounds({
    left: -6,
    right: 6,
    bottom: -6,
    top: 6
});

let newDefaultState = calculator.getState();
calculator.setDefaultState(newDefaultState);

function drawPointXYRED(x, y) {
    calculator.setExpression({
        id: x + '' + y,
        latex: '(' + x + ', ' + y + ')',
        color: Desmos.Colors.RED
    });
}

function drawPointXYBLUE(x, y) {
    calculator.setExpression({
        id: x + '' + y,
        latex: '(' + x + ', ' + y + ')',
        color: Desmos.Colors.BLUE
    });
}

function drawPoint(x, y, r) {
    calculator.setExpression({
        id: x + '' + y,
        latex: '(' + x + ', ' + y + ')',
        color: Desmos.Colors.BLACK
    });
    drawFig(r);
}


function drawFig(R){
    calculator.setExpression({ id: 'triangle', latex: `\\polygon((${-R/2}, 0), (0, 0), (0, -${R/2}))`, color: Desmos.Colors.RED, opacity: 0.3});
    calculator.setExpression({ id: 'rectangle', latex: `\\polygon((0, 0), (${R}, 0), (${R}, ${-R/2}), (0, ${-R/2}))`, color: Desmos.Colors.RED, opacity: 0.3});
    calculator.setExpression({id: 'circle', latex: `r<=${R/2} \\{\\frac{\\pi}{2}\\le\\theta\\le\\pi\\}`, color: Desmos.Colors.RED});
}

function drawPointXYRes(x, y, result) {calculator.setExpression({
    id: x + '' + y,
    latex: '(' + x + ', ' + y + ')',
    color: result ? Desmos.Colors.PURPLE : Desmos.Colors.BLUE
});
}

function inRectangle(point, rect) {
    return (
        point.x >= rect.left &&
        point.x <= rect.right &&
        point.y <= rect.top &&
        point.y >= rect.bottom
    )
}