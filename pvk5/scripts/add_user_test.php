<?php
require_once "gates.php";
check_auth_expert();
require_once "db.php";
$pdo = getPDO();
var_dump($_POST);
if(!(isset($_POST['test_id']))){
    die("error");
}

$test_ids = $_POST['test_id'];
$user_id = $_POST['user_id'];

foreach($test_ids as $key => $value){
    $test_id = $test_ids[$key];
    $result = $pdo->query("REPLACE INTO user_tests (id, user_id, test_id, order_for_user) ".
        "VALUES (null, $user_id, $test_id, $key)");
    if(!$result){
        echo "error";
        exit();
    }
}


echo "ok";
header("Location: ".$_SERVER['HTTP_REFERER']);
