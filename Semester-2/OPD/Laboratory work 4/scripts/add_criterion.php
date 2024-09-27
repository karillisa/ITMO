<?php
require_once "gates.php";
check_auth_expert();
require_once "db.php";
$pdo = getPDO();

if(!(isset($_POST['name']))){
    die("error");
}

$name = $_POST['name'];

$result = $pdo->query("INSERT INTO criteria SET name = '$name'");

if($result) {
    echo "ok";
    header("Location: ../criteria.php");
}
echo "error";
