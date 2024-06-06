<?php
require_once "gates.php";
check_auth_admin();

require_once "db.php";
$pdo = getPDO();
$id = $_GET['id'];
$pdo->query("DELETE FROM user_profession_pvk WHERE profession_id = ".$id);
$result = $pdo->query("DELETE FROM professions WHERE id = ".$id);
if($result){
    header("Location: ../professions.php");
    exit();
}