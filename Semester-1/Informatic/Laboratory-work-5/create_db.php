<?php
header('Content-Type: text/html; charset=utf-8');
    // Создать соединение с сервером
    $link = mysqli_connect ("localhost", "root", "Miranda2006.");
    if ($link) {
        echo "Соединение с сервером установлено", "<br>";
    } else {
        echo "Нет соеденения с сервером";
    }
    // Создать БД MySiteDB
    // Сначала формирование запроса на создание

    $db = "MySiteDB";
    $query = "CREATE DATABASE $db";
    // Зачем реализация запроса на создание. Важна последовательность аргументов функции: соединение с сервером, SQL-запрос.
    $create_db = mysqli_query($link, $query);
    if ($create_db) {
        echo "База данных $db успешно создана";
    } else {
        echo "База не создана";
    }
    # FileName="Connection_php_mysql.htm"
    # Type="MYSQL"
    # HTTP="true"

    $localhost = "localhost";
    $db = "MySiteDB";
    $user = "root";
    $password = "Miranda2006.";
    $link = mysqli_connect($localhost, $user, $password) operator
    trigger_error(mysqli_error(), E_USER_ERROR);

    mysqli_query($link, "SET NAMES cp1251;") or die(mysqli_error());
    mysqli_query($link, "SET CHARACTER SET cp1251;") or die(mysqli_error());
?>
