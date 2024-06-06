<?php
require_once "gates.php";
check_auth_expert();
require_once "db.php";
$pdo = getPDO();

if(!(isset($_POST['value']) &&  isset($_POST['criteria_id']) && isset($_POST['pvk_id']))){
    die("error");
}

$values = $_POST['value'];
$criteria_ids = $_POST['criteria_id'];
$pvk_id = $_POST['pvk_id'];

foreach($values as $key => $value){
    $criteria_id = $criteria_ids[$key];
    $result = $pdo->query("REPLACE INTO pvk_criteria (criteria_id, pvk_id, val) ".
        "VALUES ($criteria_id, $pvk_id, $value)");
    if(!$result){
        echo "error";
        exit();
    }
}


echo "ok";
header("Location: ".$_SERVER['HTTP_REFERER']);
