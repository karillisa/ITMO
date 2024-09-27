<?php
require_once "gates.php";
check_auth_admin();

require_once "db.php";
$pdo = getPDO();

if(!(isset($_POST['name']) &&
    isset($_POST['second_name']) &&
    isset($_POST['email']) &&
    isset($_POST['status'])
)){
    die("error");
}

$id = $_GET['id'];
$name = $_POST['name'];
$second_name = $_POST['second_name'];
$email = $_POST['email'];
$status = $_POST['status'];



if(strlen($name) < 4 || strlen($second_name) < 4){
    die("error2");
}
if (!filter_var($email, FILTER_VALIDATE_EMAIL)) {
    die("error3");
}

$result = $pdo->query("UPDATE users SET name = '$name', second_name = '$second_name', email = '$email', status = '$status' ".
    "WHERE id = $id");

if($result){
    echo "ok";
    header("Location: ../experts.php");
}