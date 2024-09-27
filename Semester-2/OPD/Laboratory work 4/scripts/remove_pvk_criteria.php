<?php
require_once "gates.php";
check_auth_admin();
require_once "db.php";
$pdo = getPDO();
$criteria_id = $_GET['criteria_id'];
$pvk_id = $_GET['pvk_id'];
$result = $pdo->query("DELETE FROM pvk_criteria WHERE criteria_id = $criteria_id AND pvk_id = $pvk_id");
if($result){
    header("Location: ".$_SERVER['HTTP_REFERER']);
    exit();
}