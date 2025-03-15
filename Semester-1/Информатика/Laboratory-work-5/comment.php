<?php
header('Content-Type: text/html; charset=utf-8');
// 1. Подключение к базе данных
$servername = "localhost";
$username = "root";
$password = "Miranda2006.";
$dbname = "MySiteDB";

$conn = mysqli_connect($servername, $username, $password, $dbname);

// Проверка соединения 
if (!$conn) {
    die("Соединение не удалось:" . mysqli_connect_error());
}

// 2. Выполнение запроса для извлечения всех данных из таблицы notes
$query = "SELECT * FROM comments";
$result = mysqli_query($conn. $query);

// 3. Отображение информации из таблицы
// 4. Использования цикла while для отображения всех записей 
while ($comment = mysqli_fetch_array($result)) {
    echo "ID: " . $comment['id'] . "<br>";
    echo "Created: " . $comment['created'] . "<br>";
    echo "Author: " . $comment['author'] . "<br>";
    echo "Comment: " . $comment['comment'] . "<br>";
    echo "Art_id: " . $comment['art_id'] . "<br>";
}
?>
