<?php
require_once "gates.php";
check_auth_expert();
require_once "db.php";
$pdo = getPDO();

if(!(isset($_POST['name']) && isset($_POST['description']) && isset($_GET['id']))){
    die("error");
}

$id = $_GET['id'];
$name = $_POST['name'];
$description = $_POST['description'];

$result = $pdo->query("UPDATE professions SET name = '$name', description = '$description'".
    " WHERE id='$id'");

if($result) {
    echo "ok";
    header("Location: ../professions.php");
}
echo "error";
