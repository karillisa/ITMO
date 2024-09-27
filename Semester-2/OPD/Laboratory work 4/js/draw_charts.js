google.charts.load('current', {'packages':['corechart']});

google.charts.setOnLoadCallback(drawChart);

function drawChart() {

    var options = {
        width:250,
        height:80,
        title: 'Пульс',
        curveType: 'function',
        legend: { position: 'bottom' },
        colors: ['red','#004411']
    };

    var chart_divs = document.getElementsByClassName("chart_div")

    for(chart_div of chart_divs){
        console.log(chart_div);
        let start = parseInt(chart_div.dataset.start)
        let end = parseInt(chart_div.dataset.end)
        let during_json = chart_div.dataset.during
        try {
            var during = JSON.parse(during_json)
        } catch (e){

        }
        console.log(start, end, during)

        if(start && end && during){
            var array = [["time", 'pulse'], [0, start]]
            for(var i = 0; i < during.length; i++){
                array.push([i+1,parseInt(during[i])])
            }
            array.push([i+1, end])
            console.log(array);
            var data = google.visualization.arrayToDataTable(array);

            var chart = new google.visualization.LineChart(chart_div);
            chart.draw(data, options);

        }
    }
}