<?php
// 1. Assigh values to variables $a and $b and print them
header('Content-Type: text/html; charset=utf-8');
$a = 10;
$b = 20;
echo "<h2>1.<br></h2>a = $a<br>";
echo "\tb = $b<br>";

// 2. Assign the sum as $a and $b to $c and print it
$c = $a + $b;
echo "<h2>2.</h2> c = a + b = $c<br>";

// 3. Triple the value of $c and print the result
$c = $c * 3;
echo "<h2>3.</h2> c = $c<br>";

// 4 Divide $c by the difference of $b and $a and print the result
$c =$c / ($b - $a);
echo "<h2>4.</h2> c = $c<br>";

// 5. Create new variables $p and $b, assigh values and print them
$p = "Программа";
$b = "работает";

// 6. Concatenate the strings
$result = $p ." ". $b;
echo "<h2>6.</h2> $result<br>";

// 7. 'Хорошо' to $result using '.='
$result .= " "."хорошо";
echo "<h2>7.</h2> $result<br>";

// 8. Swap values
$q = 5;
$w = 7;
// Используем временную переменную для обмена зачениями
$temp = $q;
$q = $w;
$w = $temp;
// Выводим результат на экран 
echo "<h2>8.</h2> После обмена: \$q = $q, \$w = $w<br>";

// 9. Loop 23 to 78
echo "<h2>9.<br></h2>";
for( $i = 23;$i < 79; $i++ )
{
    echo "$i ";
}
echo "<br>";

// 10. Loop 10 itemps
$items = array();
echo "<h2>10.<br></h2>";
for ($i = 1; $i <= 10; $i++){
    $items[] = 'Пункт '. $i;
}

// Перемешиваем массив
shuffle($items);

// Выводим перемешанный массив
foreach ($items as $item){
    echo '<li>' . $item . '</li>'
}
// 11. 100 random numbers
$randomNumbers = array();
for ($i = 0; $i < 100; $i++) {
    $randomNumbers[] = rand(1, 1000); //Генерация случайного числа от 1 до 1000
}

// Выводим массив с использованием цикла while
echo '<h2>11. Вывод массива с использованием цикла while:</h2>';
$counter = 0;
while ($counter < count($randomNumbers)){
    echo $randomNumbers[$counter] . ', ';
    $counter++;
}

// Выводим массив с использованием цикла foreach
echo '<h2>Выводим массив с использованием цикла foreach:</h2>';
foreach ($randomNumbers as $number) {
    echo "$number, ";
}
echo "<br>";

// 12. A script using the operator switch
$dayOfWeek = date("1");

// Используем оператоор switch для вывода надписи в зависимости от дня недели
switch ($dayOfWeek) {
    case "Monday":
        $message = "Сегодня понедельник.";
        break;
    case "Tuesday":
        $message = "Сегодня вторник.";
        break;
    case "Wednesday":
        $message = "Сегодня среда.";
        break;
    case "Thursday":
        $message = "Сегодня четверг.";
        break;
    case "Friday":
        $message = "Сегодня пятница.";
        break;
    case "Saturday":
        $message = "Сегодня суббота.";
        break;
    case "Sunday":
        $message = "Сегодня воскресенье.";
        break;
    default:
        $message = "Не удалось определить текущий день недели.";
}

// Выводим сообщение 
echo "<h2>12.</h2>";
echo "$message<br>";

// 13. Function to add 10 to a number and print the result
echo "<h2>13.</h2>";

function getPlus10($number){
    $result = $number + 10;
    return $result;
}
$sum = getPlus10(20);
echo "Result of function getPlus10(20) is $sum<br>";
?>
