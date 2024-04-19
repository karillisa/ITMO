<?php
include "db.php";
$pdo = getPDO();

if(!(isset($_POST['email']) &&
    isset($_POST['password']))
){
    die("error");
}

$email = $_POST['email'];
$password = $_POST['password'];

if (!filter_var($email, FILTER_VALIDATE_EMAIL)) {
    die("error3");
}
$password = md5($password.getSalt());
$result = $pdo->query("SELECT * FROM users WHERE email = '$email' AND password = '$password'");
$check = $result->fetch(PDO::FETCH_NUM);
if($check > 0) {
    echo "ok";
    setcookie("auth_email", $email, time()+3600*12, '/', $_SERVER['SERVER_NAME']);
    header("Location: ../main.php");
    exit();
}
echo "error4";
header("Location: ../login.html");
exit();