<?php
require_once "db.php";
$pdo = getPDO();

if(!(isset($_POST['name']) &&
    isset($_POST['second_name']) &&
    isset($_POST['email']) &&
    isset($_POST['password']) &&
    isset($_POST['password_confirm'])
)){
    die("error");
}

$name = $_POST['name'];
$second_name = $_POST['second_name'];
$email = $_POST['email'];
$password = $_POST['password'];
$password_confirm = $_POST['password_confirm'];


if(strlen($name) < 4 || strlen($second_name) < 4 || strlen($password) < 4 || $password != $password_confirm){
    die("error2");
}
if (!filter_var($email, FILTER_VALIDATE_EMAIL)) {
    die("error3");
}

$password = md5($password.getSalt());
$result = $pdo->query("INSERT INTO users (id, name, second_name, email, password, status)".
"VALUES (null, '$name', '$second_name', '$email', '$password', 1)");

if($result){
    echo "ok";
    setcookie("auth_email", $email, time()+3600*12, '/', $_SERVER['SERVER_NAME']);
    header("Location: ../index.php");
}