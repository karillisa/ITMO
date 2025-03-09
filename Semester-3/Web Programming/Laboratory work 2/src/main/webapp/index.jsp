<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="core" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.time.format.DateTimeFormatter" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<core:set var="userName" value="" scope="page" />


<!DOCTYPE html>
<html lang="ru-RU">
<head>
    <title>WEB LAB 2</title>
    <link rel="stylesheet" href="style/style.css">
    <meta charset="UTF-8">
    <script type="text/javascript"></script>
    <script src="script/script.js"></script>

</head>
<body>
<header>
    <nav>
        <img src="https://itmo.ru/promo/itmo-logo-dark.svg">
        <p class="variant">B-3232</p>
        <p class="author">Гафурова Фарангиз Фуркатовна P3220</p>
    </nav>
    <div>
        <form action="${pageContext.request.contextPath}/controller" method="GET">
            <input type="hidden" name="clear" value="1" />
            <div id="clear-table-container" class="select-container margin">
                <input type="submit" id="clear-table" value="Clear table" />
            </div>
        </form>
    </div>
</header>
<div id="notification-container"></div>

<div id="allBody">
    <div id="bodyA">
        <div id = "graff">
            <div id="calculator" style="width: 500px; height: 500px;"></div>
            <script src="https://www.desmos.com/api/v1.8/calculator.js?apiKey=dcb31709b452b1cf9dc26972add0fda6"></script>

            <script src="script/graph.js"></script>

        </div>

        <form action="${pageContext.request.contextPath}/controller" method="post">
            <div>
                <label>Value of X:</label><br>

                <label><input type="checkbox" name="selectedValues[]" value="-3"> -3</label><br>
                <label><input type="checkbox" name="selectedValues[]" value="-2"> -2</label><br>
                <label><input type="checkbox" name="selectedValues[]" value="-1"> -1</label><br>
                <label><input type="checkbox" name="selectedValues[]" value="0"> 0</label><br>
                <label><input type="checkbox" name="selectedValues[]" value="1"> 1</label><br>
                <label><input type="checkbox" name="selectedValues[]" value="2"> 2</label><br>
                <label><input type="checkbox" name="selectedValues[]" value="3"> 3</label><br>
                <label><input type="checkbox" name="selectedValues[]" value="4"> 4</label><br>
                <label><input type="checkbox" name="selectedValues[]" value="5"> 5</label><br>
            </div>

            <div id="y_text">
                <div id="chosingValTextY"><label>Value of Y:</label></div>
                <input type="text" name="Y-input" id="Y-input" maxlength="6" placeholder="-3...5">
            </div>

            <div id="r_text">
                <div id="chosingValTextR"><label>Value of R:</label></div>
                <input type="text" name="R-input" id="R-input" maxlength="6" placeholder="1...4" value="1">
                <script>
                    drawFig(1)
                </script>
            </div>


            <input type="submit" value="Answer" id="submit" class = "submit">
        </form>

    </div>

    <div id="man">
        <table id="historyTable" class="tab1">
            <thead>
            <tr>
                <th>X</th>
                <th>Y</th>
                <th>R</th>
                <th>Result</th>
                <th>Executed at</th>
                <th>Execution time</th>
            </tr>
            </thead>
            <tbody>

            <script>
                document.getElementById("R-input").addEventListener("change", function() {
                    const rr = document.getElementById('R-input').value;
                    const rrr = rr.replace(',', '.');
                    let r = parseFloat(rrr);

                    if (r > 4 && r < 1){
                        r = 1
                    }
                    calculator.removeExpressions(calculator.getExpressions({type: 'point'}));
                    drawFig(r)
                });
            </script>

            <core:forEach var="areaData" items="${applicationScope.globalData}">
                <script type="text/javascript">

                    var b = false;
                    document.getElementById("R-input").addEventListener("change", function() {
                        rr = this.value;

                        if (${areaData.result} === true){
                            drawPointXYRED(${areaData.x} * (rr /  ${areaData.r}), ${areaData.y} * (rr /  ${areaData.r}))
                        }else{
                            drawPointXYBLUE(${areaData.x} * (rr /  ${areaData.r}), ${areaData.y} * (rr /  ${areaData.r}))
                        }
                        b = true;
                    });

                    if(b == false){
                        let rr = document.getElementById("R-input").value;
                        if (${areaData.result} === true){
                            drawPointXYRED(${areaData.x} * (rr /  ${areaData.r}), ${areaData.y} * (rr /  ${areaData.r}))
                        }else{
                            drawPointXYBLUE(${areaData.x} * (rr /  ${areaData.r}), ${areaData.y} * (rr /  ${areaData.r}))
                        }
                    }

                </script>
            </core:forEach>


            <div id="ker">

            </div>


            <core:forEach var="areaData" items="${applicationScope.globalData}">
                <tr>
                    <td>${areaData.x}</td>
                    <td>${areaData.y}</td>
                    <td>${areaData.r}</td>
                    <td>${areaData.result ? "Попадание" : "Промах"}</td>
                    <td>${areaData.calculatedAt.format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss"))}</td>
                    <td>${areaData.calculationTime}</td>
                </tr>
            </core:forEach>
            </tbody>
        </table>
        <div id = "resultsbody">
        </div>
        <div id="pagination"></div>


    </div>
</div>
<script type="text/javascript">
    drawFig(1);
    call();
</script>
</body>
</html>
