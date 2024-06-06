<?php
require_once "gates.php";
check_auth_expert();
require_once "db.php";
$pdo = getPDO();

if(!(isset($_POST['value']) &&  isset($_POST['test_id']))){
    die("error");
}

$values = $_POST['value'];
$test_ids = $_POST['test_id'];
$criteria_id = $_POST['criteria_id'];

foreach($values as $key => $value){
    $test_id = $test_ids[$key];
    $result = $pdo->query("REPLACE INTO criteria_test (test_id, criteria_id, val) ".
        "VALUES ($test_id, $criteria_id, $value)");
    if(!$result){
        echo "error";
        exit();
    }
}


echo "ok";
header("Location: ".$_SERVER['HTTP_REFERER']);
