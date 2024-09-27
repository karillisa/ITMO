<?php
require_once "gates.php";
check_auth_admin();
require_once "db.php";
$pdo = getPDO();
$criteria_id = $_GET['criteria_id'];
$test_id = $_GET['test_id'];
$result = $pdo->query("DELETE FROM criteria_test WHERE criteria_id = $criteria_id AND test_id = $test_id");
if($result){
    header("Location: ".$_SERVER['HTTP_REFERER']);
    exit();
}