<?php
require_once "gates.php";
check_auth();


require_once "get_user_tests.php";
$pdo = getPDO();

$test_id = $_POST['test_id'];
$result = $_POST['result'];
$complexity = intval($_POST['complexity']);
$user_id = getCurrentUser()['id'];
$next_test_id = $test_id;
if($complexity == 3)
    $next_test_id = getNextTestForCurrentUser($test_id)['test_id'];

if($next_test_id == $test_id) {
    $complexity++;
} else {
    $complexity = 1;
}

$date = date("Y/m/d H:i:s");
$result = $pdo->query("INSERT INTO user_test_results (id, user_id, test_id, result, date, complexity)".
    "VALUES (null, $user_id, $test_id, $result, '$date', $complexity)");

if(!$next_test_id){
    $test = getTestById($test_id);
    header("Location: ../main.php?test_category=".$test['category_id']);
    exit();
}
if($result){
    echo "ok";

    header("Location: ../tests/test.php?id=$next_test_id&complexity=$complexity");
}