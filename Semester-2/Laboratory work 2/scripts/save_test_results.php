<?php
require_once "gates.php";
check_auth();


require_once "get_user_tests.php";
$pdo = getPDO();

$test_id = $_POST['test_id'];
$next_test_id = getNextTestForCurrentUser($test_id)['test_id'];


$result = $_POST['result'];
$user_id = getCurrentUser()['id'];

$date = date("Y/m/d H:i:s");
$result = $pdo->query("INSERT INTO user_test_results (id, user_id, test_id, result, date)".
    "VALUES (null, $user_id, $test_id, $result, '$date')");

if(!$next_test_id){
    header("Location: ../main.php");
    exit();
}
if($result){
    echo "ok";
    header("Location: ../tests/test.php?id=$next_test_id");
}