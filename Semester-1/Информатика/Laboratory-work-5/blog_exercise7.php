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
$query = "SELECT * FROM notes";
$result = mysqli_query($conn. $query);

// 3. Отображение информации из таблицы
// 4. Использования цикла while для отображения всех записей 
while ($note = mysqli_fetch_array($result)) {
    echo "ID: " . $note['id'] . "<br>";
    echo "Title: ";
    ?>
    <a href="comment_exercise7.php?note=<?php echo $note['id']; ?>">
        <?php echo $note['title'], "<br>"; ?>
        </a>
        <?php
        echo "Created: " . $note['created'], "<br>";
        echo "Article: " . $note['article'], "<br><br>";
}
?>
