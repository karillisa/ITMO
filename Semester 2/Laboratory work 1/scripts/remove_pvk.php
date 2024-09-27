<?php
require_once "gates.php";
check_auth_admin();

require_once "db.php";
$pdo = getPDO();
$id = $_GET['id'];
$result = $pdo->query("DELETE FROM pvk WHERE id = ".$id);
if($result){
    header("Location: ../pvk.php");
    exit();
}