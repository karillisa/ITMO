<?php
header('Content-Type: text/html; charset=utf-8');

// Соединение с сервером
$link = mysqli_connect('localhost', 'root', 'Miranda2006.', 'MySiteDB');

// Проверка соединения
if (!$link) {
    die("Ошибка соединения: " . mysqli_connect_error());
} 

// Создание пользователя 
$query = "GRANT SELECT, INSERT, UPDATE, DELETE ON MySiteDB.* TO 'admin'@'localhost' IDENTIFIED BY 'Miranda2006.'";

// Выполнение запроса
$result = mysqli_query($link, $query);

if ($result) {
    echo "Пользователь успешно создан";
} else {
    echo "Ошибка при создании пользователя: " . mysqli_error($link);
}

// Закрытие соединения
mysqli_close($link);
?>
