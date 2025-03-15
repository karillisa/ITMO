let graph_click_enabled = false;

var pointsList = [];

const elt = document.getElementById('calculator');
const calculator = Desmos.GraphingCalculator(elt, {
    keypad: false,
    expressions: false,
    settingsMenu: false,
    zoomButtons: false,
    invertedColors: true,
    xAxisLabel: 'x',
    yAxisLabel: 'y',
    xAxisStep: 1,
    yAxisStep: 1,
    lockViewport: true
});

calculator.setMathBounds({
    left: -6,
    right: 6,
    bottom: -6,
    top: 6
});

let newDefaultState = calculator.getState();
calculator.setDefaultState(newDefaultState);

function drawFig(R){
    calculator.setExpression({ id: 'triangle', latex: `\\polygon((${-R}, 0), (0, ${-R}), (0, 0))`, color: Desmos.Colors.RED, opacity: 0.3});
    calculator.setExpression({ id: 'rectangle', latex: `\\polygon((${-R/2}, 0), (${-R/2}, ${R}), (0, ${R}), (0, 0))`, color: Desmos.Colors.RED, opacity: 0.3});
    calculator.setExpression({id: 'circle', latex: `x^{2}+y^{2}\\ <=\\left(${R/2}\\right)^{2}\\ \\left\\{y\\ >0\\right\\}\\left\\{x>0\\right\\}`, color: Desmos.Colors.RED});
}

function drawPointXY(x, y) {
    calculator.setExpression({
        id: x + '' + y,
        latex: '(' + x + ', ' + y + ')',
        color: Desmos.Colors.RED
    });
}

function drawPointXYRED(x, y) {
    calculator.setExpression({
        id: x + '' + y,
        latex: '(' + x + ', ' + y + ')',
        color: Desmos.Colors.BLUE
    });
}
function drawPointXYGREEN(x, y) {
    calculator.setExpression({
        id: x + '' + y,
        latex: '(' + x + ', ' + y + ')',
        color: Desmos.Colors.GREEN
    });
}

function drawPointBasedOnResult(x, y, r, result) {
    const r_val = document.getElementById('ker:rValue').value;
    if (result) {
        drawPointXYGREEN((x * (r_val / r)), (y * (r_val / r)));
    } else {
        drawPointXYRED((x * (r_val / r)), (y * (r_val / r)));
    }
}
