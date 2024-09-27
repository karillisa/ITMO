<?php
require_once "gates.php";
check_auth_expert();
require_once "db.php";
$pdo = getPDO();

if(!(isset($_POST['name']) && isset($_GET['id']))){
    die("error");
}

$id = $_GET['id'];
$name = $_POST['name'];

$result = $pdo->query("UPDATE criteria SET name = '$name'".
    " WHERE id='$id'");

if($result) {
    echo "ok";
    header("Location: ../criteria.php");
}
echo "error";
