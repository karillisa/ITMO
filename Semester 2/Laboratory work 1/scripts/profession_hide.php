<?php
require_once "gates.php";
check_auth_expert();
require_once "db.php";
$pdo = getPDO();

if(!(isset($_GET['id']))){
    die("error");
}

$id = $_GET['id'];

$result = $pdo->query("UPDATE professions SET hidden = true".
    " WHERE id='$id'");

if($result) {
    echo "ok";
    header("Location: ../professions.php");
}
echo "error";
