<?php
require_once "gates.php";
check_auth_expert();
require_once "db.php";
$pdo = getPDO();

if(!(isset($_POST['value']) &&  isset($_POST['pvk_id']))){
    die("error");
}

$values = $_POST['value'];
$pvk_ids = $_POST['pvk_id'];
$profession_id = $_POST['profession_id'];
$user_id = getCurrentUser()['id'];

foreach($values as $key => $value){
    $pvk_id = $pvk_ids[$key];
    $result = $pdo->query("REPLACE INTO user_profession_pvk (id, user_id, profession_id, pvk_id, val) ".
        "VALUES (null, $user_id, $profession_id, $pvk_id, $value)");
    if(!$result){
        echo "error";
        exit();
    }
}


echo "ok";
header("Location: ".$_SERVER['HTTP_REFERER']);
