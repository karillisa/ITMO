<?php
header('Content-Type: text/html; charset=utf-8');
// Соединение с сервером
$link = mysqli_connect('localhost', 'root', 'Miranda2006.');

// Выбор БД
$db = "MySiteDB";
$select = mysqli_select_db($link, $db);

if ($select) {
    echo "База успешно выбрана", "<br>";
} else {
    echo "База не выбрана";
}

// Создание таблицы
// Формирование запроса
$create_notes_table = "CREATE TABLE notes
    (id INT NOT NULL AUTO_INCREMENT,
    PRIMARY KEY (id),
    create DATE,
    title VARCHAR(255),
    article VARCHAR(255))";

$create_comments_table = "CREATE TABLE comments
    (id INT NOT NULL AUTO_INCREMENT,
    PRIMARY KEY (id),
    create DATE,
    author VARCHAR(255),
    comment VARCHAR(256),
    art_id INT,
    FOREIGN KEY (art_id) REFERENCES notes (id))";

// Реализация запроса
$create_tbl_notes = mysqli_query($link, $create_notes_table);
$create_tbl_comments = mysqli_query($link, $create_comments_table);

if ($create_tbl_notes && $create_tbl_comments) {
    echo "Таблицы успешно созданы", "<br>";
} else {
    echo "Таблицы не созданы<br>";
}

// Добавление данных в таблицу notes
$notes_data = array(
    array('Summer Vacation', 'Our family enjoyed a wonderful summer vacation at the beach.'),
    array('Tech Trends 2023', 'Exploring the latest technology trends shaping the year 2023.'),
    array('Healthy Cooking', 'Learn how to prepare delicios and healthy meals at home.'),
    array('Fitness Journey', 'Documenting the fitness journey and the path to a healthier lifestyle.'),
    array('The Power of Habit', 'Sharing insights and takeaways from the popular book.'),
);
foreach ($notes_data as $data) {
    $add_note_query = "INSERT INTO notes (created, title, article)
                        VALUES (NOW(), '{$data[0]}', '{$data[1]}')";
    
    $add_note_result = mysqli_query($link, $add_note_query);
    if ($add_note_result) {
        echo "Данные успешно добавлены в таблицу notes", "<br>";
    } else {
        echo "Ошибка при добавлении данных: " . mysqli_error($link), "<br>";
    }
}

// Добавление данных в таблицу comments
$comments_data = array(
    array('John Doe', 'Greate article! I learned a lot.', 1),
    array('Jane Smith', 'The content is very informative.', 2),
    array('Bob Johnson', 'I have a question about the third paragraph.', 3),
    array('Alice Williams', 'This is inspiring. thanks for sharing!', 4),
    array('David Davis', 'Interesting perspective on the topic.', 5),
);
foreach ($comments_data as $data) {
    $add_comment_query = "INSERT INTO comments (created, author, comment, art_id)
                        VALUES (NOW(), '$data[0]', '$data[1]', '$data[2]')";
$add_comment_result = mysqli_query($link, $add_comment_query);
if ($add_comment_result) {
    echo "Данные успешно добавлены в таблицу comments", "<br>";
} else {
    echo "Ошибка при добавлении данных: " . mysqli_error($link), "<br>";
    }
}
?>
