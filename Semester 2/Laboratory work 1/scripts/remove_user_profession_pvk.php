<?php
require_once "gates.php";
check_auth_admin();
require_once "db.php";
$pdo = getPDO();
$id = $_GET['id'];
$result = $pdo->query("DELETE FROM user_profession_pvk WHERE id = ".$id);
if($result){
    header("Location: ".$_SERVER['HTTP_REFERER']);
    exit();
}