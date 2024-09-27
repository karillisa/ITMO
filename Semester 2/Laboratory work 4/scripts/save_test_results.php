<?php
require_once "gates.php";
check_auth();


require_once "get_user_tests.php";
$pdo = getPDO();

$test_id = $_POST['test_id'];
$result = $_POST['result'];
$user_id = getCurrentUser()['id'];
$next_test_id = $test_id;
$complexity = intval($_POST['complexity']);
if($complexity == 3 || !isset($_POST['complexity']))
    $next_test_id = getNextTestForCurrentUser($test_id)['test_id'];



$date = date("Y/m/d H:i:s");

if(isset($_POST['end_pulse']) || isset($_POST['start_pulse']) || isset($_POST['during_pulse'])){
    $start_pulse = intval($_POST['end_pulse']) ?? 0;
    $end_pulse = intval($_POST['start_pulse']) ?? 0;
    $during_pulse = $_POST['during_pulse'] ?? "";
    $sql = "INSERT INTO user_test_results (id, user_id, test_id, result, date, complexity, start_pulse, end_pulse, during_pulse)".
        "VALUES (null, $user_id, $test_id, $result, '$date', $complexity, $start_pulse, $end_pulse, '$during_pulse')";
} else {
    $sql = "INSERT INTO user_test_results (id, user_id, test_id, result, date, complexity)".
        "VALUES (null, $user_id, $test_id, $result, '$date', $complexity)";
}

$result = $pdo->query($sql);

if(!$next_test_id){
    $test = getTestById($test_id);
    header("Location: ../main.php?test_category=".$test['category_id']);
    exit();
}

if($next_test_id == $test_id) {
    $complexity++;
} else {
    $complexity = 1;
}

if($result){
    echo "ok";

    header("Location: ../tests/test.php?id=$next_test_id&complexity=$complexity");
}