<?php
require_once "gates.php";
check_auth_expert();
require_once "db.php";
$pdo = getPDO();

if(!(isset($_POST['name']) &&  isset($_POST['description']))){
    die("error");
}

$name = $_POST['name'];
$description = $_POST['description'];

$result = $pdo->query("INSERT INTO professions SET name = '$name', description = '$description'");

if($result) {
    echo "ok";
    header("Location: ../professions.php");
}
echo "error";
