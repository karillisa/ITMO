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

// 2. Получение переданного с помощью идентификатора note id заметки
$note_id = $_GET['note'];

// 3. Формирование SQL-запроса на выборку заметки
$query_note = "SELECT created, title, article FROM notes WHERE id = $note_id";
$result_note = mysqli_query($conn, $query_note);
$selected_note = mysqli_fetch_array($result_note);

// 4. Отображение выбранной заметки
echo "Created: " . $selected_note['created'], "<br>";
echo "Title: " . $selected_note['title'], "<br>";
echo "Article: " . $selected_note['article'], "<br>";

// 5. Формирование SQL-запроса на выборку комментариев
$query_comments = "SELECT * FROM comments WHERE art_id = $note_id";
$result_comments = mysqli_query($conn, $query_comments);

// 6. Отображение комментариев
while ($comment = mysqli_fetch_array($result_comments)) {
    echo "Author: " . $comment['author'], "<br>";
    echo "Comment: " . $comment['comment'], "<br><br>";
} 
?>
